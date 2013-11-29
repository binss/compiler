package lexer;
import java.io.*; import java.util.*; import symbols.*;
public class Lexer 
{
   public static int line = 1;
   char peek = ' ';
   Hashtable words = new Hashtable();                     //�½�һ����Ϊwords�Ĺ�ϣ��
   void reserve(Word w) { words.put(w.lexeme, w); }       //��������Ϊkey����wordֵ��Ϊvalue�����ϣ��

   public Lexer() {                                       //�ʷ�������

      reserve( new Word("if",    Tag.IF)    );            //��if�����ϣ��,��ͬ
      reserve( new Word("else",  Tag.ELSE)  );
      reserve( new Word("while", Tag.WHILE) );
      reserve( new Word("do",    Tag.DO)    );
      reserve( new Word("break", Tag.BREAK) );

      reserve( Word.True );  reserve( Word.False );        //True��False��Word���ж���
      reserve( Type.Int  );  reserve( Type.Char  );        //Type����symbols����Type���ж���
      reserve( Type.Bool );  reserve( Type.Float );
   }

   void readch() throws IOException 					//����һ�������ַ���������peek�У������ȡ�������׳��쳣
   { 
	   peek = (char)System.in.read(); 
   }  
   
   boolean readch(char c) throws IOException           //���غ���������ʶ�𸴺ϴʷ���Ԫ,������ĳЩ���ϴʷ���Ԫʱ���ͻ��ȡ��һ���ַ���peek�����
   {
      readch();
      if( peek != c ) return false;
      peek = ' ';
      return true;
   }
   
   public Token scan() throws IOException 				//ɨ�躯��
   {
      for( ; ; readch() )                               //�Թ��ո��Ʊ���Ϳ���
      {
         if( peek == ' ' || peek == '\t' ) continue;
         else if( peek == '\n' ) line = line + 1;
         else break;
      }
      switch( peek )              						//����ʶ����ϵ�Ԫ
      {
      case '&':
         if( readch('&') ) return Word.and;  else return new Token('&');  
         //���������������&�����ţ���ô����ʶ��Ϊһ��and������ ���������һ��tagΪ��&����token����ͬ
      case '|':
         if( readch('|') ) return Word.or;   else return new Token('|');
      case '=':
         if( readch('=') ) return Word.eq;   else return new Token('=');
      case '!':
         if( readch('=') ) return Word.ne;   else return new Token('!');
      case '<':
         if( readch('=') ) return Word.le;   else return new Token('<');
      case '>':
         if( readch('=') ) return Word.ge;   else return new Token('>');
      }
      if( Character.isDigit(peek) )                    //���ʶ������
      {
         int v = 0;
         do {
            v = 10*v + Character.digit(peek, 10); readch();
         } while( Character.isDigit(peek) );
         if( peek != '.' ) return new Num(v);          //���������������Num���͵�token
         float x = v; float d = 10;
         for(;;) {
            readch();
            if( ! Character.isDigit(peek) ) break;
            x = x + Character.digit(peek, 10) / d; d = d*10;
         }
         return new Real(x);							//����Ǹ�����������Real���͵�token
      }
      if( Character.isLetter(peek) )					//Ȼ����ʶ��Ϊ�ַ���
      {
         StringBuffer b = new StringBuffer();
         do {
            b.append(peek); readch();
         } while( Character.isLetterOrDigit(peek) );
         String s = b.toString();
         Word w = (Word)words.get(s);
         if( w != null ) return w;
         w = new Word(s, Tag.ID);
         words.put(s, w);
         return w;
      } 
      Token tok = new Token(peek); peek = ' ';          //����ֻ��ʶ��Ϊ�ʷ���Ԫ(Token)����
      return tok;
   }
}

