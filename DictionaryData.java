import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

class DictionaryData
{
	private Entry[] dictionary=new Entry[40000];    //�洢���е��ʵĶ�������
	private String stringInSearch;                  //��ǰ���������ĵ���
	private int numberOfEntry=0;                    //��������
	
  	private ArrayList<String> associatedWord = new ArrayList<>();   //�ɵ�ǰ���������Ĵ���������Ĵ�
	
	private ArrayList<EntrySimilar> similarWord = new ArrayList<>();      //�뵱ǰ�����Ĵ����Ƶĵ���
	
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
			numberOfEntry++;
		}
		
	}
	
	public String getStringInSearch(){
		return stringInSearch;
	}
	
	public void setStringInSearch(String word){
		this.stringInSearch=word;
	}
	
	public Entry findEntry(String entry)
	{
		Entry entry_match=new Entry(); 
		if (entry.equals("a")) return dictionary[0];
		int low=0;
		int high=numberOfEntry-1;
		while (low<=high)
		{
			int middle = (low+high)/2; 
			if ((dictionary[middle].getWord()).equals(entry))
			{
				entry_match=dictionary[middle];
				break;
			}
			else if ((dictionary[middle].getWord()).compareTo(entry)<0)
				low=middle+1;
			else
				high=middle-1;
		} 
		return entry_match;
	}
	
	public String findSimilarWord(String entry)
	{
		similarWord.clear();       
		for (int i=0;i<numberOfEntry;i++){ 
			int ed=dictionary[i].editDistance(entry);
			if (ed<=2){ 
				similarWord.add(new EntrySimilar(dictionary[i].getID(),dictionary[i].getWord(),
				dictionary[i].getPhoneticSymbol(),dictionary[i].getExplation(),ed));
			}
		}
		Collections.sort(similarWord);                   //���ձ༭�����������
		StringBuilder wordSimilar=new StringBuilder();
		for (int i=0;i<=similarWord.size()-1;i++)
			wordSimilar.append(similarWord.get(i).getWord()+"\n"); 
		return wordSimilar.toString();
	}
	
	public String findAssociationWord(String entry)
	{
		associatedWord.clear();       
		for (int i=0;i<numberOfEntry;i++)
			if ((dictionary[i].getWord()).startsWith(entry))
				associatedWord.add(dictionary[i].getWord());
		StringBuilder wordsAssociated=new StringBuilder();
		for (int i=0;i<=associatedWord.size()-1;i++)
			wordsAssociated.append(associatedWord.get(i)+"\n"); 
		return wordsAssociated.toString();
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

