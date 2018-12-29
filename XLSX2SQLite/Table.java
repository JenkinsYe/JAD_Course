import java.util.ArrayList;

public class Table {
    private String tableName;
    private ArrayList<Attribute> attr = new ArrayList();
    private ArrayList<Tuple> record = new ArrayList();
    public Table(String name){
        this.tableName = name;
    }

    public String getTableName() {
        return tableName;
    }

    public void addAttribute(Attribute newAttribute){
        attr.add(newAttribute);
    }

    public void addTuple(Tuple tuple){
        record.add(tuple);
    }

    public ArrayList<Attribute> getAttr(){
        return this.attr;
    }

    public ArrayList<Tuple> getRecord(){
        return this.record;
    }
}
