package src.scoringsystem.network.packages;

public class DiffJudgePackage extends DataPackage
{
	public DiffJudgePackage(){
		super();
	}
	public DiffJudgePackage(int ID, double score)
	{
		super();
		this.setDataPackageType(DataPackageType.DiffJudge);
		this.addBaseObject("id", ID);
		this.addBaseObject("score", score);
	}
	@Override
    public void run()
    {
	    // TODO Auto-generated method stub
	    
    }
}
