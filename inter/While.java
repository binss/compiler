package inter;
import symbols.*;

public class While extends Stmt 					//�̳���Stmt�࣬����ѭ��(while...)�����Ĺ���
{
   Expr expr; 										//�ж�����
   Stmt stmt;										//ִ�����

   public While() { expr = null; stmt = null; }		//�����ӽ��Ϊ�յĽ��

   public void init(Expr x, Stmt s) 				//���캯��������ж���������Ϊ�ǲ������ͣ��׳�����
   {
      expr = x;  stmt = s;
      if( expr.type != Type.Bool ) expr.error("boolean required in while");
   }
   
   public void gen(int b, int a) 					//��������ַ��
   {
      after = a;                					//������a
      expr.jumping(0, a);
      int label = newlabel();   					//stmt����ı��
      emitlabel(label); stmt.gen(label, b);
      emit("goto L" + b);							//ʹѭ��������һ�ε���
   }
}
