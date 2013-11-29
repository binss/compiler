package inter;
import lexer.*; import symbols.*;

public class Or extends Logical						//�̳���Logical�࣬���조�򡱱��ʽ�ڵ�
{
   public Or(Token tok, Expr x1, Expr x2) { super(tok, x1, x2); }

   public void jumping(int t, int f) 				//���ɲ������ʽ����ת����
   {
      int label = t != 0 ? t : newlabel();			//���t����0����ôexpr1��t���ڱ�Ȼ��t��false����Ϊexpr2�ĵ�һ��ָ��
      expr1.jumping(label, 0);
      expr2.jumping(t,f);							//expr2��t,f���ں���Ӧ������ͬ
      if( t == 0 ) emitlabel(label);				//���t��0����expr1��expr2�Ĵ������ɺ�����label
   }
}
