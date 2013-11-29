package inter;
import lexer.*; import symbols.*;

public class Unary extends Op  	 			//继承了Op类，实现了对单目运算符的处理
{
   public Expr expr;

   public Unary(Token tok, Expr x) 			
   {    // handles minus, for ! see Not
      super(tok, null);  expr = x;
      type = Type.max(Type.Int, expr.type);			//获取两个操作数的最“高”类型
      if (type == null ) error("type error");		//如果类型不匹配则返回类型错误
   }

   public Expr gen() { return new Unary(op, expr.reduce()); }

   public String toString() { return op.toString()+" "+expr.toString(); }
}
