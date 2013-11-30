package inter;
import symbols.*;

public class Else extends Stmt 							//继承了Stmt类，用于条件(if...else)语句结点的构造
{
   Expr expr; 											//判断条件
   Stmt stmt1, stmt2;									//执行语句1和2

   public Else(Expr x, Stmt s1, Stmt s2) 				//构造函数，如果判断条件类型为非布尔类型，抛出错误
   {
      expr = x; stmt1 = s1; stmt2 = s2;
      if( expr.type != Type.Bool ) expr.error("boolean required in if");
   }
   
   public void gen(int b, int a) 						//生成三地址码
   {
      int label1 = newlabel();   						//stmt1代码的标号
      int label2 = newlabel();   						//stmt2代码的标号
      expr.jumping(0,label2);    						//为真时跳转到stmt1,否则跳转到stmt2
      emitlabel(label1); stmt1.gen(label1, a); emit("goto L" + a);		
      emitlabel(label2); stmt2.gen(label2, a);
   }
}
