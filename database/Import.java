import java.io.FileNotFoundException;
import db.Database;

public class Import {
    public static void main(String[] args) throws FileNotFoundException {
    	String filepath = args[0];
    	
    	Database db = new Database(filepath);
    	
    	db.printDatabase();
    	db.closeDatabase();
    }
}
