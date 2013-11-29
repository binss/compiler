package symbols;
import lexer.*;
public class Array extends Type 			//继承了Type类，用于构造数组
{
   public Type of;                 			//数组的元素的类型
   public int size = 1;             		//数组的元素个数
   public Array(int sz, Type p) 			//调用超类设置了数组的size和of值
   {
      super("[]", Tag.INDEX, sz*p.width); size = sz;  of = p;
   }
   public String toString() { return "[" + size + "] " + of.toString(); }
}
