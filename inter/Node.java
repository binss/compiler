package inter;
import lexer.*;

public class Node 					//����ʵ�ֳ����﷨���еĽ��
{
   int lexline = 0;					//�洢����к�

   Node() { lexline = Lexer.line; }

   void error(String s) { throw new Error("near line "+lexline+": "+s); }   //��������׳����������ڵ��кż���������

   static int labels = 0;

   public int newlabel() { return ++labels; }

   public void emitlabel(int i) { System.out.print("L" + i + ":"); }		//������ɵ�����ַ��

   public void emit(String s) { System.out.println("\t" + s); }
}
