
public class Main{

	
	public static void main(String[] args) {
		
		double[] lambda = {.1, 1, 10, 100};
		int numSamples = 12;
		
		LinearRegression linearRegressionWithoutRegularization;
		LinearRegression linearRegression;
		
		DataSet trainingData = new DataSet("TRAINING");
		trainingData.generateRandom(numSamples);
		System.out.println(trainingData.toString());
		
		DataSet testData = new DataSet("TESTING");
		testData.generateRandom(numSamples);
		System.out.println(testData.toString());
		
		/*
		 * Linear Regression without Regularization
		 */
		System.out.println("Linear Regression without Regularization");
		linearRegressionWithoutRegularization = new LinearRegression(trainingData);
		linearRegressionWithoutRegularization.solve();
		System.out.println(linearRegressionWithoutRegularization.printEquation());
		System.out.println("MSE(train) = " + linearRegressionWithoutRegularization.getError());
		System.out.println("MSE(test) = " + LinearRegression.getMeanSquaredError(linearRegressionWithoutRegularization, testData) + "\n");
		
		/*
		 * Linear Regression with regularization and 3 fold cross validation
		 */
		linearRegression = LinearRegression.solveWithVFoldCrossValidation(trainingData, 3, lambda);
		System.out.println("\nLinear Regression with Regularization. Lambda is " + linearRegression.getLambda());
		System.out.println(linearRegression.printEquation());
		System.out.println("MSE(train) = " + linearRegression.getError());
		System.out.println("MSE(test) = " + LinearRegression.getMeanSquaredError(linearRegression, testData) + "\n");
		
//		/*
//		 * Linear Regression with regularization
//		 */
//		System.out.println("\nLinear Regression with Regularization. Lambda is " + lambda[0]);
//		linearRegression = new LinearRegression(trainingData);
//		linearRegression.solveWithRegularization(lambda[0]);
//		System.out.println(linearRegression.printEquation());
//		System.out.println("MSE(train) = " + linearRegression.getError());
//		
//		System.out.println("\nLinear Regression with Regularization. Lambda is " + lambda[1]);
//		linearRegression = new LinearRegression(trainingData);
//		linearRegression.solveWithRegularization(lambda[1]);
//		System.out.println(linearRegression.printEquation());
//		System.out.println("MSE(train) = " + linearRegression.getError());
//		
//		System.out.println("\nLinear Regression with Regularization. Lambda is " + lambda[2]);
//		linearRegression = new LinearRegression(trainingData);
//		linearRegression.solveWithRegularization(lambda[2]);
//		System.out.println(linearRegression.printEquation());
//		System.out.println("MSE(train) = " + linearRegression.getError());
//		
//		System.out.println("\nLinear Regression with Regularization. Lambda is " + lambda[3]);
//		linearRegression = new LinearRegression(trainingData);
//		linearRegression.solveWithRegularization(lambda[3]);
//		System.out.println(linearRegression.printEquation());
//		System.out.println("MSE(train) = " + linearRegression.getError());
//		
//		System.out.println("\nLinear Regression with Regularization. Lambda is " + lambda[4]);
//		linearRegression = new LinearRegression(trainingData);
//		linearRegression.solveWithRegularization(lambda[4]);
//		System.out.println(linearRegression.printEquation());
//		System.out.println("MSE(train) = " + linearRegression.getError());
//		System.out.println("MSE(test) = " + LinearRegression.getMeanSquaredError(linearRegression1, testData) + "\n");
//		
//		
//		/*
//		 * Linear Regression with regularization
//		 */
//		System.out.println("Linear Regression with Regularization. Lambda is " + lambda[1]);
//		linearRegression2 = new LinearRegression(trainingData);
//		linearRegression2.trainWithRegularization(lambda[1]);
//		System.out.println(linearRegression2.printEquation());
//		System.out.println("MSE(train) = " + linearRegression2.getError());
//		System.out.println("MSE(test) = " + LinearRegression.getMeanSquaredError(linearRegression2, testData) + "\n");
		
//		linearRegression2.solveWithRegularization(lambda[1]);
//		System.out.println(linearRegression2.printEquation());
//		System.out.println("MSE(train) = " + linearRegression2.getError());
//		System.out.println("MSE(test) = " + LinearRegression.getMeanSquaredError(linearRegression2, testData) + "\n");
		
//		/*
//		 * Linear Regression with regularization
//		 */
//		System.out.println("Linear Regression with Regularization. Lambda is " + lambda[2]);
//		linearRegression3 = new LinearRegression(trainingData);
//		linearRegression3.trainWithRegularization(lambda[2]);
//		System.out.println(linearRegression3.printEquation());
//		System.out.println("MSE(train) = " + linearRegression3.getError());
//		System.out.println("MSE(test) = " + LinearRegression.getMeanSquaredError(linearRegression3, testData) + "\n");
//		
//		/*
//		 * Linear Regression with regularization
//		 */
//		System.out.println("Linear Regression with Regularization. Lambda is " + lambda[3]);
//		linearRegression4 = new LinearRegression(trainingData);
//		linearRegression4.trainWithRegularization(lambda[3]);
//		System.out.println(linearRegression4.printEquation());
//		System.out.println("MSE(train) = " + linearRegression4.getError());
//		System.out.println("MSE(test) = " + LinearRegression.getMeanSquaredError(linearRegression4, testData) + "\n");
		
		
//		trainingData = new DataSet("TRAINING");
//		trainingData.generateSudoRandom();
//		System.out.println(trainingData.toString());
//		
//		LinearRegression linearRegressionTest = new LinearRegression(trainingData, trainingData);
//		linearRegressionTest.largeMatrixTest();
	}
}
