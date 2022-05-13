package pages;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.CommonLibs;
import io.github.bonigarcia.wdm.WebDriverManager;
import locators.loginPageLocators;


public class usmoDriver extends CommonLibs {
	public static String browser = "firefox";
	public static WebDriver driver;
		
	
	public static void main(String[] args) throws InterruptedException{
		if (browser == "firefox"){
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();	
	}else if(browser == "chrome"){
		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
	}else if(browser == "edge"){
		WebDriverManager.edgedriver().setup();
		driver =new EdgeDriver();
	}
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//WebDriverWait wait = new WebDriverWait(driver, 20);
		// Waiting 30 seconds for an element to be present on the page, checking
		   // for its presence once every 5 seconds.
		   Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
		       .withTimeout(Duration.ofSeconds(30)) //30, TimeUnit.SECONDS
		       .pollingEvery(Duration.ofSeconds(5)) //5, TimeUnit.SECONDS
		       .ignoring(NoSuchElementException.class);
		   
		driver.get("https://calms.k12.com");
		//Thread.sleep(5000);
		//driver.findElement(By.xpath("//*[@id='login:username']")).sendKeys("jdhar0");	
		//driver.findElement(By.xpath("//*[@id='login:password']")).sendKeys("jdhar0");
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='login:username']"))).sendKeys("jdhar0");			
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='login:password']"))).sendKeys("jdhar0");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='login:password']"))).sendKeys("jdhar0");
		driver.findElement(By.xpath("//a[@id='login:submitLink']")).click();
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='login:submitLink']"))).click();
	}
}