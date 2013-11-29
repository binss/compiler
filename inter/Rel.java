package inter;
import lexer.*; import symbols.*;

public class Rel extends Logical 					//�̳���Logical�࣬���조�Ƚϡ����ʽ�ڵ㣨��< , > , ==�ȣ�
{
   public Rel(Token tok, Expr x1, Expr x2) { super(tok, x1, x2); }

   public Type check(Type p1, Type p2) 				//��������Ƿ���ͬ��Ϊ����������
   {
      if ( p1 instanceof Array || p2 instanceof Array ) return null;
      else if( p1 == p2 ) return Type.Bool;
      else return null;
   }

   public void jumping(int t, int f) 				
   {
      Expr a = expr1.reduce();						//��Լ
      Expr b = expr2.reduce();
      String test = a.toString() + " " + op.toString() + " " + b.toString();	//���ɴ��봮
      emitjumps(test, t, f);													//���ú���������ת����
   }
}
