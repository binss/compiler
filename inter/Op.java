package inter;
import lexer.*; import symbols.*;

public class Op extends Expr 			//�̳���Expr�࣬�ṩ��reduce������һ��ʵ�ַ���
{
   public Op(Token tok, Type p)  { super(tok, p); }

   public Expr reduce() 
   {
      Expr x = gen();					//����gen����������һ���������������ʱ����������
      Temp t = new Temp(type);
      emit( t.toString() + " = " + x.toString() );
      return t;
   }
}
