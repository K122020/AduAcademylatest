package sampleLearning;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class checkvisiblity {

	public static WebDriver driver;
	
	public static boolean isElementPresent(By by) {
		
		
//		try {
//		driver.findElement(By.id(locator));
//		return true;
//		}
//		catch(Throwable t){
//			return false;
//		}
		
		int size = driver.findElements(by).size();
		if(size==0) {
			return false;
		
		}else {
			return true;
		}
	}
	
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		driver.get("https://www.wikipedia.org");
		
		//System.out.println(driver.findElement(By.id("searchLanguage")).isDisplayed());
		System.out.println(isElementPresent(By.id("searchLanguageq")));

	}

}
