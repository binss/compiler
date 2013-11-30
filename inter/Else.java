package inter;
import symbols.*;

public class Else extends Stmt 							//�̳���Stmt�࣬��������(if...else)�����Ĺ���
{
   Expr expr; 											//�ж�����
   Stmt stmt1, stmt2;									//ִ�����1��2

   public Else(Expr x, Stmt s1, Stmt s2) 				//���캯��������ж���������Ϊ�ǲ������ͣ��׳�����
   {
      expr = x; stmt1 = s1; stmt2 = s2;
      if( expr.type != Type.Bool ) expr.error("boolean required in if");
   }
   
   public void gen(int b, int a) 						//��������ַ��
   {
      int label1 = newlabel();   						//stmt1����ı��
      int label2 = newlabel();   						//stmt2����ı��
      expr.jumping(0,label2);    						//Ϊ��ʱ��ת��stmt1,������ת��stmt2
      emitlabel(label1); stmt1.gen(label1, a); emit("goto L" + a);		
      emitlabel(label2); stmt2.gen(label2, a);
   }
}
