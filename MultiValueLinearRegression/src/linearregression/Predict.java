package linearregression;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Predict
{
	int num_Feature;
	int num_TrainingSet;
	private double THETA0 = 0.699937;
	private double THETA1 = 0.135227;
	private double THETA2 = -0.218162;
	private double THETA3 = 0.020956;
	Team[] team;
	//Gradient Descent를 진행후 cost값이 가장 낮았던 theta값들을 변수에 지정해준다.
	
	public Predict() throws FileNotFoundException
	{
		initFile();
	}
	
	public void initFile() throws FileNotFoundException 
	{
		File file = new File("src/linearregression/predict.txt");
		Scanner scanner = new Scanner(file);

		num_Feature = scanner.nextInt();
		num_TrainingSet = scanner.nextInt();
		team = new Team[num_TrainingSet];
		//input 데이터들의 predict값을 변수에 저장한다.
		for (int i = 0; i < num_TrainingSet; i++) 
		{
			double x1 = scanner.nextDouble();
			double x2 = scanner.nextDouble();
			double x3 = scanner.nextDouble();
			String name = scanner.nextLine();
			team[i] = new Team();
			team[i].name = name;
			team[i].point =(THETA0 + THETA1*x1 + THETA2*x2 + THETA3*x3);
		}
	}
	//predict값이 높은순으로 정렬을 한후 결과를 출력해준다.
	public void printResult()
	{
		Arrays.sort(team);
		for(int i=0;i<num_TrainingSet;i++)
		{
			System.out.printf("\t%d.\t%.5f\t%s\n",i+1,team[i].point,team[i].name);
		}
	}
}
