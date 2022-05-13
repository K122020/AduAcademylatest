package sampleLearning;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestNgTestCase {

	@BeforeTest
	public void creatConn() {
		System.out.println("Create connection");
	}
	
	@AfterTest
	public void closeConn() {
		System.out.println("Close connection");
	}
	
	@BeforeMethod
	public void launchBro() {
		System.out.println("Lauch Browser");
	}
	
	@AfterMethod
	public void closeBro() {
		System.out.println("Close Browser");
	}
	
	@Test(priority=2)
	public void doLogin() {
		System.out.println("Login successfully");
		String expected_name ="Gaurav";
		String actual_name ="Gaurav";
		
		//Assert.assertEquals(actual_name, actual_name);
		//Assert.fail("Failing the testcase due to condtion is not met.");
		//Assert.assertTrue(false, "Element is not found.");
		//Assert.assertEquals(true, false); //----This is hard assertion-------------
		System.out.println("Start Validating testcase");
		SoftAssert softassert= new SoftAssert();
		softassert.assertEquals(actual_name, expected_name, "name is not mismatch");
		System.out.println("Validate testcase");
		
		System.out.println("Validate image");
		//softassert.assertEquals(true, false, "image is not found");
		
		System.out.println("Validate textbox");
		///softassert.assertEquals(true, false, "textbox not found");
		System.out.println("End Validate testcase");
		
		softassert.assertAll();
	}
	
	@Test(priority=1)
	public void doReg() {
		System.out.println("Registration successfully");
	}


}
