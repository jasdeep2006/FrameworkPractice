package rahulshettyacademy.data;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class dataExcel {
    DataFormatter formatter = new DataFormatter();

    @DataProvider
    public Object[][] getData() throws IOException {
        FileInputStream file = new FileInputStream("D://SeleniumFramework//excel//shettySite.xlsx");

        XSSFWorkbook workbook =  new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);

        int rowCount = sheet.getPhysicalNumberOfRows();

        XSSFRow row = sheet.getRow(0);
        int columnCount = row.getLastCellNum();

        Object data [][] = new Object[rowCount-1][columnCount];


        for (int i = 0;i<rowCount-1;i++)
        {
            row = sheet.getRow(i+1);
            for (int j = 0;j<columnCount;j++)
            {
                XSSFCell cell = row.getCell(j);
                data[i][j]  = formatter.formatCellValue(cell);
            }

        }

        return data;

    }

    @Test(dataProvider = "getData")
    public void print(String id,String password,String prdtName)
    {
        System.out.println(id+" "+password+" "+prdtName);
    }


}

