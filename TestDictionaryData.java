import java.util.Scanner;
import java.io.File;

class DictionaryData
{
	private Entry[] dictionary=new Entry[40000];    //�洢���е��ʵĶ�������
	private String stringInSearch;                  //��ǰ���������ĵ���
	
	public DictionaryData(String filename) throws Exception//��һ���ֵ��ļ������ֵ����
	{
		Scanner input = new Scanner(new File(filename));
		int i=0; 
		 
		while (input.hasNext())
		{
			String oneEntry = input.nextLine(); 
			String[] details = oneEntry.split("\t"); 
			
			if (details[0].equals("ID")) continue;
			dictionary[i]=new Entry(Integer.parseInt(details[0]),
			details[1],details[2],details[3]); 
			i++; 
		}
	}
	
	public String getStringInSearch(){
		return stringInSearch;
	}
	
	public void setStringInSearch(String word){
		this.stringInSearch=word;
	}
	
	//�����ú���
	public void output()
	{
		for (int i=0;i<10;i++)
		{
			System.out.print(dictionary[i].toString());
		}
	}
}

public class TestDictionaryData
{
	public static void main(String[] args) throws Exception
	{ 
		DictionaryData dd=new DictionaryData("dictionary.txt");
		dd.output();
	}
}