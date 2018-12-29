public class Attribute {
    private String name;
    private Type type =null;
    private int charLength;

    public Attribute(String name){
        this.name = name;
    }

    public Type getType(){
        return this.type;
    }

    public int getCharLength(){
        return this.charLength;
    }

    public String getName(){
        return this.name;
    }

    public void setType(Type type){
        this.type = type;
    }

    public void setCharLength(int length){
        this.charLength = length;
    }
}


