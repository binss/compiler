package inter;
import lexer.*; import symbols.*;

public class Or extends Logical						//继承了Logical类，构造“或”表达式节点
{
   public Or(Token tok, Expr x1, Expr x2) { super(tok, x1, x2); }

   public void jumping(int t, int f) 				//生成布尔表达式的跳转代码
   {
      int label = t != 0 ? t : newlabel();			//如果t不是0，那么expr1的t出口必然是t，false出口为expr2的第一条指令
      expr1.jumping(label, 0);
      expr2.jumping(t,f);							//expr2的t,f出口和相应出口相同
      if( t == 0 ) emitlabel(label);				//如果t是0，在expr1和expr2的代码生成后生成label
   }
}
