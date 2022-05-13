package base;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;



public class threadLocalDriver {
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	public static void setDriver()
	{
		WebDriverManager.chromedriver().setup();
		
		  Map<String, Object> prefs = new HashMap<String, Object>();
		  prefs.put("profile.default_content_setting_values.notifications", 2);
		  prefs.put("profile.default_content_setting_values.media_stream_camera", 1);
		  prefs.put("credentials_enable_service", false);
		  prefs.put("profile.password_manager_enabled", false); 
		  ChromeOptions options =  new ChromeOptions(); 
		  options.setExperimentalOption("prefs", prefs);
		  options.addArguments("--disable-extensions");
		  options.addArguments("--disable-infobars");
		  
		  // Additional options 
		  options.addArguments("chrome.switches","--disable-extensions"); 
		  options.addArguments("--start-maximized");
		  options.addArguments("--disable-popup-blocking");
		  options.addArguments("test-type", "start-maximized", "no-default-browser-check");
		 
		driver.set(new ChromeDriver(options));
	}
	
	public static WebDriver getDriver()
	{
		return driver.get();
	}
	
	public static void closeBrowser()
	{
		driver.get().close();
		driver.remove();
	}
	
		
			/*
			 * SimpleDateFormat df = new SimpleDateFormat("HH:mm"); java.util.Date d = new
			 * java.util.Date(); java.util.Calendar cal = java.util.Calendar.getInstance();
			 * cal.setTime(d); cal.add(java.util.Calendar.MINUTE, time); String desiredTime
			 * = df.format(cal.getTime()); String currentTime = df.format(d);
			 * System.out.println("New Time" + desiredTime);
			 * System.out.println("New Time with date " + currentTime);
			 */


}
