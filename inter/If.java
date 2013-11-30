package inter;
import symbols.*;

public class If extends Stmt 							//继承了Stmt类，用于条件(if)语句结点的构造
{

   Expr expr; 											//判断条件
   Stmt stmt;											//执行语句

   public If(Expr x, Stmt s) 							//构造函数，如果判断条件类型为非布尔类型，抛出错误
   {
      expr = x;  stmt = s;
      if( expr.type != Type.Bool ) expr.error("boolean required in if");
   }

   public void gen(int b, int a) 						//生成三地址码
   {
      int label = newlabel(); 							//stmt代码的标号
      expr.jumping(0, a);     							//t时设定为0，表示略过，f时跳转到a
      emitlabel(label); stmt.gen(label, a);
   }
}
