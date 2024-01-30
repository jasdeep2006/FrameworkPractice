package rahulshettyacademy.Tests;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.testng.Assert;

import org.testng.ITestResult;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageObject.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class submitOrderTest extends BaseTest {

    @Test(dataProvider = "getExcelData")
    public void submitOrder(String id,String password,String productName) throws IOException {


        ProductCatalouge productCatalouge = landingPage.LoginApplication(id, password);
        List<WebElement> products = productCatalouge.getProductList();
        productCatalouge.addProductToCart(productName);
        OrderConfirmation orderConfirmation = productCatalouge.goToCart();
        Boolean match = orderConfirmation.productCheck(productName);
        Assert.assertTrue(match);
        CheckOutPage checkOutPage = orderConfirmation.goToCheckout();
        ConfirmationPage confirmationPage = checkOutPage.placeOrder();
        String orderConfirmationMessage = confirmationPage.verifyorderConfirmationMessage();
        Assert.assertTrue(orderConfirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        System.out.println("Success");
        driver.quit();

    }


    DataFormatter formatter = new DataFormatter();

    @DataProvider
    public Object[][] getExcelData() throws IOException {
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


//    @Test(dataProvider = "getExcelData",retryAnalyzer = Retry.class)
//    public void submitOrder(HashMap<String,String> input) throws IOException {
//
//
//        ProductCatalouge productCatalouge = landingPage.LoginApplication(input.get("id"), input.get("password"));
//        List<WebElement> products = productCatalouge.getProductList();
//        productCatalouge.addProductToCart(input.get("productName"));
//        OrderConfirmation orderConfirmation = productCatalouge.goToCart();
//        Boolean match = orderConfirmation.productCheck(input.get("productName"));
//        Assert.assertTrue(match);
//        CheckOutPage checkOutPage = orderConfirmation.goToCheckout();
//        ConfirmationPage confirmationPage = checkOutPage.placeOrder();
//        String orderConfirmationMessage = confirmationPage.verifyorderConfirmationMessage();
//        Assert.assertTrue(orderConfirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
//        System.out.println("Success");
//        driver.quit();
//
//    }
//
//    @DataProvider
//    public Object [] [] getData() throws IOException {
//        List<HashMap<String,String>> data = getJSONDataToMap();
////        HashMap<String,String> map = new HashMap<String,String>();
////        map.put("id","jasdeep123@gmail.com");
////        map.put("password","Gekko@06");
////        map.put("productName","ZARA COAT 3");
////
////        HashMap<String,String> map1 = new HashMap<String,String>();
////        map1.put("id","anshika@gmail.com");
////        map1.put("password","Iamking@000");
////        map1.put("productName","ADIDAS ORIGINAL");
//
//        return new Object[][]{{data.get(0)},{data.get(1)}};
//    }


}

