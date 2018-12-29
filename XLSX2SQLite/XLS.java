import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.ArrayList;


/*
*  Read the Excel File, and it provide interface for
*  get the contents of the file.
* */
public class XLS {
    private Token token;

    private File file;
    XSSFSheet targetSheet;
    XSSFRow headRow;
    public XLS(Token token){
        this.token = token;
    }

    public void readExcel() {
        file = new File(token.getFilename());
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
            int sizeOfSheet = workbook.getNumberOfSheets();
            // find the target sheet according to the token
            if(token.getSheetName() == null){
                token.setSheetName(workbook.getSheetName(0));
                targetSheet = workbook.getSheetAt(0);
            } else{
                for(int index = 0; index < sizeOfSheet; index++){
                    if(workbook.getSheetAt(index).getSheetName().equals(token.getSheetName())){
                        targetSheet = workbook.getSheetAt(index);
                        break;
                    }
                }
            }
            if(token.getTableName() == null){
                token.setTableName(targetSheet.getSheetName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Table getTable(){
        headRow = targetSheet.getRow(0);
        int rowNum = targetSheet.getLastRowNum();
        int colNum = headRow.getLastCellNum();

        Table table = new Table(token.getTableName());
        // create all attribute
        for(int index = 0; index < colNum; index++){
            Attribute attribute = new Attribute(headRow.getCell(index).toString());
            table.addAttribute(attribute);
        }

        // create all tuples
        ArrayList<Attribute> attributes = table.getAttr();
        for(int rowIndex = 1; rowIndex <= rowNum; rowIndex++){
            Tuple tuple = new Tuple();
            XSSFRow row = targetSheet.getRow(rowIndex);
            for(int cellIndex = 0; cellIndex < colNum; cellIndex++){
                XSSFCell cell = row.getCell(cellIndex);
                Type t = attributes.get(cellIndex).getType();
                if(t != Type.VARCHAR) {
                    if (cell.getCellType() == CellType.NUMERIC) {
                        long intVal = Math.round(cell.getNumericCellValue());
                        if (Double.parseDouble(intVal + ".0") == cell.getNumericCellValue())
                            t = Type.INT;
                        else t = Type.REAL;
                    } else if(cell.getCellType() == CellType.STRING){
                        t = Type.VARCHAR;
                        int length = cell.toString().length();
                        attributes.get(cellIndex).setCharLength(length);
                    }
                    attributes.get(cellIndex).setType(t);
                }else{
                    int length = cell.toString().length();
                    if(length > attributes.get(cellIndex).getCharLength())
                        attributes.get(cellIndex).setCharLength(length);
                }
                if( t == Type.VARCHAR) tuple.addContent(cell.toString());
                else  if( t == Type.REAL) tuple.addContent(Double.parseDouble(cell.toString()));
                else tuple.addContent(Math.round(cell.getNumericCellValue()));
            }
            table.addTuple(tuple);
        }

        return table;
    }

}
