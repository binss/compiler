package lexer;
public class Word extends Token     //Word�࣬��Token�����࣬���ڽ��lexerΪString������
{
   public String lexeme = "";
   public Word(String s, int tag) { super(tag); lexeme = s; }
   public String toString() { return lexeme; }

   public static final Word         //���졣��ĳЩ���������ΪWord���ͣ���Tag����Tag�����ã����������Ϊ����
                                    //�磬����Word���Ͷ���add,��TagΪAND��256�������Ϊ && 
      and = new Word( "&&", Tag.AND ),  or = new Word( "||", Tag.OR ),
      eq  = new Word( "==", Tag.EQ  ),  ne = new Word( "!=", Tag.NE ),
      le  = new Word( "<=", Tag.LE  ),  ge = new Word( ">=", Tag.GE ),
      minus  = new Word( "minus", Tag.MINUS ),
      True   = new Word( "true",  Tag.TRUE  ),
      False  = new Word( "false", Tag.FALSE ),
      temp   = new Word( "t",     Tag.TEMP  );
}
