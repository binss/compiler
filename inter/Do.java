package inter;
import symbols.*;

public class Do extends Stmt 							//�̳���Stmt�࣬����ѭ��(do...while)�����Ĺ���
{
   Expr expr; 											//�ж�����
   Stmt stmt;											//ִ�����

   public Do() { expr = null; stmt = null; }			//�����ӽ��Ϊ�յĽ��

   public void init(Stmt s, Expr x)						//���캯��������ж���������Ϊ�ǲ������ͣ��׳�����
   {
      expr = x; stmt = s;
      if( expr.type != Type.Bool ) expr.error("boolean required in do");
   }

   public void gen(int b, int a) 						//��������ַ��
   {
      after = a;										//������a
      int label = newlabel();  							//stmt����ı��
      stmt.gen(b,label);
      emitlabel(label);
      expr.jumping(b,0);			
   }
}
