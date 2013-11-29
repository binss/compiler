package lexer;

public class Num extends Token   //Num类，是Token的子类，用于解决lexer为整型数的问题
{
	public final int value;
	public Num(int v) { super(Tag.NUM); value = v; }
	public String toString() { return "" + value; }
}
