package src.scoringsystem.network.packages;

public class JudgeResetPackage extends DataPackage
{
	public JudgeResetPackage(){
		super();
	}
	public JudgeResetPackage(boolean Reset)
	{
		super();
		this.setDataPackageType(DataPackageType.JudgeReset);
		this.addBaseObject("reset", Reset);
	}
	@Override
    public void run()
    {
	    // TODO Auto-generated method stub
	    
    }
}
