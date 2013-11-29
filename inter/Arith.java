package inter;
import lexer.*; import symbols.*;

public class Arith extends Op 	 			//�̳���Op�࣬ʵ���˶�˫Ŀ������Ĵ���
{
   public Expr expr1, expr2;

   public Arith(Token tok, Expr x1, Expr x2)  
   {
      super(tok, null); expr1 = x1; expr2 = x2;			//���ó���Ĺ��캯��
      type = Type.max(expr1.type, expr2.type);			//��ȡ��������������ߡ�����
      if (type == null ) error("type error");			//������Ͳ�ƥ���򷵻����ʹ���
   }

   public Expr gen() 									//�ѱ��ʽ���ӱ��ʽ��ԼΪ��ַ�����������������������ַ����Ҳ�
   {
      return new Arith(op, expr1.reduce(), expr2.reduce());
   }

   public String toString() {
      return expr1.toString()+" "+op.toString()+" "+expr2.toString();
   }
}
