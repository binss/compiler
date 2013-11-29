package lexer;
public class Real extends Token      //Real类，是Token的子类，用于解决lexer为浮点数的问题
{
	public final float value;
	public Real(float v) { super(Tag.REAL); value = v; }
	public String toString() { return "" + value; }
}
