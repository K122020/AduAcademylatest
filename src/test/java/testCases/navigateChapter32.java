package testCases;

import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
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

public class navigateChapter32 extends CommonLibs {
	ExcelLibs exl;
	String userSheet = null;
	XSSFSheet shtobj;
	int tempactId;
	int i = 1;
	static String actd;
	static String actd2;
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

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Reporter.log("**********Started Execution of " + "**********", true);
	}

	@Test (priority=1, alwaysRun=true, description = "Have loggedIn") 
	public void doLogin() throws InterruptedException
	{driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		shtobj = excel.getSheetObject(filepath, "registeredUser");		
		data.put("emailId", excel.getCellData(shtobj, 1, 0));
		data.put("password", excel.getCellData(shtobj, 1, 1));
		System.out.println(data);
		//loginPage.Lms_Login(data);
		LoginPage login = new LoginPage();
		login.Lms_Login(data);

	}
//	@Test (priority=2, alwaysRun=true, description = "Start Publishing")
//	public void navNextPage3() throws InterruptedException
//	{
//		NextPage2 nxtpg = new NextPage2();
//		nxtpg.Next_Page3(data);
//	}
	@Test (priority=2, alwaysRun=true, description = "Start USMO12") 
	public void navNextPage2() throws InterruptedException
	{//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String temp;
		String tempb;
	//int j=i-1;
	//Sheet sheet = book.getSheetAt(0);
	//System.out.println(sheet.getLastRowNum());
	shtobj = excel.getSheetObject(filepath, "USMO12");
	//int rowCount=shtobj.getLastRowNum()-shtobj.getFirstRowNum();
	int rowCount=shtobj.getLastRowNum();
	System.out.println("rowCount : ---->"+rowCount);

	for(int rowValue=1; rowValue <= rowCount; rowValue++) {

		data.put("actId", excel.getCellData(shtobj, rowValue, 0));
		data.put("usmo", excel.getCellData(shtobj, rowValue, 1));

		NextPage2 nxtpg = new NextPage2();
		
		//nxtpg.Next_Page2(data);
		//nxtpg.Next_Page3(data);
		
		
		

		//			if(tempactId == 1) {
		//				System.out.println("==========Only IF Condition=========="+actId);
		//			nxtpg.Next_Page2(data);	
		//			System.out.println("========Call Method Next_Page2 With If Condition=======");
		//			tempactId++;
		//			
		//			}else
		String actId =data.get("actId");
		if(actd2 != actId && actd2 != null)
		{
			//rowValue=rowValue-1;
			//data.put("actId", excel.getCellData(shtobj, rowValue-1, 0));
			System.out.println("=========IF Condition============"+actId);
			//nxtpg.Next_Page3(data);
			
			//Thread.sleep(40000);
			System.out.println("========Call Method Next_Page3=======");
			//driver.navigate().back();
			//rowValue=rowValue+1;
			rowValue--;
		}
		else if(rowValue==rowCount) 
		{
			System.out.println("==rowValue=="+rowValue+"=====ELSE IF Condition==rowCount==="+rowCount+"======="+actId);
			nxtpg.Next_Page2(data);
			//nxtpg.Next_Page3(data);
			
			//Thread.sleep(40000);
			System.out.println("========Call Method Next_Page3=======");
		}
		else 
		{
			System.out.println("==========Only ELSE Condition======="+actId);
			nxtpg.Next_Page2(data);

			System.out.println("========Call Method Next_Page2 With Else Condition=======");
			//tempactId++;
		}
		actd2 = actId;			



	
		if(NextPage2.stat==true)
		{
			temp = "Done";
			excel.setCellData("USMO12", "Status", rowValue+1, temp);
			
		} 
		else
		{
			temp = "Not Done";
			excel.setCellData("USMO12", "Status", rowValue+1, temp);
		}
		
		if(NextPage2.statb==true)
		{
			tempb="Published";
			excel.setCellData("USMO12", "publish_Status", rowValue+1, tempb);
			
		}
		else
		{
			tempb = "Not Published";
			excel.setCellData("USMO12", "publish_Status", rowValue+1, tempb);
		}
		
		
		}

	}

	

	@AfterMethod()
	public void postTestcase()
	{
		flag++;
	}

	
//	 @AfterTest() 
//	 public void doQuit() 
//	 {
//		 driver.quit();
//	}
//	


}
