/*
*   Token is used to record the general information
* */
public class Token {
    private String dbName;
    private String filename;
    private String sheetName;
    private String tableName;

    public Token(String[] parameter){
        switch (parameter.length){
            case 2 : tokenAssign(parameter[0], parameter[1], null, null); break;
            case 3 : tokenAssign(parameter[0], parameter[1], parameter[2], null); break;
            case 4 : tokenAssign(parameter[0], parameter[1], parameter[2], parameter[3]); break;
            default: System.exit(1);
        }
    }
    public void tokenAssign(String args1, String args2, String args3, String args4){
        this.dbName = args1;
        this.filename = args2;
        if(args3 != null){
            this.sheetName = args3;
            if(args4 != null)
                this.tableName = args4;
            else this.tableName = null;
        }
        else{
            // the sheet name is defined later.
            this.sheetName = null;
            String[] s = args2.split("\\.");
            // the table name is file's name in default
            this.tableName = s[0];
        }

    }

    public String getDbName(){
        return this.dbName;
    }

    public String getFilename(){
        return this.filename;
    }

    public String getTableName(){
        return this.tableName;
    }

    public String getSheetName(){
        return this.sheetName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setSheetName(String sheetName){
        this.sheetName = sheetName;
    }
}
