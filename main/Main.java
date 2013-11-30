
package main;
import java.io.*; import lexer.*; import parser.*;

public class Main {

	public static void main(String[] args) throws IOException 
	{
		Lexer lex = new Lexer();          //定义新的词法分析器
		Parser parse = new Parser(lex);   //用词法分析器来定义语法分析器
		parse.program();                  //调用程序，生成三地址码   
		System.out.write('\n');
	}
}

