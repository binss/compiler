
package main;
import java.io.*; import lexer.*; import parser.*;

public class Main {

	public static void main(String[] args) throws IOException 
	{
		Lexer lex = new Lexer();          //�����µĴʷ�������
		Parser parse = new Parser(lex);   //�ôʷ��������������﷨������
		parse.program();                  //���ó�����������ַ��   
		System.out.write('\n');
	}
}

