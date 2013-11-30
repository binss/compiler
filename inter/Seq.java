package inter;

public class Seq extends Stmt 						//继承了Stmt类，用于语句序列结点的构造
{
   Stmt stmt1; Stmt stmt2;

   public Seq(Stmt s1, Stmt s2) { stmt1 = s1; stmt2 = s2; }

   public void gen(int b, int a) 					//生成三地址码
   {
      if ( stmt1 == Stmt.Null ) stmt2.gen(b, a);	//测试是否为空语句，避免使用多余标号
      else if ( stmt2 == Stmt.Null ) stmt1.gen(b, a);
      else {
         int label = newlabel();
         stmt1.gen(b,label);
         emitlabel(label);
         stmt2.gen(label,a);
      }
   }
}
