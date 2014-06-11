package src.scoringsystem.network.packages;

public class JudgeScorePackage extends DataPackage
{
	public JudgeScorePackage(){
		super();
	}
	public JudgeScorePackage(int ID, double score)
	{
		super();
		this.setDataPackageType(DataPackageType.JudgeScore);
		this.addBaseObject("id", ID);
		this.addBaseObject("score", score);
	}
	@Override
    public void run()
    {
	    // TODO Auto-generated method stub
	    
    }
}
