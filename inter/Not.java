package inter;
import lexer.*; import symbols.*;

public class Not extends Logical					//继承了Logical类，构造“非”表达式节点
{
   public Not(Token tok, Expr x2) { super(tok, x2, x2); }			//把Not当作双目运算符来处理

   public void jumping(int t, int f) { expr2.jumping(f, t); }		//把true和false出口对调

   public String toString() { return op.toString()+" "+expr2.toString(); }
}
