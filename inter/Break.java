package inter;

public class Break extends Stmt						//继承了Stmt类，用于跳出当前循环（选择）语句结点的构造
{
   Stmt stmt;										//外围语句构造

   public Break() 									//构造函数，如果外围语句为空，则抛出错误
   {
      if( Stmt.Enclosing == Stmt.Null ) error("unenclosed break");
      stmt = Stmt.Enclosing;
   }

   public void gen(int b, int a) 					//生成三地址码
   {
      emit( "goto L" + stmt.after);
   }
}
