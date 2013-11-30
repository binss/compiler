package inter;
import lexer.*; import symbols.*;

public class SetElem extends Stmt 						//继承了Stmt类，用于数组元素赋值语句结点的构造
{
   public Id array;										//数组名
   public Expr index; 									//数组下表表达式
   public Expr expr;									//等号右端表达式

   public SetElem(Access x, Expr y) 					//构造函数，如果两者类型无法统一，则抛出错误		
   {
      array = x.array; index = x.index; expr = y;
      if ( check(x.type, expr.type) == null ) error("type error");
   }

   public Type check(Type p1, Type p2) 					//检查等号两边类型是否可统一
   {
      if ( p1 instanceof Array || p2 instanceof Array ) return null;
      else if ( p1 == p2 ) return p2;
      else if ( Type.numeric(p1) && Type.numeric(p2) ) return p2;
      else return null;
   }

   public void gen(int b, int a) 						//生成三地址码
   {
      String s1 = index.reduce().toString();
      String s2 = expr.reduce().toString();
      emit(array.toString() + " [ " + s1 + " ] = " + s2);
   }
}
