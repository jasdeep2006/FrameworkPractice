package rahulshettyacademy.Excel;

import java.io.IOException;

public class excelDataProvider {
    public void testData(String excelPath,String sheetName) throws IOException {

        ExcelUtils excelUtils =  new ExcelUtils(excelPath,sheetName);

        int rowCount = excelUtils.getRowCount();
        int colCount = excelUtils.getColCount();

    }
}
