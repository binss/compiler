package lexer;

public class Token                    //token�࣬���ڹ���token
{
	public final int tag;
	public Token(int t) { tag = t; }        //���캯������ʼ��ĳtoken��tag��ֵ
	public String toString() {return "" + (char)tag;}   //����String ���͵� token 
}
