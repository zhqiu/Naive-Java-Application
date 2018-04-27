public class EntrySimilar extends Entry implements Comparable<EntrySimilar>  //继承Entry 并实现Comparable接口
{
	private int editDistance;    //编辑距离
	
	public EntrySimilar(int ID,String word,String phoneticSymbol,String explation,int ed)
	{
		super(ID,word,phoneticSymbol,explation);
		this.editDistance=ed;
	}
	
	public void setEditDistance(int ed){
		this.editDistance=ed;
	}
	
	public int getEditDistance(){
		return this.editDistance;
	}
	
	@Override
	public int compareTo(EntrySimilar o){
		if (getEditDistance() > o.getEditDistance())
			return 1;
		else if (getEditDistance() < o.getEditDistance())
			return -1;
		else 
			return 0;
	}
}