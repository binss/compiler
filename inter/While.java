package inter;
import symbols.*;

public class While extends Stmt 					//继承了Stmt类，用于循环(while...)语句结点的构造
{
   Expr expr; 										//判断条件
   Stmt stmt;										//执行语句

   public While() { expr = null; stmt = null; }		//构造子结点为空的结点

   public void init(Expr x, Stmt s) 				//构造函数，如果判断条件类型为非布尔类型，抛出错误
   {
      expr = x;  stmt = s;
      if( expr.type != Type.Bool ) expr.error("boolean required in while");
   }
   
   public void gen(int b, int a) 					//生成三地址码
   {
      after = a;                					//保存标号a
      expr.jumping(0, a);
      int label = newlabel();   					//stmt代码的标号
      emitlabel(label); stmt.gen(label, b);
      emit("goto L" + b);							//使循环进入下一次迭代
   }
}
