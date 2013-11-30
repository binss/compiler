package inter;
import lexer.*; import symbols.*;

public class Set extends Stmt 					//�̳���Stmt�࣬���ڸ�ֵ�����Ĺ���
{
   public Id id; 								//�Ⱥ����
   public Expr expr;							//�Ⱥ��Ҷ˵ı��ʽ

   public Set(Id i, Expr x) 					//���캯����������������޷�ͳһ�����׳�����					
   {
      id = i; expr = x;
      if ( check(id.type, expr.type) == null ) error("type error");
   }

   public Type check(Type p1, Type p2) 			//���Ⱥ����������Ƿ��ͳһ
   {
      if ( Type.numeric(p1) && Type.numeric(p2) ) return p2;
      else if ( p1 == Type.Bool && p2 == Type.Bool ) return p2;
      else return null;
   }

   public void gen(int b, int a) 				//��������ַ��
   {
      emit( id.toString() + " = " + expr.gen().toString() );
   }
}
