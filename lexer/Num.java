package lexer;

public class Num extends Token   //Num�࣬��Token�����࣬���ڽ��lexerΪ������������
{
	public final int value;
	public Num(int v) { super(Tag.NUM); value = v; }
	public String toString() { return "" + value; }
}
