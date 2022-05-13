package pages;

import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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


public class NextPage2 extends CommonLibs {
	public nextPageLocator nextpage2;
	static String actd;
	public static boolean stat;
	public static boolean statb;
	public NextPage2()
	
	{
		this.nextpage2 = new nextPageLocator();
		PageFactory.initElements(driver, this.nextpage2);
		
	}
	
	public void Next_Page2(Hashtable<String, String> data) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver,120);	
		Actions actions = new Actions(driver);
		String usmoid =  data.get("usmo");
		 String actId =data.get("actId");
		
		try {	
			//driver.switchTo().defaultContent();
			//System.out.println(" actd:"+actd);

			if(actd != actId) {
				driver.switchTo().defaultContent();
				Thread.sleep(2000);	
				type(nextpage2.actId, actId);	
				Thread.sleep(1000);	
				String actstr = driver.findElement(By.xpath("//div[@id='ext-gen38']/input[@id='gotoDestination']")).getAttribute("value");
				System.out.println("=====actstr===getAttribute======"+actstr+"====");
				//value="ACTIVITIES"
				String strvalue="ACTIVITIES";
				if(actstr.length() != strvalue.length()) {
					System.out.println("=====actstr="+actstr.length()+"==getAttribute======"+strvalue.length()+"====");
				click(nextpage2.selectbtn);		
				driver.findElement(By.xpath("//*[@id='ext-gen40']"));		
				List<WebElement> allOptions = driver.findElements(By.xpath("//*[@id='ext-gen41']//div[1]")); //get all the options from the dropdown
				Thread.sleep(2000);
				for(WebElement option : allOptions) {   
				if (option.getText().equals("Activity")) 
				{
				option.click();
				}}				
				
				}
				sleep(2);	
				click(wait.until(ExpectedConditions.elementToBeClickable(nextpage2.gobtn)));
				//sleeph(2);
				//click(wait.until(ExpectedConditions.elementToBeClickable(nextpage2.gobtn)));
				Thread.sleep(1000);			
				String published = driver.findElement(By.xpath("//span[@id='activityHeader_form:titleStatus']")).getText();
				String publish ="Published";
				System.out.println("Get Text: "+published);
				
				if(published.length() == publish.length()) 
				{
					System.out.println("Status: "+published);			
					Thread.sleep(1000);
					// System.out.println(driver.findElement(By.className("x-btn-split")).getAttribute("id"));
				     //   System.out.println(driver.findElement(By.className("x-btn-split")).getAttribute("className"));
				
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='x-btn-mr']"))).click();
					Thread.sleep(1000);
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
					
					Thread.sleep(5000);	
					WebElement clicksel= driver.findElement(By.xpath("//a[@id='activityHeader_form:j_id203']/span[1]"));
					//Thread.sleep(1000);
					clicksel.click();
					System.out.println("Clicked: on save");					
					//driver.switchTo().window(parentHandle); // switch back to the original window	
				
					Thread.sleep(15000);	
					//WebElement element = driver.findElement(By.xpath("//td[@id='activityForm:propertyTab_lbl']"));
					WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("activityForm:propertyTab_lbl")));										
					actions.moveToElement(element).click().build().perform();
					//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@id='activityForm:propertyTab_lbl']"))).click();
					System.out.println("Clicked on Activity Properties!!!");
					Thread.sleep(4000);	
					WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='activityForm:objectivesControl']")));					
					actions.moveToElement(element1).click().build().perform();
					//driver.findElement(By.xpath("//*[@id='activityForm:objectivesControl']")).click();
					System.out.println("Clicked on Objectives!!!");				
					//Thread.sleep(1000);

					
				//////------------------loop Start for USMO Removing---------------------////	
//					try {
					Reporter.log("Loop has been started !!!", true);
					boolean hasNextPage = true;
					while (hasNextPage) {
						Thread.sleep(2000);
						List<WebElement> enabled_next_page_btn = driver
								.findElements(By.xpath("//input[contains(@id, 'activityForm:j_id')]"));
						if (enabled_next_page_btn.size() > 0) {
							Thread.sleep(1000);
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
					actd=actId;
					System.out.println("actd: "+actId+ "    actd: "+actd);				
					stat=true;
				
				}
				else {
					Thread.sleep(2000);
				//	driver.switchTo().defaultContent();
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@id='activityForm:propertyTab_lbl']"))).click();
					System.out.println("Clicked on Activity Properties!!!");
					Thread.sleep(1000);
					driver.findElement(By.xpath("//*[@id='activityForm:objectivesControl']")).click();
					System.out.println("Clicked on Objectives!!!");					
					
					
				//////------------------loop Start for USMO Removing---------------------////	
					//try {
					Reporter.log("Loop has been started !!!", true);
					
					boolean hasNextPage = true;
					while (hasNextPage) {
						Thread.sleep(2000);
						List<WebElement> enabled_next_page_btn = driver
								.findElements(By.xpath("//input[contains(@id, 'activityForm:j_id')]"));
						if (enabled_next_page_btn.size() > 0) {
							Thread.sleep(1000);
							enabled_next_page_btn.get(0).click();
							//Thread.sleep(1000);
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
				Thread.sleep(1000);
				click(nextpage2.usmobtn);				
				//actd=actId;
				stat=true;
				System.out.println("Bif IF closing actd: "+actd);
				}			
		
			
			else {
				System.out.println("-MSG-Big Else Statement------USMO Start Adding-------");
				Thread.sleep(2000);	
				type(nextpage2.usmoid, usmoid);	
				Thread.sleep(1000);
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

	
//	public void Next_Page3(Hashtable<String, String> data) throws InterruptedException {
//		
//		String actId =data.get("actId");
//		System.out.println("Next_Page3 == actId is : "+ actId);
//		
//		
//		try {	
//						
//				Thread.sleep(2000);			
//				String published = driver.findElement(By.xpath("//span[@id='activityHeader_form:titleStatus']")).getText();
//				String publish ="Published";
//				System.out.println("Get Text: "+published);
//				
//				if(published.length() == publish.length()) 
//				{
//					System.out.println("Status: Already "+published);
//					//actd=actId;
//					statb=true;
//				}else {//span[contains(text(), 'Publish')]
//					
////					Thread.sleep(2000);
////					 System.out.println(driver.findElement(By.className("x-btn-split")).getAttribute("id"));
////				        System.out.println(driver.findElement(By.className("x-btn-split")).getAttribute("className"));
//					
//					Thread.sleep(1000);
//				    driver.findElement(By.xpath("//td[@class='x-btn-mr']")).click();
//				    Thread.sleep(1000);
//				   
//				    
//					List<WebElement> allOptions1 = driver.findElements(By.xpath("//ul[@class='x-menu-list']/li[4]")); //get all the options from the dropdown
//					
//					for(WebElement option1 : allOptions1) {   
//					if (option1.getText().equals("Publish")) 
//					{
//					option1.click();
//					}}
//					
//					System.out.println("Clicked: on Publish");					
//					Thread.sleep(1000);//				
//					
//					//code to do something on new window
//					WebElement iframe = driver.findElement(By.xpath("//iframe[@id='validateActivityIMP']"));
//					driver.switchTo().frame(iframe);
//					Thread.sleep(500);	
//					String clo = driver.findElement(By.xpath("//*[@id='validateActivityForm:j_id89']/span")).getText();
//					String close ="Close";					
//					Thread.sleep(1000);
//					if(driver.findElements(By.xpath("//a[@id='validateActivityForm:j_id79']")).size() > 0) {
//						//Thread.sleep(1000);
//						driver.findElement(By.xpath("//a[@id='validateActivityForm:j_id79']")).click();
//						sleep(30);					  
//					    statb=true;
//					}
//					else if(clo.length() == close.length()) 
//					{
//						System.out.println("Get Text: "+clo);
//						driver.findElement(By.xpath("//*[@id='validateActivityForm:j_id89']/span")).click();
//						driver.switchTo().defaultContent();
//						Thread.sleep(3000);
//						driver.findElement(By.xpath("//a[@id='activityForm:activityInfoControl']")).click();
//						Thread.sleep(1000);
//						driver.findElement(By.xpath("//*[@id='activityForm:contentPlayerProperties_body']/div[2]/table/tbody/tr/td/a/span")).click();
//						Thread.sleep(1000);//				
//						
//						//code to do something on new window
//						WebElement iframe2 = driver.findElement(By.xpath("//iframe[@id='editActivityIMP']"));
//						driver.switchTo().frame(iframe2);
//						/**
//						* Validate Checkbox isSelected method and click
//						*/
//						Thread.sleep(1000);
//						//WebElement checkBoxElement = 
//					driver.findElement(By.xpath("//*[@id='createForm:showPagination']")).click();
////						boolean isSelected = checkBoxElement.isSelected();
////								
////						//performing click operation if element is not checked
////						if(isSelected == false) {
////							checkBoxElement.click();
////						}
//					driver.findElement(By.xpath("//*[@id='createForm:saveEdit']")).click();
//					
//					
//					driver.switchTo().defaultContent();
//					Thread.sleep(2000);
//					//driver.findElement(By.xpath("//*[@id='ext-comp-1082']")).click();
//					driver.findElement(By.xpath("//button[contains(text(), 'Publish')]")).click();
//				    //Thread.sleep(1000);	  				    
////				    List<WebElement> allOptions2 = driver.findElements(By.xpath("//ul[@class='x-menu-list']/li[4]")); //get all the options from the dropdown
////					for(WebElement option2 : allOptions1) {   
////					if (option2.getText().equals("Publish")) 
////					{
////					option2.click();
////					}}
//					
//					System.out.println("Clicked: on Publish");					
//					Thread.sleep(1000);//				
//					
//					//code to do something on new window
//					WebElement iframe1 = driver.findElement(By.xpath("//iframe[@id='validateActivityIMP']"));
//					
//					driver.switchTo().frame(iframe1);
//					Thread.sleep(1000);
//					driver.findElement(By.xpath("//a[@id='validateActivityForm:j_id79']")).click();
//					sleep(30);
//					statb=true;
//					} 
//					else {
//					driver.findElement(By.xpath("//a[@id='validateActivityForm:j_id79']")).click();
//					sleep(30);
//				  //driver.switchTo().defaultContent();
//				   //driver.switchTo().window(parentHandle); // switch back to the original window	
//					
//					//actd=actId;
//				    statb=true;
//					}
//				}
//				
////			}
////			else {
////				actd=actId;
////				stat=false;
////				System.out.println("Already published !!!!!");
////				}
//		}
//		catch(org.openqa.selenium.StaleElementReferenceException ex)
//		{
//			
//			System.out.println("Not clicked on form!!!!!!!!!!!!!!!!!!!!");
//			
//			
//		}
//	}
	///duplicate----------------//////
public void Next_Page3(Hashtable<String, String> data) throws InterruptedException {
		
		String actId =data.get("actId");
		System.out.println("Next_Page3 == actId is : "+ actId);
		
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
					//actd=actId;
					statb=true;
				}else {
					//span[contains(text(), 'Publish')]					
//					Thread.sleep(2000);
//					 System.out.println(driver.findElement(By.className("x-btn-split")).getAttribute("id"));
//				        System.out.println(driver.findElement(By.className("x-btn-split")).getAttribute("className"));
					Thread.sleep(1000);
				    driver.findElement(By.xpath("//td[@class='x-btn-mr']")).click();
				    Thread.sleep(1000);
				   
				    
					List<WebElement> allOptions1 = driver.findElements(By.xpath("//ul[@class='x-menu-list']/li[4]")); //get all the options from the dropdown
					
					for(WebElement option1 : allOptions1) {   
					if (option1.getText().equals("Publish")) 
					{
					option1.click();
					}}
					
					System.out.println("Clicked: on Publish");					
					Thread.sleep(2000);//				
					
					//code to do something on new window
					WebElement iframe = driver.findElement(By.xpath("//iframe[@id='validateActivityIMP']"));
					driver.switchTo().frame(iframe);
					Thread.sleep(2000);	
					driver.findElement(By.xpath("//a[@id='validateActivityForm:j_id79']")).click();
				    Thread.sleep(23000);
				  //driver.switchTo().defaultContent();
				   //driver.switchTo().window(parentHandle); // switch back to the original window	
					
					//actd=actId;
				    statb=true;
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
