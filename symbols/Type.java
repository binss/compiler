package symbols;
import lexer.*;
public class Type extends Word 					//继承了Word类，用于识别并映射为类型
{
   public int width = 0;          				//用于存储分配

   public Type(String s, int tag, int w) { super(s, tag); width = w; }

   public static final Type						//基本类型对象
      Int   = new Type( "int",   Tag.BASIC, 4 ),
      Float = new Type( "float", Tag.BASIC, 8 ),
      Char  = new Type( "char",  Tag.BASIC, 1 ),
      Bool  = new Type( "bool",  Tag.BASIC, 1 );

   public static boolean numeric(Type p) 		//识别是否为可进行类型转换的类型
   {
      if (p == Type.Char || p == Type.Int || p == Type.Float) return true;
      else return false;
   }

   public static Type max(Type p1, Type p2 ) 	//类型转换，返回最“高”的类型
   {
      if ( ! numeric(p1) || ! numeric(p2) ) return null;
      else if ( p1 == Type.Float || p2 == Type.Float ) return Type.Float;
      else if ( p1 == Type.Int   || p2 == Type.Int   ) return Type.Int;
      else return Type.Char;
   }
}
