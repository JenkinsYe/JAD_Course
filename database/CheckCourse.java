import java.text.NumberFormat;
import java.util.ArrayList;
import db.Tuple;
import db.Database;


public class CheckCourse {
    public static void main(String[] args) throws Exception {
    	Database db = new Database();
    	String course = getCourseName(args);
    	ArrayList<Tuple> queryResult = new ArrayList<Tuple>();
    	NumberFormat format = NumberFormat.getNumberInstance();
    	
    	for(Tuple temp : db.getData()) {
    		if(temp.getCourse().equals(course))
    			queryResult.add(temp);
    	}
    	if(queryResult.size() > 0) {
    		System.out.print(queryResult.size() + " records has been found!\n");
	        System.out.print("--------------------\n");
    		
	        int total = 0;
	        double average;
	        int index = 0;
	        for(Tuple temp : queryResult) {
	        	System.out.println(++index +" "+ temp.getName() + " " + temp.getScore());
	        	total += temp.getScore();
	        }
	        
	        average = total * 1.0 / queryResult.size();
	        System.out.print("--------------------\n");
	        System.out.println("Number of students is: " + queryResult.size());
	        format.setMaximumFractionDigits(2);
	        System.out.println("Average Mark is: " + format.format(average) + "\n");
	        
    	}
    	else {
    		System.out.println("No record is found!");
    	}
    	
    	db.closeDatabase();
    }
    
    /**
     * @param args
     * @return
     * this method transfer the args, a String array, to the course_name, a String
     */
    private static String getCourseName(String[] args) {
    	StringBuffer s = new StringBuffer();
    	
    	for(int i = 0; i < args.length - 1; i++) {
    		s.append(args[i]);
    		s.append(" ");
    	}
    	
    	s.append(args[args.length-1]);
    	
    	return s.toString();
    }
}
