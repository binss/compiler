package lexer;
public class Real extends Token      //Real�࣬��Token�����࣬���ڽ��lexerΪ������������
{
	public final float value;
	public Real(float v) { super(Tag.REAL); value = v; }
	public String toString() { return "" + value; }
}
