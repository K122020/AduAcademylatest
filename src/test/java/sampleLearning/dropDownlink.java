package sampleLearning;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;



import io.github.bonigarcia.wdm.WebDriverManager;

public class dropDownlink {
	
	public static void main(String[] args) throws IOException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.wikipedia.org");
		
		WebElement dropdwon = driver.findElement(By.id("searchLanguage"));
		Select select = new Select(dropdwon);
		//System.out.println(select.getOptions());
		select.selectByValue("hi");
		File sel = dropdwon.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(sel, new File("./screenshot/dropdwon.jpg"));
		
		List<WebElement> ddvalue= dropdwon.findElements(By.tagName("option"));
		System.out.println("Total value == "+ddvalue.size());
		
		WebElement section = driver.findElement(By.xpath("/html/body/div[8]/div[3]"));
		List<WebElement> links= section.findElements(By.tagName("a"));
		//System.out.println(links.get(0).getText());
		System.out.println(links.size());
//		for (int i = 0; i < links.size(); i++) {
//			System.out.println(links.get(i).getText()+" "+ links.get(i).getAttribute("href"));
//			
//			
//		}
		for (WebElement link : links) {
			System.out.println(link.getText()+" "+ link.getAttribute("href"));
		}
	}
}
