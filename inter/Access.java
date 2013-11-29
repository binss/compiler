package inter;
import lexer.*; import symbols.*;

public class Access extends Op 				//�̳���Op�࣬�������������ַ������
{
   public Id array;
   public Expr index;

   public Access(Id a, Expr i, Type p) 		//���캯�������������ͼ�飬����p�ǽ�����ƽ̹�����Ԫ������
   {   
      super(new Word("[]", Tag.INDEX), p);  
      array = a; index = i;
   }

   public Expr gen() { return new Access(array, index.reduce(), type); }		//������������

   public void jumping(int t,int f) { emitjumps(reduce().toString(),t,f); }		//������ת����

   public String toString() {
      return array.toString() + " [ " + index.toString() + " ]";
   }
}
