package inter;
import lexer.*; import symbols.*;

public class Arith extends Op 	 			//继承了Op类，实现了对双目运算符的处理
{
   public Expr expr1, expr2;

   public Arith(Token tok, Expr x1, Expr x2)  
   {
      super(tok, null); expr1 = x1; expr2 = x2;			//调用超类的构造函数
      type = Type.max(expr1.type, expr2.type);			//获取两个操作数的最“高”类型
      if (type == null ) error("type error");			//如果类型不匹配则返回类型错误
   }

   public Expr gen() 									//把表达式的子表达式规约为地址并用运算符操作来构造三地址码的右部
   {
      return new Arith(op, expr1.reduce(), expr2.reduce());
   }

   public String toString() {
      return expr1.toString()+" "+op.toString()+" "+expr2.toString();
   }
}
