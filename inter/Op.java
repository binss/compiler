package inter;
import lexer.*; import symbols.*;

public class Op extends Expr 			//继承了Expr类，提供对reduce函数的一种实现方法
{
   public Op(Token tok, Type p)  { super(tok, p); }

   public Expr reduce() 
   {
      Expr x = gen();					//调用gen函数来生成一个项，并把这个项赋给临时变量并返回
      Temp t = new Temp(type);
      emit( t.toString() + " = " + x.toString() );
      return t;
   }
}
