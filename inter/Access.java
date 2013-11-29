package inter;
import lexer.*; import symbols.*;

public class Access extends Op 				//继承了Op类，用于生成数组地址计算结点
{
   public Id array;
   public Expr index;

   public Access(Id a, Expr i, Type p) 		//构造函数，并进行类型检查，其中p是将数组平坦化后的元素类型
   {   
      super(new Word("[]", Tag.INDEX), p);  
      array = a; index = i;
   }

   public Expr gen() { return new Access(array, index.reduce(), type); }		//生成正常代码

   public void jumping(int t,int f) { emitjumps(reduce().toString(),t,f); }		//生成跳转代码

   public String toString() {
      return array.toString() + " [ " + index.toString() + " ]";
   }
}
