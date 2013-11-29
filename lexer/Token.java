package lexer;

public class Token                    //token类，用于构造token
{
	public final int tag;
	public Token(int t) { tag = t; }        //构造函数，初始化某token中tag的值
	public String toString() {return "" + (char)tag;}   //返回String 类型的 token 
}
