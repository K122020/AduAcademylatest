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
import pages.NextPage;
import pages.SignOutPage;

public class navigateChapter_bkup extends CommonLibs {
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
	public void preTestCase ()
	{
		//OrderNo = null;
		//String methodName = method.getName();

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
		//	try {
		//	        loginPage.Lms_Login(data);
		//	}
		//	catch (Exception ex) {
		//		Reporter.log(" ===============Login failed ===========================", true);		
		//
		//		System.out.println("Login is failed: --> " + ex.getMessage());
		//	}
		//	LoginPage login = new LoginPage();
		//login.Lms_Login(data);

	}

	@Test (priority=2, alwaysRun=true, description = "Start comp") 
	public void navNextPage() throws InterruptedException
	{
		//int j=i-1;
		shtobj = excel.getSheetObject(filepath, "ActId");
		int rowCount=shtobj.getLastRowNum()-shtobj.getFirstRowNum();
		System.out.println("rowCount : ---->"+rowCount);
		for(int rowValue=1;rowValue<=rowCount;rowValue++) {

			data.put("actId", excel.getCellData(shtobj, rowValue, 0));
			data.put("pageNo", excel.getCellData(shtobj, rowValue, 1));
			data.put("comp", excel.getCellData(shtobj, rowValue, 2));
			NextPage nxtpg = new NextPage();
			nxtpg.Next_Page(data);
			String temp;

			if (NextPage.stat==true) {
				temp = "Component Added Successfully";
			} else {
				temp = "Skipped: Component Already Present";
			}
			//rowValue++;
			excel.setCellData("ActId", "Status", rowValue+1, temp);

		}

	}

	/*
	 * @Test(priority=3, alwaysRun=true, description =
	 * "Signout from the application") public void logOut() { SignOutPage logOut =
	 * new SignOutPage(); logOut.Logout(); }
	 */
	//	@Test
	//	public void registeredUserData() {
	//		
	//		shtobj = excel.getSheetObject(filepath, "registeredUser");		
	//		data.put("emailId", excel.getCellData(shtobj, 1, 0));
	//		data.put("password", excel.getCellData(shtobj, 1, 1));
	//		
	//
	//	}
	//	

	//public void actIdData() throws InterruptedException {
	//		//int j=i-1;
	//	shtobj = excel.getSheetObject(filepath, "ActId");
	//	int rowCount=shtobj.getLastRowNum()-shtobj.getFirstRowNum();
	//	System.out.println("rowCount : ---->"+rowCount);
	//	for(int rowValue=1;rowValue<=rowCount;rowValue++) {
	//					
	//		data.put("actId", excel.getCellData(shtobj, rowValue, 0));
	//		data.put("pageNo", excel.getCellData(shtobj, rowValue, 1));
	//		data.put("comp", excel.getCellData(shtobj, rowValue, 2));
	//		NextPage nxtpg = new NextPage();
	//		nxtpg.Next_Page(data);
	//	String temp;
	//		
	//	if (NextPage.stat==true) {
	//			temp = "Added Successfully";
	//		} else {
	//			temp = "Failed or already component present.";
	//		}
	//		//rowValue++;
	//		excel.setCellData("ActId", "Status", rowValue+1, temp);
	//		
	//	}
	//		
	//	}
	@AfterMethod()
	public void postTestcase()
	{
		flag++;
	}

	/*
	 * @AfterTest() public void doQuit() { driver.quit(); }
	 */


}
