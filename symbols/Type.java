package symbols;
import lexer.*;
public class Type extends Word 					//�̳���Word�࣬����ʶ��ӳ��Ϊ����
{
   public int width = 0;          				//���ڴ洢����

   public Type(String s, int tag, int w) { super(s, tag); width = w; }

   public static final Type						//�������Ͷ���
      Int   = new Type( "int",   Tag.BASIC, 4 ),
      Float = new Type( "float", Tag.BASIC, 8 ),
      Char  = new Type( "char",  Tag.BASIC, 1 ),
      Bool  = new Type( "bool",  Tag.BASIC, 1 );

   public static boolean numeric(Type p) 		//ʶ���Ƿ�Ϊ�ɽ�������ת��������
   {
      if (p == Type.Char || p == Type.Int || p == Type.Float) return true;
      else return false;
   }

   public static Type max(Type p1, Type p2 ) 	//����ת����������ߡ�������
   {
      if ( ! numeric(p1) || ! numeric(p2) ) return null;
      else if ( p1 == Type.Float || p2 == Type.Float ) return Type.Float;
      else if ( p1 == Type.Int   || p2 == Type.Int   ) return Type.Int;
      else return Type.Char;
   }
}
