package rahulshettyacademy.Excel;

import java.io.IOException;

public class excelDemo {
    public static void main(String[] args) throws IOException {
        String projectPath = System.getProperty("user.dir");

        ExcelUtils excel = new ExcelUtils(projectPath+"//ex" +
                "cel//shettySite.xlsx","testData");

        excel.getRowCount();
        excel.getCellData(1,1);
        excel.getColCount();

    }
}
