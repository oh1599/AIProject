package linearregression;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LinearRegression 
{
	private final double LEARNING_RATE = 0.00018;

	private int num_TrainingSet;
	private int num_Feature;

	private double[][] x;
	private double[] theta;
	private double[] y;

	private Scanner scanner;

	public LinearRegression() throws FileNotFoundException 
	{
		initialize();
	}

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

	private void initialize() throws FileNotFoundException
	{
		initFile();
		initTheta();
	}

	public void initTheta() 
	{
		theta = new double[num_Feature + 1];
		for (int i = 0; i <= num_Feature; i++)
		{
			theta[i] = 0;
		}
	}

	public double getHypothesis(int nth) 
	{
		double sum = 0;
		for (int i = 0; i <= num_Feature; i++) 
		{
			sum += (theta[i] * x[nth][i]);
		}
		return sum;
	}

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
			System.out.printf("%d ��° GradientDescent , Cost %f\n", iter, getCost());
			printResult();
		} while (prevCost > nextCost);
	}

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