package inter;
import lexer.*; import symbols.*;

public class Not extends Logical					//�̳���Logical�࣬���조�ǡ����ʽ�ڵ�
{
   public Not(Token tok, Expr x2) { super(tok, x2, x2); }			//��Not����˫Ŀ�����������

   public void jumping(int t, int f) { expr2.jumping(f, t); }		//��true��false���ڶԵ�

   public String toString() { return op.toString()+" "+expr2.toString(); }
}
