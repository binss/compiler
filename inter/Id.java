package inter;
import lexer.*; import symbols.*;

public class Id extends Expr 				//�̳���Expr�࣬Ϊ���gen��reduce��ʵ�ַ���
{
	public int offset;    				    //��Ե�ַ��ƫ������

	public Id(Word id, Type p, int b) { super(id, p); offset = b; }		//���ó��๹��ڵ㲢��¼ƫ����
}
