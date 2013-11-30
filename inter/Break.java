package inter;

public class Break extends Stmt						//�̳���Stmt�࣬����������ǰѭ����ѡ�������Ĺ���
{
   Stmt stmt;										//��Χ��乹��

   public Break() 									//���캯���������Χ���Ϊ�գ����׳�����
   {
      if( Stmt.Enclosing == Stmt.Null ) error("unenclosed break");
      stmt = Stmt.Enclosing;
   }

   public void gen(int b, int a) 					//��������ַ��
   {
      emit( "goto L" + stmt.after);
   }
}
