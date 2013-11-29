package inter;
import lexer.*; import symbols.*;

public class Constant extends Expr				//继承了Expr类，构造常数结点
{
   public Constant(Token tok, Type p) { super(tok, p); }		//调用超类构造函数构造结点
   public Constant(int i) { super(new Num(i), Type.Int); }		//重载函数使得可根据整数创建一个常量对象

   public static final Constant
      True  = new Constant(Word.True,  Type.Bool),				//true出口
      False = new Constant(Word.False, Type.Bool);				//false出口

   public void jumping(int t, int f) {
      if ( this == True && t != 0 ) emit("goto L" + t);			//如果为真，则跳转代码包含目标为t的跳转指令
      else if ( this == False && f != 0) emit("goto L" + f);	//如果为假，则跳转代码包含目标为f的跳转指令
   }
}
