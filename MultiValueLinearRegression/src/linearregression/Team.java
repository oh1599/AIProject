package linearregression;


//각각의 프로 축구팀을 나타내는 클래스
public class Team implements Comparable<Team>
{
	String name;
	double point;//경기당 얻는 승점 평균값으로 y값에 해당한다.
	
	//point값을 기준으로 sorting을 진행해준다.
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
