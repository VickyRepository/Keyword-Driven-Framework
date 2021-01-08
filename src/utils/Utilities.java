package utils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;

import base.TestBase;

public class Utilities extends TestBase {

	/**
	 * Reusable function will be stored here.
	 * 
	 * @throws Exception
	 * 
	 */

	public static void doLogin(String userNameData, String passwordData) throws Exception {

		WebElement userName = getObject("username");
		userName.sendKeys(userNameData);
		WebElement password = getObject("password");
		password.sendKeys(passwordData);
		WebElement login = getObject("loginButton");
		login.click();
		try {
			if (webDriver.switchTo().alert() != null) {
				webDriver.switchTo().alert().accept();
			}
		} catch (Exception e) {
			e.getMessage();
		}

		if (webDriver.findElements(By.xpath("//a[contains(text(), 'Logout')]")).size() != 0) {
			isLoggedIn = true;

		} else {
			isLoggedIn = false;
		}
		/*
		 * WebElement logout = getObject("logoutButton"); if (logout.isDisplayed())
		 * isLoggedIn = true; else isLoggedIn = false;
		 */
	}

	public static boolean isSkip(String testCaseName) {

		for (int row_Num = 2; row_Num <= dataTable.getRowCount("TestCase"); row_Num++) {

			if (dataTable.getCellData("TestCase", "TestCaseID", row_Num).equalsIgnoreCase(testCaseName)) {

				if (dataTable.getCellData("TestCase", 2, row_Num).equalsIgnoreCase("y")) {

					return true;
				} else {
					return false;
				}

			}

		}
		return false;

	}

	public static Collection<String[]> getData(String sheetName) {

		dataTable = new Xls_Reader(System.getProperty("user.dir") + "\\src\\input\\TestSheet.xlsx");

		String[][] data = new String[dataTable.getRowCount(sheetName) - 1][dataTable.getColumnCount(sheetName)];

		for (int row_Num = 2; row_Num <= dataTable.getRowCount(sheetName); row_Num++) {

			for (int col_Num = 0; col_Num < dataTable.getColumnCount(sheetName); col_Num++) {

				data[row_Num - 2][col_Num] = dataTable.getCellData(sheetName, col_Num, row_Num);
			}
		}

		return Arrays.asList(data);

	}
	
	public static void getScreenshot(String fileName) throws IOException {
		
		File src = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(src, new File(System.getProperty("user.dir")+"\\src\\output\\"+fileName+".png"));
	} 

}
