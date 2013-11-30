package parser;
import java.io.*; import lexer.*; import symbols.*; import inter.*;

public class Parser 						//语法分析器，用于构造出一棵抽象语法树
{
   private Lexer lex;    					//词法分析器
   private Token look;   					//向前看（lookahead）的词法单元（token）
   Env top = null;      					//当前符号表（默认为空）
   int used = 0;         					//用于储存变量声明

   public Parser(Lexer l) throws IOException { lex = l; move(); }

   void move() throws IOException { look = lex.scan(); }					//定义扫描过程中的异常处理

   void error(String s) { throw new Error("near line "+lex.line+": "+s); }	//抛出语法错误出现的行数

   void match(int t) throws IOException 									//定义匹配过程中的异常处理
   {
      if( look.tag == t ) move();
      else error("syntax error");
   }

   public void program() throws IOException 								//调用block来对输入流进行语法分析
   {  
      Stmt s = block();
      int begin = s.newlabel();  int after = s.newlabel();
      s.emitlabel(begin);  s.gen(begin, after);  s.emitlabel(after);
   }

   Stmt block() throws IOException 											//对符号表的处理
   {  
      match('{');  Env savedEnv = top;  top = new Env(top);					//存储了前一个符号表，又读取新的符号表
      decls(); Stmt s = stmts();
      match('}');  top = savedEnv;
      return s;
   }

   void decls() throws IOException 											//对声明的处理
   {
      while( look.tag == Tag.BASIC ) 
      {   
         Type p = type(); Token tok = look; match(Tag.ID); match(';');
         Id id = new Id((Word)tok, p, used);
         top.put( tok, id );												//处理为符号表中有关标识符的条目
         used = used + p.width;
      }
   }

   Type type() throws IOException											//对类型的处理
   {
      Type p = (Type)look;            										//期望两者类型相同
      match(Tag.BASIC);
      if( look.tag != '[' ) return p; 
      else return dims(p);           										//返回数组类型
   }

   Type dims(Type p) throws IOException										//对数组访问的处理
   {
      match('[');  Token tok = look;  match(Tag.NUM);  match(']');
      if( look.tag == '[' )
      p = dims(p);
      return new Array(((Num)tok).value, p);								//返回数组
   }

   Stmt stmts() throws IOException											//对多语句的处理
   {
      if ( look.tag == '}' ) return Stmt.Null;								//遇到结束符号（ } ）则返回空语句
      else return new Seq(stmt(), stmts());									//否则返回语句序列
   }

   Stmt stmt() throws IOException 											//对特殊语句的处理								
   {
      Expr x;  Stmt s, s1, s2;
      Stmt savedStmt;         												//保存break的外层语句

      switch( look.tag ) 													//switch语句，各分支对应了不同类型
      {
      case ';':																//结束的情况
         move();
         return Stmt.Null;

      case Tag.IF:															//if语句的情况
         match(Tag.IF); match('('); x = bool(); match(')');
         s1 = stmt();
         if( look.tag != Tag.ELSE ) return new If(x, s1);
         match(Tag.ELSE);
         s2 = stmt();
         return new Else(x, s1, s2);

      case Tag.WHILE:														//while语句的情况														
         While whilenode = new While();
         savedStmt = Stmt.Enclosing; Stmt.Enclosing = whilenode;
         match(Tag.WHILE); match('('); x = bool(); match(')');
         s1 = stmt();
         whilenode.init(x, s1);
         Stmt.Enclosing = savedStmt;  										//重置 Stmt.Enclosing
         return whilenode;

      case Tag.DO:															//do语句的情况
         Do donode = new Do();
         savedStmt = Stmt.Enclosing; Stmt.Enclosing = donode;
         match(Tag.DO);
         s1 = stmt();
         match(Tag.WHILE); match('('); x = bool(); match(')'); match(';');
         donode.init(s1, x);
         Stmt.Enclosing = savedStmt;							   		    //重置 Stmt.Enclosing
         return donode;

      case Tag.BREAK:														//do语句的情况
         match(Tag.BREAK); match(';');
         return new Break();

      case '{':																//语句块的情况
         return block();

      default:
         return assign();
      }
   }

   Stmt assign() throws IOException 										//对赋值语句的处理
   {
      Stmt stmt;  Token t = look;
      match(Tag.ID);
      Id id = top.get(t);
      if( id == null ) error(t.toString() + " undeclared");					//如果赋值对象不存在，抛出错误

      if( look.tag == '=' ) {       										// S -> id = E ;
         move();  stmt = new Set(id, bool());
      }
      else {                        										// S -> L = E ;
         Access x = offset(id);
         match('=');  stmt = new SetElem(x, bool());
      }
      match(';');
      return stmt;
   }

   //以下是对双目运算符的处理
   Expr bool() throws IOException 											//对布尔（逻辑）语句的处理
   {
      Expr x = join();
      while( look.tag == Tag.OR ) {
         Token tok = look;  move();  x = new Or(tok, x, join());
      }
      return x;
   }

   Expr join() throws IOException 										   //对并（and）语句的处理
   {
      Expr x = equality();
      while( look.tag == Tag.AND ) {
         Token tok = look;  move();  x = new And(tok, x, equality());
      }
      return x;
   }

   Expr equality() throws IOException 									  //对相等（＝＝）语句的处理
   {
      Expr x = rel();
      while( look.tag == Tag.EQ || look.tag == Tag.NE ) {
         Token tok = look;  move();  x = new Rel(tok, x, rel());
      }
      return x;
   }

   Expr rel() throws IOException 										 //对比较（>,<）语句的处理
   {
      Expr x = expr();
      switch( look.tag ) {
      case '<': case Tag.LE: case Tag.GE: case '>':
         Token tok = look;  move();  return new Rel(tok, x, expr());
      default:
         return x;
      }
   }

   Expr expr() throws IOException										//对加减（+，-）语句的处理
   {
      Expr x = term();
      while( look.tag == '+' || look.tag == '-' ) {
         Token tok = look;  move();  x = new Arith(tok, x, term());
      }
      return x;
   }

   Expr term() throws IOException 										//对乘除（*，/）语句的处理
   {
      Expr x = unary();
      while(look.tag == '*' || look.tag == '/' ) {
         Token tok = look;  move();   x = new Arith(tok, x, unary());
      }
      return x;
   }
   
   //以下为对单目运算符的处理
   Expr unary() throws IOException 										//对负（-），非（！）语句的处理						
   {
      if( look.tag == '-' ) {
         move();  return new Unary(Word.minus, unary());
      }
      else if( look.tag == '!' ) {
         Token tok = look;  move();  return new Not(tok, unary());
      }
      else return factor();
   }

   Expr factor() throws IOException 									//因子生成
   {
      Expr x = null;
      switch( look.tag ) {
      case '(':
         move(); x = bool(); match(')');
         return x;
      case Tag.NUM:
         x = new Constant(look, Type.Int);    move(); return x;
      case Tag.REAL:
         x = new Constant(look, Type.Float);  move(); return x;
      case Tag.TRUE:
         x = Constant.True;                   move(); return x;
      case Tag.FALSE:
         x = Constant.False;                  move(); return x;
      default:
         error("syntax error");
         return x;
      case Tag.ID:
         String s = look.toString();
         Id id = top.get(look);
         if( id == null ) error(look.toString() + " undeclared");
         move();
         if( look.tag != '[' ) return id;
         else return offset(id);
      }
   }

   Access offset(Id a) throws IOException 								//偏移量生成
   {  
      Expr i; Expr w; Expr t1, t2; Expr loc;  							//继承id

      Type type = a.type;
      match('['); i = bool(); match(']');     							//第一个下标, I -> [ E ]
      type = ((Array)type).of;
      w = new Constant(type.width);
      t1 = new Arith(new Token('*'), i, w);
      loc = t1;
      while( look.tag == '[' ) 											//多维下标
      {     
         match('['); i = bool(); match(']');
         type = ((Array)type).of;
         w = new Constant(type.width);
         t1 = new Arith(new Token('*'), i, w);
         t2 = new Arith(new Token('+'), loc, t1);
         loc = t2;
      }

      return new Access(a, loc, type);
   }
}
