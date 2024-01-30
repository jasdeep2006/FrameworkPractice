package rahulshettyacademy.Excel;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

public class ExcelUtils {

    static XSSFWorkbook workbook;
    static XSSFSheet sheet;

    public ExcelUtils(String excelPath , String sheetName) throws IOException {

        workbook = new XSSFWorkbook(excelPath);
        sheet = workbook.getSheet(sheetName);
    }
public int getRowCount() throws IOException {
    int rowCount = sheet.getPhysicalNumberOfRows();
    return rowCount;
}

public int getColCount() throws  IOException{
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
        return colCount;
}

public void getCellData(int rowNum,int colNum)
{
    String cellData = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
    System.out.println(cellData);
}

}
