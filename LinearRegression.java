
public class LinearRegression {
	
	private double[] weights;
	private DataSet trainingData;
	private double lambda = 0;
	private double error;

	public LinearRegression(DataSet trainingData){
		this.trainingData = trainingData;
		weights = new double[trainingData.getRecords().get(0).getValues().length + 1];
	}
	
	public LinearRegression(DataSet trainingData, double lambda){
		this.trainingData = trainingData;
		this.lambda = lambda;
		weights = new double[trainingData.getRecords().get(0).getValues().length + 1];
	}

	//solve with cross validation and select best model parameter
	public static LinearRegression solveWithVFoldCrossValidation(DataSet trainingData, int folds, double[] lambda){
		LinearRegression chosen;
		int validationSetSize = trainingData.getRecords().size() / folds;
		
		double[] trainingError = new double[lambda.length];
		double[] validationError = new double[lambda.length];
		
		//for each model parameter change
		for(int model = 0; model < validationError.length; model++){
			DataSet[] dataAndValidationSets;
			
			//for each fold in the training set
			for(int fold = 0; fold < folds; fold++){
				
				//seperate dataset into training and validation sets based on fold
				int start = (validationSetSize * fold);
				int end = ((validationSetSize * (fold + 1)) - 1);
				dataAndValidationSets = trainingData.divideForValidation(start, end);
				
				//create a linear regression model and train it on the training set
				LinearRegression candidate = new LinearRegression(dataAndValidationSets[0], lambda[model]);
				candidate.solveWithRegularization(lambda[model]);
				
				//calculate and sum the error from the training and validation sets
				trainingError[model] += LinearRegression.getMeanSquaredError(candidate, dataAndValidationSets[0]);
				validationError[model] += LinearRegression.getMeanSquaredError(candidate, dataAndValidationSets[1]);
			}
			trainingError[model] /= lambda.length;
			validationError[model] /= lambda.length;
		}
		
		//find the model that generated the least error
		int leastError = 0;
		for(int i = 0; i < validationError.length; i++){
			if(validationError[i] < validationError[leastError]) leastError = i;
			System.out.println("\t\tLambda( " + lambda[i] + " ) : In-Sample Error is ( " + trainingError[i] + " ) : Validation Error is ( " + validationError[i] + " )");
		}
		
		chosen = new LinearRegression(trainingData, lambda[leastError]);
		chosen.solveWithRegularization(lambda[leastError]);
		
		return chosen;
	}
	
	//standard linear regression
	//W = Inverse(Transpose(X) * X) * Transpose(X) * Y
	public void solve(){
		Matrix data = trainingData.dataAsMatrix();
		Matrix classes = trainingData.classAsMatrix();
		
		Matrix result = Matrix.multiply(Matrix.transpose(data), data);
		result = Matrix.multiply(Matrix.inverse2(result), Matrix.transpose(data));
		result = Matrix.multiply(result, classes);
		extractWeights(result.asArray());
		error = LinearRegression.getMeanSquaredError(this, trainingData);
	}
	
	//linear regression with regularization
	//W = Inverse(Transpose(X) * X - LAMBDA * I) * Transpose(X) * Y
	public void solveWithRegularization(double lambda){
		Matrix data = trainingData.dataAsMatrix();
		Matrix classes = trainingData.classAsMatrix();
		
		Matrix result = Matrix.multiply(Matrix.transpose(data), data);
		result = Matrix.add(result, Matrix.getIdentityMatrix(Matrix.getHeight(result), lambda));
		result = Matrix.multiply(Matrix.inverse2(result), Matrix.transpose(data));
		result = Matrix.multiply(result, classes);
		extractWeights(result.asArray());
		error = LinearRegression.getMeanSquaredError(this, trainingData);
	}
	
	public double getErrorForRegularization(DataSet dataset){
		double error = 0;
		Matrix data = dataset.dataAsMatrix();
		System.out.println(Matrix.toString(data));
		Matrix classes = dataset.classAsMatrix();
		
		Matrix result1 = Matrix.scale(Matrix.transpose(classes), 1/Matrix.getHeight(data));
		System.out.println(Matrix.toString(result1));
		Matrix h = Matrix.multiply(Matrix.transpose(data), data);
		h = Matrix.add(h, Matrix.getIdentityMatrix(Matrix.getHeight(h), lambda));
		h = Matrix.multiply(data, Matrix.inverse2(h));
		h = Matrix.multiply(h, Matrix.transpose(data));
		System.out.println(Matrix.toString(h));
		
		Matrix result2 = Matrix.subtract(Matrix.getIdentityMatrix(Matrix.getHeight(h)), h);
		result2 = Matrix.multiply(result2, result2);
		System.out.println(Matrix.toString(result2));
		result1 = Matrix.multiply(result1, result2);
		result1 = Matrix.multiply(result1, classes);
		System.out.println(Matrix.toString(result1));
		
		return error;
	}
	
	public static double getMeanSquaredError(LinearRegression model, DataSet testData){
		double[][] data = testData.dataAsArray();
		double[][] result = testData.classAsArray();
		double sum = 0;
		for(int row = 0; row < data.length; row++){
			double y = 0;
			for(int col = 0; col < data[row].length; col++){
				y += model.weights[col] * data[row][col];
			}
			sum += Math.pow(result[row][0] - y, 2);
		}
		return (sum/data.length);
	}
	
	public String printEquation(){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("y = ");
		for(int i = 0; i < weights.length; i++){
			if(i == 0) stringBuilder.append(weights[i] + " + ");
			else if(i == 1 && weights.length > 2) stringBuilder.append(weights[i] + "x + ");
			else if(i == 1 && weights.length == 2) stringBuilder.append(weights[i] + "x");
			else if(i == weights.length - 1) stringBuilder.append(weights[i] + "x^" + i);
			else stringBuilder.append(weights[i] + "x^" + i + " + ");
		}
		return stringBuilder.toString();
	}
	
	//extract the weights from a double[][] into a double[]	
	private void extractWeights(Double[][] result){
		if(result != null){
			if(result[0].length == 1){
				weights = new double[result.length];
				for(int row = 0; row < result.length; row++){
					weights[row] = result[row][0];
				}
			}
		}
	}
	
	public double getLambda(){
		return lambda;
	}
	
	public double getError(){
		return error;
	}
}
