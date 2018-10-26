import java.text.NumberFormat;
import java.util.ArrayList;
import db.Database;
import db.Tuple;

public class CheckStudent {
    public static void main(String[] args) throws Exception {
    	Database db = new Database();
    	String name = args[0];
    	ArrayList<Tuple> queryResult = new ArrayList<Tuple>();
    	NumberFormat format = NumberFormat.getNumberInstance();
    	
        for(Tuple temp : db.getData()) {
        	if(temp.getName().equals(name))
        		queryResult.add(temp);
        }
        if(queryResult.size() > 0) {
	        System.out.print(queryResult.size() + " records has been found!\n");
	        System.out.print("--------------------\n");
	        int total = 0;
	        double average;
	        int index = 0;
	        for(Tuple temp : queryResult) {
	        	System.out.println(++index +" "+ temp.getCourse() + " " + temp.getScore());
	        	total += temp.getScore();
	        }
	        
	        average = total * 1.0 / queryResult.size();
	        System.out.print("--------------------\n");
	        System.out.println("Total mark is: " + total);
	        format.setMaximumFractionDigits(2);
	        System.out.println("Average Mark is: " + format.format(average) + "\n");
        }
        else {
        	System.out.println("No record is found!");
        }
        
        db.closeDatabase();
    }
    
}
