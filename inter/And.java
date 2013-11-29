package inter;
import lexer.*; import symbols.*;

public class And extends Logical 					//�̳���Logical�࣬���조�롱���ʽ�ڵ�
{
   public And(Token tok, Expr x1, Expr x2) { super(tok, x1, x2); }

   public void jumping(int t, int f) 				//ԭ��ͬOr���е�jumping
   {
      int label = f != 0 ? f : newlabel();
      expr1.jumping(0, label);
      expr2.jumping(t,f);
      if( f == 0 ) emitlabel(label);
   }
}
