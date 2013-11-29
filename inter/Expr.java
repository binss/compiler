package inter;
import lexer.*; import symbols.*;

public class Expr extends Node 					//继承了Node类，用于识别表达式结点
{
   public Token op;								//存储操作
   public Type type;							//存储类型

   Expr(Token tok, Type p) { op = tok; type = p; }   //构造节点

   public Expr gen() { return this; }				//返回三地址码的右部，见。。。。
   public Expr reduce() { return this; }			//把表达式规约为一个临时量（单地址）

   public void jumping(int t, int f) { emitjumps(toString(), t, f); }	//生成跳转代码

   public void emitjumps(String test, int t, int f) 					//生成跳转代码
   {
      if( t != 0 && f != 0 ) {
         emit("if " + test + " goto L" + t);
         emit("goto L" + f);
      }
      else if( t != 0 ) emit("if " + test + " goto L" + t);
      else if( f != 0 ) emit("iffalse " + test + " goto L" + f);
      else ; 										//如果t和f都为零，那么不生成指令
   }
   public String toString() { return op.toString(); }
}
