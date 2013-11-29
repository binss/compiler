package inter;
import lexer.*; import symbols.*;

public class Constant extends Expr				//�̳���Expr�࣬���쳣�����
{
   public Constant(Token tok, Type p) { super(tok, p); }		//���ó��๹�캯��������
   public Constant(int i) { super(new Num(i), Type.Int); }		//���غ���ʹ�ÿɸ�����������һ����������

   public static final Constant
      True  = new Constant(Word.True,  Type.Bool),				//true����
      False = new Constant(Word.False, Type.Bool);				//false����

   public void jumping(int t, int f) {
      if ( this == True && t != 0 ) emit("goto L" + t);			//���Ϊ�棬����ת�������Ŀ��Ϊt����תָ��
      else if ( this == False && f != 0) emit("goto L" + f);	//���Ϊ�٣�����ת�������Ŀ��Ϊf����תָ��
   }
}
