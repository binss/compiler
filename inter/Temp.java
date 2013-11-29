package inter;
import lexer.*; import symbols.*;

public class Temp extends Expr  			//继承了Expr类，用于构造临时变量
{
   static int count = 0;
   int number = 0;

   public Temp(Type p) { super(Word.temp, p); number = ++count; }		//构造函数，注意，需要有类型才能构造

   public String toString() { return "t" + number; }
}
