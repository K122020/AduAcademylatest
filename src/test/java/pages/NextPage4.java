package pages;

import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import base.CommonLibs;
import generics.Constants;
import locators.nextPageLocator;


public class NextPage4 extends CommonLibs {
	public nextPageLocator nextpage2;
	static String actd;
	public static boolean stat;
	public NextPage4()
	
	{
		this.nextpage2 = new nextPageLocator();
		PageFactory.initElements(driver, this.nextpage2);
		
	}
	public void Next_Page2(Hashtable<String, String> data) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver,120);	
		String usmoid =  data.get("usmo");
		 String actId =data.get("actId");
		
		try {	
			driver.switchTo().defaultContent();
			System.out.println(" actd:"+actd);

			if(actd != actId) {
				driver.switchTo().defaultContent();
				Thread.sleep(1000);	
				type(nextpage2.actId, actId);	
				Thread.sleep(2000);	
				click(nextpage2.selectbtn);		
				driver.findElement(By.xpath("//*[@id='ext-gen40']"));		
				List<WebElement> allOptions = driver.findElements(By.xpath("//*[@id='ext-gen41']//div[1]")); //get all the options from the dropdown
				Thread.sleep(2000);
				for(WebElement option : allOptions) {   
				if (option.getText().equals("Activity")) 
				{
				option.click();
				}}
				
				click(nextpage2.gobtn);				
				Thread.sleep(2000);			
				String published = driver.findElement(By.xpath("//span[@id='activityHeader_form:titleStatus']")).getText();
				String publish ="Published";
				System.out.println("Get Text: "+published);
				
				if(published.length() == publish.length()) 
				{
					System.out.println("Status: "+published);			
					Thread.sleep(2000);
					 System.out.println(driver.findElement(By.className("x-btn-split")).getAttribute("id"));
				        System.out.println(driver.findElement(By.className("x-btn-split")).getAttribute("className"));
				
				    driver.findElement(By.xpath("//td[@class='x-btn-mr']")).click();
				    String parentHandle = driver.getWindowHandle();
					List<WebElement> allOptions1 = driver.findElements(By.xpath("//*[@id='ext-comp-1014']")); //get all the options from the dropdown
					Thread.sleep(2000);
					for(WebElement option1 : allOptions1) {   
					if (option1.getText().equals("Change Status")) 
					{
					option1.click();
					}}
					
					System.out.println("Clicked: on Change Status");					
					Thread.sleep(2000);
					
					for (String winHandle : driver.getWindowHandles()) {
					    driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
					}

					//code to do something on new window
					WebElement select = driver.findElement(By.xpath("//select[@id='activityHeader_form:statusID']"));
					Select pageindex = new Select(select);		
					pageindex.selectByIndex(3);
					System.out.println("Clicked: on 3 selectByIndex");
					
					Thread.sleep(2000);	
					WebElement clicksel= driver.findElement(By.xpath("//a[@id='activityHeader_form:j_id203']/span[1]"));
					clicksel.click();
					System.out.println("Clicked: on save");
					//driver.switchTo().defaultContent();
					driver.switchTo().window(parentHandle); // switch back to the original window	
				//	driver.switchTo().defaultContent();
					Thread.sleep(10000);
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='activityForm:propertyTab_lbl']"))).click();
					System.out.println("Clicked on Activity Properties!!!");
					Thread.sleep(2000);
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='activityForm:objectivesControl']"))).click();
					System.out.println("Clicked on Objectives!!!");				
					Thread.sleep(2000);

					
				//////------------------loop Start for USMO Removing---------------------////	
//					try {
					Reporter.log("Loop has been started !!!", true);
					boolean hasNextPage = true;
					while (hasNextPage) {
						List<WebElement> enabled_next_page_btn = driver
								.findElements(By.xpath("//input[contains(@id, 'activityForm:j_id')]"));
						if (enabled_next_page_btn.size() > 0) {
							enabled_next_page_btn.get(0).click();
							//Thread.sleep(2000);
							driver.switchTo().alert().accept();
							Thread.sleep(1000);
							//waitTime(Constants.waitforNextPage);						
							hasNextPage = true;
						} else {
							hasNextPage = false;
							System.out.println("No more USMO Available");
						
						}
					}
//				} catch (StaleElementReferenceException e) {
//					e.printStackTrace();
//				}
					//////------------------loop End after USMO Removed---------------------////
					
					System.out.println("Outside 1 the loop:-- ");	
//					System.out.println("-MSG->1------USMO Start Adding-------");
//					Thread.sleep(2000);	
//					type(nextpage2.usmoid, usmoid);	
//					Thread.sleep(2000);	
//					click(nextpage2.usmobtn);
					actd=actId;
					System.out.println("actd: "+actId+ "    actd: "+actd);
//					System.out.println("Msg--IF condition: USMO present on the activity!!! "+actd);
//					stat=true;
				
				}
				else {
					Thread.sleep(3000);
				//	driver.switchTo().defaultContent();
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='activityForm:propertyTab_lbl']"))).click();
					System.out.println("Clicked on Activity Properties!!!");
					Thread.sleep(5000);
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='activityForm:objectivesControl']"))).click();
					System.out.println("Clicked on Objectives!!!");					
					Thread.sleep(2000);
					
				//////------------------loop Start for USMO Removing---------------------////	
					//try {
					Reporter.log("Loop has been started !!!", true);
					boolean hasNextPage = true;
					while (hasNextPage) {
						List<WebElement> enabled_next_page_btn = driver
								.findElements(By.xpath("//input[contains(@id, 'activityForm:j_id')]"));
						if (enabled_next_page_btn.size() > 0) {
							enabled_next_page_btn.get(0).click();
							//Thread.sleep(2000);
							driver.switchTo().alert().accept();
							Thread.sleep(1000);
							//waitTime(Constants.waitforNextPage);						
							hasNextPage = true;
						} else {
							hasNextPage = false;
							System.out.println("No more USMO Available");
							
						}
					}
//					} catch (StaleElementReferenceException e) {
//						e.printStackTrace();
//					}
					//////------------------loop End after USMO Removed---------------------////
					
					System.out.println("Outside 2 the loop:-- ");				

					actd=actId;
					System.out.println("Msg--Else Statement:!!! "+actd);
//					stat=true;
					}
				System.out.println("-MSG->2------USMO Start Adding-------");
				Thread.sleep(2000);	
				type(nextpage2.usmoid, usmoid);						
				click(nextpage2.usmobtn);				
				//actd=actId;
				stat=true;
				System.out.println("Bif IF closing actd: "+actd);
				}			
		
			
			else {
				System.out.println("-MSG-Big Else Statement------USMO Start Adding-------");
				Thread.sleep(2000);	
				type(nextpage2.usmoid, usmoid);					
				click(nextpage2.usmobtn);
				actd=actId;
				System.out.println("Msg--2: USMO added on the activity!!! "+actd);
				stat=true;
				
				}
			
		
		}
		catch(org.openqa.selenium.StaleElementReferenceException ex)
		{
			
			System.out.println("Not clicked on form!!!!!!!!!!!!!!!!!!!!");
			
			
		}
	}

	
	public void Next_Page3(Hashtable<String, String> data) throws InterruptedException {
		
		String actId =data.get("actId");
		
		try {	
			driver.switchTo().defaultContent();
			System.out.println(" actd:"+actd);
			if(actd != actId) {
				driver.switchTo().defaultContent();
				Thread.sleep(1000);	
				type(nextpage2.actId, actId);	
				Thread.sleep(2000);	
				click(nextpage2.selectbtn);		
				driver.findElement(By.xpath("//*[@id='ext-gen40']"));		
				List<WebElement> allOptions = driver.findElements(By.xpath("//*[@id='ext-gen41']//div[1]")); //get all the options from the dropdown
				Thread.sleep(2000);
				for(WebElement option : allOptions) {   
				if (option.getText().equals("Activity")) 
				{
				option.click();
				}}
				
				click(nextpage2.gobtn);				
						
				Thread.sleep(2000);			
				String published = driver.findElement(By.xpath("//span[@id='activityHeader_form:titleStatus']")).getText();
				String publish ="Published";
				System.out.println("Get Text: "+published);
				
				if(published.length() == publish.length()) 
				{
					System.out.println("Status: Already "+published);
					actd=actId;
					stat=true;
				}else {
					
					Thread.sleep(2000);
					 System.out.println(driver.findElement(By.className("x-btn-split")).getAttribute("id"));
				        System.out.println(driver.findElement(By.className("x-btn-split")).getAttribute("className"));
				
				    driver.findElement(By.xpath("//td[@class='x-btn-mr']")).click();				 
					List<WebElement> allOptions1 = driver.findElements(By.xpath("//*[@id='ext-comp-1017']")); //get all the options from the dropdown
					Thread.sleep(2000);
					for(WebElement option1 : allOptions1) {   
					if (option1.getText().equals("Publish")) 
					{
					option1.click();
					}}
					
					System.out.println("Clicked: on Publish");					
					Thread.sleep(3000);
//					String parentHandle = driver.getWindowHandle();
//					for (String winHandle : driver.getWindowHandles()) {
//				    driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
//					
//				}
					//Thread.sleep(2000);
					//driver.switchTo().defaultContent();
					
					//code to do something on new window
					WebElement iframe = driver.findElement(By.xpath("//iframe[@id='validateActivityIMP']"));
					driver.switchTo().frame(iframe);
					Thread.sleep(3000);	
					driver.findElement(By.xpath("//*[contains(text(), 'Submit for Republish')]")).click();
					//driver.switchTo().defaultContent();
						
				    Thread.sleep(22000);
				   //driver.switchTo().window(parentHandle); // switch back to the original window	
					
					actd=actId;
					stat=true;
				}
				
			}
			else {
				actd=actId;
				stat=false;
				System.out.println("Already published !!!!!");
				}
		}
		catch(org.openqa.selenium.StaleElementReferenceException ex)
		{
			
			System.out.println("Not clicked on form!!!!!!!!!!!!!!!!!!!!");
			
			
		}
	}
			
}
