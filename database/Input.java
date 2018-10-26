import db.Database;
import db.Tuple;

public class Input {

	public static void main(String[] args) throws Exception {
		Database db = new Database();
        String tuplestring = Input.transferArgs(args);
        //create new tuple with args
		Tuple newtuple = new Tuple(tuplestring);
		
		//update the information in the database
        if(!db.hasTuple(newtuple)) db.addTuple(newtuple);
        
        db.writeBack();
        db.closeDatabase();
	}
	
	public static String transferArgs(String[] args) {
		String name = args[0];
        String score = args[args.length - 1];
        StringBuffer course_name = new StringBuffer();
        String data;
        
        for(int i = 1; i < args.length - 1; i++)
        {
        	course_name.append(' ');
        	course_name.append(args[i]);
        }
        
        data = name + "," + course_name.toString() + ", " + score;
        
        return data;
	}

}
