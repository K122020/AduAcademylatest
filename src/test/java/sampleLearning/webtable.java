package sampleLearning;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class webtable {
	
	public static WebDriver driver;
	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://money.rediff.com/gainers");
		
		List<WebElement> tblRow = driver.findElements(By.xpath("//table[@class='dataTable']/tbody/tr"));
		List<WebElement> tblCol = driver.findElements(By.xpath("//table[@class='dataTable']/tbody/tr[1]/td"));
		System.out.println("tblRow count = "+tblRow.size()+" tblCol count = "+tblCol.size());
		for (int i = 1; i <= tblRow.size(); i++) {
			
			for (int j = 1; j <= tblCol.size(); j++) {
				
				//System.out.println("tblRow value = "+ tblRow.get(i).getText()+" tblCol value = "+ tblCol.get(j).getText());
				System.out.print(driver.findElement(By.xpath("//table[@class='dataTable']/tbody/tr["+i+"]/td["+j+"]")).getText()+"  ");
			}
			System.out.println();
		}
	}

}
