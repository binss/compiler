package inter;
import lexer.*; import symbols.*;

public class Rel extends Logical 					//继承了Logical类，构造“比较”表达式节点（如< , > , ==等）
{
   public Rel(Token tok, Expr x1, Expr x2) { super(tok, x1, x2); }

   public Type check(Type p1, Type p2) 				//检查类型是否相同且为非数组类型
   {
      if ( p1 instanceof Array || p2 instanceof Array ) return null;
      else if( p1 == p2 ) return Type.Bool;
      else return null;
   }

   public void jumping(int t, int f) 				
   {
      Expr a = expr1.reduce();						//规约
      Expr b = expr2.reduce();
      String test = a.toString() + " " + op.toString() + " " + b.toString();	//生成代码串
      emitjumps(test, t, f);													//调用函数生成跳转代码
   }
}
