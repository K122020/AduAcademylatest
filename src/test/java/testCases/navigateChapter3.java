package testCases;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import generics.ExcelReader;

import generics.ExcelLibs;
import base.CommonLibs;
import generics.Constants;
import pages.LoginPage;
import pages.NextPage2;
import pages.SignOutPage;

public class navigateChapter3 extends CommonLibs {
	ExcelLibs exl;
	String userSheet = null;
	XSSFSheet shtobj;

	int i = 1;
	//LoginPage loginPage;
	static String DelMthd = null;
	Hashtable<String, String> data = new Hashtable<String, String>();
	String filepath = Constants.EXCEL_PATH;
	public static int flag=1;
	//public int rowValue=1;
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\Testdata.xlsx");

	@BeforeTest()
	public void launchBrowser() throws InterruptedException {

		getDriverObject(Constants.browser, Constants.URL);

	}

	@BeforeMethod	
	public void preTestCase ()	{
		

		Reporter.log("**********Started Execution of " + "**********", true);
	}

	@Test (priority=1, alwaysRun=true, description = "Have loggedIn") 
	public void doLogin() throws InterruptedException
	{
		shtobj = excel.getSheetObject(filepath, "registeredUser");		
		data.put("emailId", excel.getCellData(shtobj, 1, 0));
		data.put("password", excel.getCellData(shtobj, 1, 1));
		System.out.println(data);
		//loginPage.Lms_Login(data);
		LoginPage login = new LoginPage();
		login.Lms_Login(data);
		
	}

	@Test (priority=2, alwaysRun=true, description = "Start USMO") 
	public void navNextPage2() throws InterruptedException
	{
		//int j=i-1;
		shtobj = excel.getSheetObject(filepath, "USMO");
		int rowCount=shtobj.getLastRowNum()-shtobj.getFirstRowNum();
		System.out.println("rowCount : ---->"+rowCount);
		for(int rowValue=1; rowValue <= rowCount; rowValue++) {

			data.put("actId", excel.getCellData(shtobj, rowValue, 0));
			//data.put("usmo", excel.getCellData(shtobj, rowValue, 1));			
			NextPage2 nxtpg = new NextPage2();
			//nxtpg.Next_Page2(data);
			nxtpg.Next_Page3(data);
			String temp;

			if (NextPage2.stat==true) {
				temp = "Done";
			} else {
				temp = "Already Done";				
				
			}
			//rowValue++;
			//excel.setCellData("USMO", "Status", rowValue+1, temp);
			excel.setCellData("USMO", "publish_Status", rowValue+1, temp);
		}

	}

	
	@AfterMethod()
	public void postTestcase()
	{
		flag++;
	}

	/*
	 * @AfterTest() public void doQuit() { driver.quit(); }
	 */


}
