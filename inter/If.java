package inter;
import symbols.*;

public class If extends Stmt 							//�̳���Stmt�࣬��������(if)�����Ĺ���
{

   Expr expr; 											//�ж�����
   Stmt stmt;											//ִ�����

   public If(Expr x, Stmt s) 							//���캯��������ж���������Ϊ�ǲ������ͣ��׳�����
   {
      expr = x;  stmt = s;
      if( expr.type != Type.Bool ) expr.error("boolean required in if");
   }

   public void gen(int b, int a) 						//��������ַ��
   {
      int label = newlabel(); 							//stmt����ı��
      expr.jumping(0, a);     							//tʱ�趨Ϊ0����ʾ�Թ���fʱ��ת��a
      emitlabel(label); stmt.gen(label, a);
   }
}
