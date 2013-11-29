package lexer;
import java.io.*; import java.util.*; import symbols.*;
public class Lexer 
{
   public static int line = 1;
   char peek = ' ';
   Hashtable words = new Hashtable();                     //新建一个名为words的哈希表
   void reserve(Word w) { words.put(w.lexeme, w); }       //将词素作为key，把word值作为value插入哈希表

   public Lexer() {                                       //词法分析器

      reserve( new Word("if",    Tag.IF)    );            //把if插入哈希表,下同
      reserve( new Word("else",  Tag.ELSE)  );
      reserve( new Word("while", Tag.WHILE) );
      reserve( new Word("do",    Tag.DO)    );
      reserve( new Word("break", Tag.BREAK) );

      reserve( Word.True );  reserve( Word.False );        //True和False在Word类中定义
      reserve( Type.Int  );  reserve( Type.Char  );        //Type则在symbols包的Type类中定义
      reserve( Type.Bool );  reserve( Type.Float );
   }

   void readch() throws IOException 					//把下一个输入字符读到变量peek中，如果读取出错，则抛出异常
   { 
	   peek = (char)System.in.read(); 
   }  
   
   boolean readch(char c) throws IOException           //重载函数，用于识别复合词法单元,当读到某些复合词法单元时，就会读取下一个字符到peek并检查
   {
      readch();
      if( peek != c ) return false;
      peek = ' ';
      return true;
   }
   
   public Token scan() throws IOException 				//扫描函数
   {
      for( ; ; readch() )                               //略过空格，制表符和空行
      {
         if( peek == ' ' || peek == '\t' ) continue;
         else if( peek == '\n' ) line = line + 1;
         else break;
      }
      switch( peek )              						//优先识别符合单元
      {
      case '&':
         if( readch('&') ) return Word.and;  else return new Token('&');  
         //如果有连续两个“&”符号，那么他被识别为一个and操作， 否则仅仅是一个tag为“&”的token，下同
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
      if( Character.isDigit(peek) )                    //其次识别数字
      {
         int v = 0;
         do {
            v = 10*v + Character.digit(peek, 10); readch();
         } while( Character.isDigit(peek) );
         if( peek != '.' ) return new Num(v);          //如果是整数，返回Num类型的token
         float x = v; float d = 10;
         for(;;) {
            readch();
            if( ! Character.isDigit(peek) ) break;
            x = x + Character.digit(peek, 10) / d; d = d*10;
         }
         return new Real(x);							//如果是浮点数，返回Real类型的token
      }
      if( Character.isLetter(peek) )					//然后考虑识别为字符串
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
      Token tok = new Token(peek); peek = ' ';          //最终只能识别为词法单元(Token)类型
      return tok;
   }
}

