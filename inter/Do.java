package inter;
import symbols.*;

public class Do extends Stmt 							//继承了Stmt类，用于循环(do...while)语句结点的构造
{
   Expr expr; 											//判断条件
   Stmt stmt;											//执行语句

   public Do() { expr = null; stmt = null; }			//构造子结点为空的结点

   public void init(Stmt s, Expr x)						//构造函数，如果判断条件类型为非布尔类型，抛出错误
   {
      expr = x; stmt = s;
      if( expr.type != Type.Bool ) expr.error("boolean required in do");
   }

   public void gen(int b, int a) 						//生成三地址码
   {
      after = a;										//保存标号a
      int label = newlabel();  							//stmt代码的标号
      stmt.gen(b,label);
      emitlabel(label);
      expr.jumping(b,0);			
   }
}
