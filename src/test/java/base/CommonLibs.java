package base;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import generics.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CommonLibs {

	public static WebDriver driver = null;
	public static WebDriver wait = null;
	public static String brw = null;
	public static Properties OR = new Properties();

	public WebDriver getDriverObject(String browser, String url) throws InterruptedException {
	
		System.out.println("Will launch : " + browser);
		switch (browser) {
		case "firefox":

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			
			break;
		case "ie":

			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
			
			break;

		case "chrome":
			WebDriverManager.chromedriver().setup();
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("profile.default_content_setting_values.media_stream_camera", 1);
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-infobars");
			
			// Additional options
			options.addArguments("chrome.switches", "--disable-extensions");
			options.addArguments("--start-maximized");
			options.addArguments("--disable-popup-blocking");
			options.addArguments("test-type", "start-maximized", "no-default-browser-check");

			driver = new ChromeDriver(options);
			
			break;
		}
		driver.manage().deleteAllCookies();
		driver.get(url);
		isPageloaded(driver);

		return driver;
	}
	
	

	public static void click(WebElement ele) {

		try {

			ele.click();

		} catch (Exception e) {

			Assert.fail("Element is not found!!!");
		}

	}

	public static void type(WebElement element, String value) {

		try {

			element.clear();
			element.sendKeys(value);

		} catch (Exception e) {
			Assert.fail("Element is not found for typing");
		}

	}
	
	public static void isPageloaded(WebDriver drv) throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(drv, Constants.waittimeforURL);
		  wait.until((ExpectedCondition<Boolean>) wd -> 
		  ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")); 
		  int count =0; 
		  JavascriptExecutor executor = (JavascriptExecutor) drv;
		  if((Boolean) executor.executeScript("return window.jQuery != undefined"))
		  { 
			  while(!(Boolean) executor.executeScript("return jQuery.active == 0"))
			  { 
				  drv.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				  if(count>4) 
					  break; 
				  count++; 
			  } 
		 } 
	}
	

	public void waitTime(int i) {
		try {
			Thread.sleep(i * 60000);			
		} catch (InterruptedException e) {
			e.getMessage();
		}
	}
	
	public static void sleep(int sleep) {
		try {
			Thread.sleep(sleep * 1000);
		} catch (InterruptedException e) {
			e.getMessage();
		}
		
	}
	public void sleeph(int seconds) {
		try {
			Thread.sleep(seconds);			
		} catch (InterruptedException e) {
			e.getMessage();
		}
	}

	public void navigateBack(WebDriver drv) {
		drv.navigate().back();
	}

}
