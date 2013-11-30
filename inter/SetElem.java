package inter;
import lexer.*; import symbols.*;

public class SetElem extends Stmt 						//�̳���Stmt�࣬��������Ԫ�ظ�ֵ�����Ĺ���
{
   public Id array;										//������
   public Expr index; 									//�����±���ʽ
   public Expr expr;									//�Ⱥ��Ҷ˱��ʽ

   public SetElem(Access x, Expr y) 					//���캯����������������޷�ͳһ�����׳�����		
   {
      array = x.array; index = x.index; expr = y;
      if ( check(x.type, expr.type) == null ) error("type error");
   }

   public Type check(Type p1, Type p2) 					//���Ⱥ����������Ƿ��ͳһ
   {
      if ( p1 instanceof Array || p2 instanceof Array ) return null;
      else if ( p1 == p2 ) return p2;
      else if ( Type.numeric(p1) && Type.numeric(p2) ) return p2;
      else return null;
   }

   public void gen(int b, int a) 						//��������ַ��
   {
      String s1 = index.reduce().toString();
      String s2 = expr.reduce().toString();
      emit(array.toString() + " [ " + s1 + " ] = " + s2);
   }
}
