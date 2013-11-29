package symbols;
import lexer.*;
public class Array extends Type 			//�̳���Type�࣬���ڹ�������
{
   public Type of;                 			//�����Ԫ�ص�����
   public int size = 1;             		//�����Ԫ�ظ���
   public Array(int sz, Type p) 			//���ó��������������size��ofֵ
   {
      super("[]", Tag.INDEX, sz*p.width); size = sz;  of = p;
   }
   public String toString() { return "[" + size + "] " + of.toString(); }
}
