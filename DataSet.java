import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class DataSet implements Iterable<Record>{
	
	private String name;
	private FileReader dataFile;
	private String[] attributeNames; 
	private List<Record> records;
	
	public DataSet(String name, FileReader dataFile){
		this.name = name;
		this.dataFile = dataFile;
		records = new ArrayList<>();
	}
	
	public DataSet(String name){
		this.name = name;
		records = new ArrayList<>();
	}
	
	/*
	 * Returns 2 DataSets, set[0] is the training set set[1] is the validation set
	 */
	public DataSet[] divideForValidation(int validationSetStart, int validationSetEnd){
		DataSet[] sets = new DataSet[2];
		for(int i = 0; i < sets.length; i++){
			sets[i] = new DataSet(name);
			sets[i].attributeNames = attributeNames;
		}
		
		for(int i = 0; i < records.size(); i++){
			if(i >= validationSetStart && i <= validationSetEnd){ 
				sets[1].records.add(records.get(i));  //add to validation set
			}
			else{
				sets[0].records.add(records.get(i));  //add to training set
			}
		}
		
		return sets;
	}
	
	public void generateRandom(int numberRecords){
		Random random = new Random();
		for(int i = 0; i < numberRecords; i++){
			attributeNames = new String[2];
			attributeNames[0] = "X";
			attributeNames[1] = "Y";
			double[] values = new double[2];
			values[0] = random.nextInt(13) - 2;
			values[1] = (values[0] * values[0]) + 10;
			records.add(new Record(this, i, values));
		}
	}
		
	public void generateSudoRandom(){
		int[] x = {6, 0, 7, 0, 4, 0};
		for(int i = 0; i < x.length; i++){
			attributeNames = new String[2];
			attributeNames[0] = "X";
			attributeNames[1] = "Y";
			double[] values = new double[2];
			values[0] = x[i];
			values[1] = (values[0] * values[0]) + 10;
			records.add(new Record(this, i, values));
		}
	}
	
	public Matrix dataAsMatrix(){
		if(records == null) return null;
		Matrix matrix = new Matrix(records.size(), records.get(0).getValues().length + 1);
		for(int i = 0; i < Matrix.getHeight(matrix); i++){
			for(int j = 0; j < Matrix.getWidth(matrix); j++){
				if(j == 0) Matrix.setCell(matrix, i, j, 1);
				else Matrix.setCell(matrix, i, j, records.get(i).getValues()[j-1]);
			}
		}
		return matrix;
	}
	
	public double[][] dataAsArray(){
		double[][] matrix = new double[records.size()][records.get(0).getValues().length+1];
		for(int i = 0; i < matrix.length; i++){
			for(int j = 0; j < matrix[i].length; j++){
				if(j == 0) matrix[i][j] = 1;
				else matrix[i][j] = records.get(i).getValues()[j-1];
			}
		}
		return matrix;
	}
	
	public Matrix classAsMatrix(){
		Matrix matrix = new Matrix(records.size(), 1);
		int i = 0;
		for(Record record : records){
			Matrix.setCell(matrix, i, 0, record.getClassification());
			i++;
		}
		return matrix;
	}
	
	public double[][] classAsArray(){
		double[][] matrix = new double[records.size()][1];
		int i = 0;
		for(Record record : records){
			matrix[i][0] = record.getClassification();
			i++;
		}
		return matrix;
	}
	
	public void parseCSV() throws IOException{
		String seperator = ",";
		BufferedReader bufferedReader = new BufferedReader(dataFile);
		
		attributeNames = bufferedReader.readLine().split(seperator);
		
		int recordNumber = 1;
		
		String[] values;
		String line;
		while((line = bufferedReader.readLine()) != null){
			values = line.split(seperator);
			records.add(new Record(this, recordNumber, values));
			recordNumber++;
		}
		bufferedReader.close();
	}
	
	@Override
	public Iterator<Record> iterator() {
		return records.iterator();
	}

	public String[] getAttributeNames() {
		return attributeNames;
	}

	public List<Record> getRecords() {
		return records;
	}

	public String toString(){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("--" + name + " DATA SET--\n");
		for(Record record : records){
			stringBuilder.append(record.toString());
		}
		return stringBuilder.toString();
	}
}
