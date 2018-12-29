import java.util.ArrayList;

public class Tuple {
    private ArrayList<Object> contents = new ArrayList();

    public ArrayList<Object> getContents() {
        return contents;
    }

    public Tuple(){
    }

    public void addContent(Object s){
        contents.add(s);
    }


}
