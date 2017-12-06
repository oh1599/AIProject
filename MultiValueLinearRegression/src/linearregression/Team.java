package linearregression;


//������ ���� �౸���� ��Ÿ���� Ŭ����
public class Team implements Comparable<Team>
{
	String name;
	double point;//���� ��� ���� ��հ����� y���� �ش��Ѵ�.
	
	//point���� �������� sorting�� �������ش�.
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
