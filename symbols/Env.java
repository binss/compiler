package symbols;
import java.util.*; import lexer.*; import inter.*;

public class Env 												//用于把字符串映射为Id类型的对象
{
	private Hashtable table;
	protected Env prev;

	public Env(Env n) { table = new Hashtable(); prev = n; }

	public void put(Token w, Id i) { table.put(w, i); }

	public Id get(Token w) 										//查找是否可识别为Id
	{
		for( Env e = this; e != null; e = e.prev ) 
		{
			Id found = (Id)(e.table.get(w));
			if( found != null ) return found;
		}
		return null;
	}
}
