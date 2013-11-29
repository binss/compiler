package inter;
import lexer.*; import symbols.*;

public class Id extends Expr 				//继承了Expr类，为获得gen和reduce的实现方法
{
	public int offset;    				    //相对地址（偏移量）

	public Id(Word id, Type p, int b) { super(id, p); offset = b; }		//调用超类构造节点并记录偏移量
}
