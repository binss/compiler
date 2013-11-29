package inter;
import lexer.*; import symbols.*;

public class Expr extends Node 					//�̳���Node�࣬����ʶ����ʽ���
{
   public Token op;								//�洢����
   public Type type;							//�洢����

   Expr(Token tok, Type p) { op = tok; type = p; }   //����ڵ�

   public Expr gen() { return this; }				//��������ַ����Ҳ�������������
   public Expr reduce() { return this; }			//�ѱ��ʽ��ԼΪһ����ʱ��������ַ��

   public void jumping(int t, int f) { emitjumps(toString(), t, f); }	//������ת����

   public void emitjumps(String test, int t, int f) 					//������ת����
   {
      if( t != 0 && f != 0 ) {
         emit("if " + test + " goto L" + t);
         emit("goto L" + f);
      }
      else if( t != 0 ) emit("if " + test + " goto L" + t);
      else if( f != 0 ) emit("iffalse " + test + " goto L" + f);
      else ; 										//���t��f��Ϊ�㣬��ô������ָ��
   }
   public String toString() { return op.toString(); }
}
