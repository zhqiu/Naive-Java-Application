public class Entry
{
	private int ID;                 //ID
	private String word;            //����
	private String phoneticSymbol;  //����
	private String explation;       //����
	
	public Entry()
	{
		this(0,"","","");
	}
	
	public Entry(int ID,String word,String phoneticSymbol,String explation)
	{
		this.ID=ID;
		this.word=word;
		this.phoneticSymbol=phoneticSymbol;
		this.explation=explation;
	}
	
	public int getID(){
		return ID;
	}
	
	public String getWord(){
		return word;
	}
	
	public String getPhoneticSymbol(){
		return phoneticSymbol;
	}
	
	public String getExplation(){
		return explation;
	}
	
	public void setID(int ID){
		this.ID=ID;
	}
	
	public void setWord(String word){
		this.word=word;
	}
	
	public void setPhoneticSymbol(String ps){
		this.phoneticSymbol=ps;
	}
	
	public void setExplation(String exp){
		this.explation=exp;
	}
	
	//����������ʺ�����һ�����ʵı༭����
	public int editDistance(String s){
		int n = this.word.length();
        int m = s.length();
        if(n == 0) return m;
        if(m == 0) return n;
 
        int[][] d = new int[n+1][m+1];           //��¼����
        for(int i = 0; i <= n; i++) d[i][0] = i; //��ʼ����
        for(int i = 0; i <= m; i++) d[0][i] = i; //��ʼ����
 
        for(int i = 1; i <= n; i++) {
            char ch1 = this.word.charAt(i - 1);
            for(int j = 1; j <= m; j++) {
                char ch2 = s.charAt(j - 1);
                int temp = ch1 == ch2 ? 0: 1; //�����ַ������б�Ϸ��ļ�ȨֵΪ0,����Ϊ1
                d[i][j] = Math.min(Math.min(d[i-1][j] + 1, d[i][j-1] + 1), d[i-1][j-1] + temp);
            }
        }
        return d[n][m];
	}
	 
	public String toString(){ 
		return "ID:\n"+ID+"\n"+"Word:\n"+word+"\n"+"Phonetic Symbol:\n"+phoneticSymbol+"\n"+"Explaination:\n"+explation+"\n";
	}
}