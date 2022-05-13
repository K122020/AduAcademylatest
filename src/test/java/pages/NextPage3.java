package pages;

import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import base.CommonLibs;
import generics.Constants;
import locators.nextPageLocator;


public class NextPage3  extends CommonLibs {
	public nextPageLocator nextpage2;
	static String actd;
	public static boolean stat;
	public static boolean statb;
	public NextPage3()
	
	{
		this.nextpage2 = new nextPageLocator();
		PageFactory.initElements(driver, this.nextpage2);
	}
@SuppressWarnings("deprecation")
WebDriverWait wait = new WebDriverWait(driver, Constants.timeoutInSeconds);

	public void Next_Page2(Hashtable<String, String> data) throws InterruptedException {
		
		String usmoid =  data.get("usmo");
		 String actId =data.get("actId");
		 
		try {	
		

			if(actd != actId) {
				driver.switchTo().defaultContent();
				
				
				type(wait.until(ExpectedConditions.visibilityOf(nextpage2.actId)), actId);	
				
				WebElement gettxt1=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='ext-gen38']/input[@id='gotoDestination']")));
				String actstr = gettxt1.getAttribute("value");
				System.out.println("=====actstr===getAttribute======"+actstr+"====");
			
				String strvalue="ACTIVITIES";
				if(actstr.length() != strvalue.length()) {
					System.out.println("=====actstr="+actstr.length()+"==getAttribute======"+strvalue.length()+"====");
				click(nextpage2.selectbtn);		
				//driver.findElement(By.xpath("//*[@id='ext-gen40']"));		
				sleeph(1);
				List<WebElement> allOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id='ext-gen41']//div[1]"))); //get all the options from the dropdown
				
				sleep(2);
				for(WebElement option : allOptions) {   
				if (option.getText().equals("Activity")) 
				{
				option.click();
				}}				
				
				}
				sleeph(1);
				click(wait.until(ExpectedConditions.elementToBeClickable(nextpage2.gobtn)));
						
				WebElement gettxt2=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='activityHeader_form:titleStatus']")));
				String published = gettxt2.getText();
				String publish ="Published";
				System.out.println("Get Text: "+published);
				
				if(published.length() == publish.length()) 
				{
					System.out.println("Status: "+published);			
					
					sleeph(1);
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='x-btn-mr']"))).click();
				    String parentHandle = driver.getWindowHandle();
				    sleeph(1);
					List<WebElement> allOptions1 = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id='ext-comp-1014']"))); //get all the options from the dropdown
				
					for(WebElement option1 : allOptions1) {   
					if (option1.getText().equals("Change Status")) 
					{
					option1.click();
					}}
					
					System.out.println("Clicked: on Change Status");					
				
					
					for (String winHandle : driver.getWindowHandles()) {
					    driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
					}

					//code to do something on new window
					WebElement select = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='activityHeader_form:statusID']")));
					Select pageindex = new Select(select);		
					pageindex.selectByIndex(3);
					System.out.println("Clicked: on 3 selectByIndex");
					sleep(2);
				
					wait.until(ExpectedConditions.elementToBeClickable(By.id("activityHeader_form:j_id203"))).click();
				
					System.out.println("Clicked: on save");	
				
					driver.switchTo().window(parentHandle); // switch back to the original window	
					sleeph(1);
					
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='activityForm:propertyTab_lbl']"))).click();
					System.out.println("Clicked on Activity Properties!!!");
					sleeph(1);
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='activityForm:objectivesControl']"))).click();
					System.out.println("Clicked on Objectives!!!");				
					

					
				//////------------------loop Start for USMO Removing---------------------////	
					try {
						
					Reporter.log("Loop has been started !!!", true);
					boolean hasNextPage = true;
					while (hasNextPage) {
						
						sleeph(1);
						List<WebElement> enabled_next_page_btn = driver.findElements(By.xpath("//input[contains(@id, 'activityForm:j_id')]"));
						if (enabled_next_page_btn.size() > 0) {
							sleeph(1);
							enabled_next_page_btn.get(0).click();
							
							driver.switchTo().alert().accept();
												
							hasNextPage = true;
						} else {
							hasNextPage = false;
							System.out.println("No more USMO Available");
						
						}
					}
			} catch (StaleElementReferenceException e) {
					e.printStackTrace();
				}
					//////------------------loop End after USMO Removed---------------------////
					
					System.out.println("Outside 1 the loop:-- ");
					actd=actId;
					System.out.println("actd: "+actId+ "    actd: "+actd);				
					stat=true;
				
				}
				else {
					
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='activityForm:propertyTab_lbl']"))).click();
					System.out.println("Clicked on Activity Properties!!!");
					sleeph(2);
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='activityForm:objectivesControl']"))).click();
					System.out.println("Clicked on Objectives!!!");					
					
					
				//////------------------loop Start for USMO Removing---presenceOfElementLocated------------------////	
					try {
						Reporter.log("Loop has been started !!!", true);
						sleeph(2);
						boolean hasNextPage = true;
						while (hasNextPage) {
						
							sleeph(2);
							List<WebElement> enabled_next_page_btn =driver.findElements(By.xpath("//input[contains(@id, 'activityForm:j_id')]"));
							if (enabled_next_page_btn.size() > 0) {
								sleeph(1);
								enabled_next_page_btn.get(0).click();
								sleeph(1);
								driver.switchTo().alert().accept();
														
								hasNextPage = true;
							} else {
								hasNextPage = false;
								System.out.println("No more USMO Available");
							
							}
						}
				} catch (StaleElementReferenceException e) {
						e.printStackTrace();
					}
					//////------------------loop End after USMO Removed---------------------////
					
					System.out.println("Outside 2 the loop:-- ");				

					actd=actId;
					System.out.println("Msg--Else Statement:!!! "+actd);

					}
				System.out.println("-MSG->2------USMO Start Adding-------");
				
				type(wait.until(ExpectedConditions.visibilityOf(nextpage2.usmoid)), usmoid);						
				click(wait.until(ExpectedConditions.elementToBeClickable(nextpage2.usmobtn)));				
				
				stat=true;
				System.out.println("Bif IF closing actd: "+actd);
				}			
		
			else {
				System.out.println("-MSG-Big Else Statement------USMO Start Adding-------");
				
				sleeph(1);
				type(wait.until(ExpectedConditions.visibilityOf(nextpage2.usmoid)), usmoid);
				sleeph(2);
				click(wait.until(ExpectedConditions.elementToBeClickable(nextpage2.usmobtn)));
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
		System.out.println("Next_Page3 == actId is : "+ actId);
		
		try {	
			//driver.switchTo().defaultContent();
			//System.out.println(" actd:"+actd);
//			if(actd != actId) {
//				driver.switchTo().defaultContent();
//				Thread.sleep(1000);	
//				type(nextpage2.actId, actId);	
//				Thread.sleep(2000);	
//				click(nextpage2.selectbtn);		
//				driver.findElement(By.xpath("//*[@id='ext-gen40']"));		
//				List<WebElement> allOptions = driver.findElements(By.xpath("//*[@id='ext-gen41']//div[1]")); //get all the options from the dropdown
//				Thread.sleep(2000);
//				for(WebElement option : allOptions) {   
//				if (option.getText().equals("Activity")) 
//				{
//				option.click();
//				}}
//				
//				click(nextpage2.gobtn);				
						
				//Thread.sleep(2000);			
			WebElement gettxt3=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='activityHeader_form:titleStatus']")));
				String published =gettxt3.getText();
				String publish ="Published";
				System.out.println("Get Text: "+published);
				
				if(published.length() == publish.length()) 
				{
					System.out.println("Status: Already "+published);
					//actd=actId;
					statb=true;
				}else {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='x-btn-mr']"))).click();
					
					List<WebElement> allOptions1 = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ul[@class='x-menu-list']/li[4]"))); //get all the options from the dropdown
					sleeph(1);
					for(WebElement option1 : allOptions1) {   
					if (option1.getText().equals("Publish")) 
					{
					option1.click();
					}}
					
					System.out.println("Clicked: on Publish");
					
					//code to do something on new window
					WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[@id='validateActivityIMP']")));
					driver.switchTo().frame(iframe);
						
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='validateActivityForm:j_id79']"))).click();
				   
				  //driver.switchTo().defaultContent(); //switch back to the original window	
					
					//actd=actId;
				    statb=true;
				}
				
//			}
//			else {
//				actd=actId;
//				stat=false;
//				System.out.println("Already published !!!!!");
//				}
		}
		catch(org.openqa.selenium.StaleElementReferenceException ex)
		{
			
			System.out.println("Not clicked on form!!!!!!!!!!!!!!!!!!!!");
			
			
		}
	}
	
	public boolean isAlertPresent(){ 
	    try{ 
	        Alert a = new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
	        if(a!=null){
	            System.out.println("Alert is present");
	            driver.switchTo().alert().accept();
	            return true;
	        }else{
	            throw new Throwable();
	        }
	    } 
	    catch (Throwable e) {
	        System.err.println("Alert isn't present!!");
	        return false; 
	    } 

	} 	
}
