package inter;
import lexer.*; import symbols.*;

public class Temp extends Expr  			//�̳���Expr�࣬���ڹ�����ʱ����
{
   static int count = 0;
   int number = 0;

   public Temp(Type p) { super(Word.temp, p); number = ++count; }		//���캯����ע�⣬��Ҫ�����Ͳ��ܹ���

   public String toString() { return "t" + number; }
}
