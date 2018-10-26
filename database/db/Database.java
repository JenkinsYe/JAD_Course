package db;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {
    private File file;
    private ArrayList<Tuple> dataList = new ArrayList<Tuple>();
    private Scanner input;
    private PrintWriter output;
    
    
    /**
     * @param filepath construct Database with given filepath
     * @throws FileNotFoundException
     */
    public Database(String filepath) throws FileNotFoundException{
        file = new File(filepath);
        input = new Scanner(file);
        
        while(input.hasNext()) {
        	String data = input.nextLine();
        	Tuple tuple = new Tuple(data);
        	dataList.add(tuple);
        }
        
    }
    
    
    /**default constructors
     * @throws FileNotFoundException
     */
    public Database() throws FileNotFoundException {
    	file = new File("a.csv");
    	input = new Scanner(file);
    	
    	while(input.hasNext()) {
        	String data = input.nextLine();
        	Tuple tuple = new Tuple(data);
        	dataList.add(tuple);
        }
    }
    
    public void closeDatabase() {
    	input.close();
    }
    
    public void printDatabase() {
    	System.out.println("-------------------------------------------");
    	System.out.println("The data stored in Database is shown bellow");
     	System.out.println("-------------------------------------------");
    	for(Tuple t : dataList) {
    		t.printTuple();
    	}
    }
    
    /**
     * @throws Exception
     * this method is used to write back the information in the buffer to local file
     */
    public void writeBack() throws Exception {
    	output = new PrintWriter(file);
    	
    	for(Tuple tuple : dataList) {
    		output.println(tuple.toString());
    	}
    	
    	output.close();
    }
    
    public void addTuple(Tuple newtuple) {
    	dataList.add(newtuple);
    }
    
    /**
     * @param newtuple
     * @return
     * this method return true if the tuple record has already in the database
     */
    public boolean hasTuple(Tuple newtuple) {
    	
    	for(Tuple temp : dataList) {
    		if(temp.sameName(newtuple) && temp.sameCourse(newtuple)) {
    			temp.setScore(newtuple.getScore());
    			return true;
    		}
    	}
    	return false;
    }
    
    public ArrayList<Tuple> getData(){
    	return this.dataList;
    }
}
