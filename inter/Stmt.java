package inter;

public class Stmt extends Node 						//继承了Node类，用于语句构造
{
   public Stmt() { }

   public static Stmt Null = new Stmt();

   public void gen(int b, int a) {} 			//处理三地址码的生成，待子类重写。b为语句代码开始处，a为该代码后的第一条指令

   int after = 0;                   			//保存下一条指令的标号
   public static Stmt Enclosing = Stmt.Null;	//用于break语句
}
