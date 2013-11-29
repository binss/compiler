package inter;
import lexer.*; import symbols.*;

public class Unary extends Op  	 			//�̳���Op�࣬ʵ���˶Ե�Ŀ������Ĵ���
{
   public Expr expr;

   public Unary(Token tok, Expr x) 			
   {    // handles minus, for ! see Not
      super(tok, null);  expr = x;
      type = Type.max(Type.Int, expr.type);			//��ȡ��������������ߡ�����
      if (type == null ) error("type error");		//������Ͳ�ƥ���򷵻����ʹ���
   }

   public Expr gen() { return new Unary(op, expr.reduce()); }

   public String toString() { return op.toString()+" "+expr.toString(); }
}
