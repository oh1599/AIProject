package linearregression;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LinearRegression 
{
	private final double LEARNING_RATE = 0.00018;

	private int num_TrainingSet;//트레이닝세트의 개수
	private int num_Feature;//특징의 수

	private double[][] x;
	private double[] theta;
	private double[] y;

	private Scanner scanner;

	public LinearRegression() throws FileNotFoundException 
	{
		initialize();//생성자에 파일을 읽어들이는 함수 호출
	}
	
	//파일을 읽은 후에 피처의 수와 트레이닝세트의 수를 변수에 저장해주고
	//파일에서 읽어들인 변수값들을 저장한다.
	public void initFile() throws FileNotFoundException 
	{
		File file = new File("src/linearregression/data.txt");
		scanner = new Scanner(file);

		num_Feature = scanner.nextInt();
		num_TrainingSet = scanner.nextInt();

		x = new double[num_TrainingSet][num_Feature + 1];
		y = new double[num_TrainingSet];

		for (int i = 0; i < num_TrainingSet; i++) 
		{
			for (int j = 0; j <= num_Feature; j++) 
			{
				if (j == 0) 
				{
					x[i][j] = 1;
				} 
				else if (j == num_Feature)
				{
					x[i][j] = scanner.nextDouble();
					y[i] = scanner.nextDouble();
				} else {
					x[i][j] = scanner.nextDouble();
				}
			}
		}
	}

	//생성자에서 호출하는 함수로
	//파일을 읽어들이고 세타값으 초기화한다.
	private void initialize() throws FileNotFoundException
	{
		initFile();
		initTheta();
	}

	//세타값들을 초기화해준다.
	public void initTheta() 
	{
		theta = new double[num_Feature + 1];
		for (int i = 0; i <= num_Feature; i++)
		{
			theta[i] = 0;
		}
	}
	
	//가설값을 return 해주는 함수이다 h
	public double getHypothesis(int nth) 
	{
		double sum = 0;
		for (int i = 0; i <= num_Feature; i++) 
		{
			sum += (theta[i] * x[nth][i]);
		}
		return sum;
	}
	
	//cost값을 계산하여 return 해주는 함수
	public double getCost() 
	{
		double sum = 0;
		for (int i = 0; i < num_TrainingSet; i++)
		{
			sum = sum + ((getHypothesis(i) - y[i]) * (getHypothesis(i) - y[i]));
		}
		sum = sum / (2 * num_TrainingSet);
		return sum;
	}

	//Gradient Descent를 하기위해서는 각 theta별로 편미분을 하여야한다.
	//해당 theta 의 편미분값을 return 해준다
	public double getPartialDerivative(int nthFeature) 
	{
		double sum = 0;
		for (int i = 0; i < num_TrainingSet; i++) 
		{
			sum = sum + ((getHypothesis(i) - y[i]) * x[i][nthFeature]);
		}
		sum = sum / num_TrainingSet;
		return sum;
	}

	//Gradient Descent를 해주고 난 후에 theta 값을 갱신해주는 함수
	public void updateTheta() 
	{
		double[] temp = new double[num_Feature + 1];
		for (int i = 0; i <= num_Feature; i++) 
		{
			temp[i] = theta[i] - LEARNING_RATE * getPartialDerivative(i);
		}
		for (int i = 0; i <= num_Feature; i++) 
		{
			theta[i] = temp[i];
		}
	}

	//Gradient Descent를 수행해주는 함수 updateTheta를 해주기전과 해준 후의
	//cost 값을 비교하여 후의 cost값이 더 높을때까지 Gradient Descent를 수행한다.
	public void gradientDescent() 
	{
		int iter = 0;
		double prevCost;
		double nextCost;
		do 
		{
			iter++;
			prevCost = getCost();
			updateTheta();
			nextCost = getCost();
			System.out.printf("%d 번째 GradientDescent , Cost %f\n", iter, getCost());
			printResult();
		} while (prevCost > nextCost);
	}

	//Gradient Descent를 수행해주고 난 후의 theta값들을 출력해주는 함수
	public void printResult() 
	{
		for (int i = 0; i <= num_Feature; i++) 
		{
			if (i == num_Feature)
				System.out.printf("Theta%d = %f\n", i, theta[i]);
			else
				System.out.printf("Theta%d = %f,", i, theta[i]);
		}
	}
}