package lexer;
public class Word extends Token     //Word类，是Token的子类，用于解决lexer为String的问题
{
   public String lexeme = "";
   public Word(String s, int tag) { super(tag); lexeme = s; }
   public String toString() { return lexeme; }

   public static final Word         //构造。将某些运算符构造为Word类型，其Tag已由Tag类设置，其词素设置为符号
                                    //如，对于Word类型对象add,其Tag为AND即256，其词素为 && 
      and = new Word( "&&", Tag.AND ),  or = new Word( "||", Tag.OR ),
      eq  = new Word( "==", Tag.EQ  ),  ne = new Word( "!=", Tag.NE ),
      le  = new Word( "<=", Tag.LE  ),  ge = new Word( ">=", Tag.GE ),
      minus  = new Word( "minus", Tag.MINUS ),
      True   = new Word( "true",  Tag.TRUE  ),
      False  = new Word( "false", Tag.FALSE ),
      temp   = new Word( "t",     Tag.TEMP  );
}
