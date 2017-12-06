package linearregression;

public class Team implements Comparable<Team>
{
	String name;
	double point;
	
	@Override
	public int compareTo(Team t)
	{
		if (this.point < t.point)
		{
			return 1;
		}
		else if (this.point == t.point)
		{
			return 0;
		}
		else
		{
			return -1;
		}
	}
}
