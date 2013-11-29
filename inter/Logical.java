package inter;
import lexer.*; import symbols.*;

public class Logical extends Expr 				//继承了Expr类，构造逻辑运算结点
{
   public Expr expr1, expr2;					//逻辑运算的两个操作数

   Logical(Token tok, Expr x1, Expr x2) 		//构造节点
   {
      super(tok, null);                      	//开始时，类型设置为空
      expr1 = x1; expr2 = x2;
      type = check(expr1.type, expr2.type);		//检查类型
      if (type == null ) error("type error");	//类型错误则报错
   }

   public Type check(Type p1, Type p2) 			//检查类型，如果两个不都是布尔值的话，返回null
   {
      if ( p1 == Type.Bool && p2 == Type.Bool ) return Type.Bool;
      else return null;
   }

   public Expr gen() 							//生成代码
   {
      int f = newlabel(); int a = newlabel();
      Temp temp = new Temp(type);				//产生临时变量
      this.jumping(0,f);
      emit(temp.toString() + " = true");
      emit("goto L" + a);
      emitlabel(f); emit(temp.toString() + " = false");
      emitlabel(a);
      return temp;
   }

   public String toString() {
      return expr1.toString()+" "+op.toString()+" "+expr2.toString();
   }
}
