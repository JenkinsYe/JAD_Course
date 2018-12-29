import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
*   This file implements the interaction between
*   program to the database
* */
public class SQLiteJDBC {
    private Token token;
    private Connection c;

    public SQLiteJDBC(Token token){
        this.token = token;
        c = null;
    }

    public void connectDB(){
        c = null;
        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + this.token.getDbName());
        }
        catch (Exception e){
            System.err.println(e.getClass().getName()+":"+e.getMessage());
            System.exit(0);
        }
        System.out.println("SQLite Connect Successfully!\n");
    }

    public void createTable(Table table){
        ArrayList<Attribute> attributes = table.getAttr();
        Statement stm =null;
        try {
            stm = c.createStatement();
            StringBuffer sql = new StringBuffer();
            sql.append("CREATE TABLE " + table.getTableName() + "(\nPK INT PRIMARY KEY NOT NULL, \n");
            for(Attribute attr : attributes){
                if(attr.getType() == Type.VARCHAR){
                    sql.append(attr.getName() + " " + attr.getType() + "("
                            + attr.getCharLength() + "), \n");
                }else{
                    sql.append(attr.getName() + " " + attr.getType() + ", \n");
                }
            }

            sql.replace(sql.length()-3, sql.length() - 1, ")");
            stm.execute(sql.toString());

            System.out.println("Table has been created!");
            System.out.println("Table Structure:");
            System.out.println(sql);
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertRecord(Table table){
        ArrayList<Tuple> records = table.getRecord();
        StringBuffer sb = new StringBuffer();
        ArrayList<Attribute> attributes = table.getAttr();
        sb.append(" (PK,");
        for(Attribute attribute : attributes){
            sb.append(attribute.getName() + ",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(") ");

        Statement stm = null;
        try {
            stm = c.createStatement();
            int pk = 1;
            for(Tuple tuple : records){
                StringBuffer sql = new StringBuffer();
                sql.append("Insert INTO " + table.getTableName() + sb
                           + "VALUES (" + pk++ + ", ");
                for(Object content : tuple.getContents()){
                    if(content instanceof String)
                        sql.append("\"" + content.toString() + "\"" + ", ");
                    else sql.append(content.toString() + ", ");
                }
                sql.replace(sql.length()-2, sql.length()-1, ");");
                stm.execute(sql.toString());
            }
            System.out.println("All Records has been inserted, totally " + records.size() + " Records.");
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        try {
            c.close();
            System.out.println("SQLite Connection closed!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
