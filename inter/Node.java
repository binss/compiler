package inter;
import lexer.*;

public class Node 					//用于实现抽象语法数中的结点
{
   int lexline = 0;					//存储结点行号

   Node() { lexline = Lexer.line; }

   void error(String s) { throw new Error("near line "+lexline+": "+s); }   //如果出错，抛出错误结点所在的行号及错误描述

   static int labels = 0;

   public int newlabel() { return ++labels; }

   public void emitlabel(int i) { System.out.print("L" + i + ":"); }		//输出生成的三地址码

   public void emit(String s) { System.out.println("\t" + s); }
}
