package inter;

public class Stmt extends Node 						//�̳���Node�࣬������乹��
{
   public Stmt() { }

   public static Stmt Null = new Stmt();

   public void gen(int b, int a) {} 			//��������ַ������ɣ���������д��bΪ�����뿪ʼ����aΪ�ô����ĵ�һ��ָ��

   int after = 0;                   			//������һ��ָ��ı��
   public static Stmt Enclosing = Stmt.Null;	//����break���
}
