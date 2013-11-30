package inter;
import lexer.*; import symbols.*;

public class Set extends Stmt 					//继承了Stmt类，用于赋值语句结点的构造
{
   public Id id; 								//等号左端
   public Expr expr;							//等号右端的表达式

   public Set(Id i, Expr x) 					//构造函数，如果两者类型无法统一，则抛出错误					
   {
      id = i; expr = x;
      if ( check(id.type, expr.type) == null ) error("type error");
   }

   public Type check(Type p1, Type p2) 			//检查等号两边类型是否可统一
   {
      if ( Type.numeric(p1) && Type.numeric(p2) ) return p2;
      else if ( p1 == Type.Bool && p2 == Type.Bool ) return p2;
      else return null;
   }

   public void gen(int b, int a) 				//生成三地址码
   {
      emit( id.toString() + " = " + expr.gen().toString() );
   }
}
