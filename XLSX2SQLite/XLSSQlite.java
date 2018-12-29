/*
* This project implements a simple operation of
* inserting data from a Excel File into a sqlite
* database. This class is the main method.
*/
public class XLSSQlite {

    public static void main(String[] args){
        Token token = new Token(args);
        SQLiteJDBC sqLiteJDBC = new SQLiteJDBC(token);

        sqLiteJDBC.connectDB();

        XLS file = new XLS(token);
        file.readExcel();

        Table table = file.getTable();

        sqLiteJDBC.createTable(table);
        sqLiteJDBC.insertRecord(table);
        sqLiteJDBC.closeConnection();
    }
}
