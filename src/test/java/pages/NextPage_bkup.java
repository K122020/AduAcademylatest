package pages;

import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import base.CommonLibs;
import generics.Constants;
import locators.nextPageLocator;


public class NextPage_bkup extends CommonLibs {
	public nextPageLocator nextpage;
	//int pindex=0;
	//String actId="892181";
	static String actd;
	public static boolean stat;
	public NextPage_bkup()
	
	{
		this.nextpage = new nextPageLocator();
		PageFactory.initElements(driver, this.nextpage);
	}
	public void Next_Page(Hashtable<String, String> data) throws InterruptedException {
		
		
		mufun(data);
	}
	
	public void mufun(Hashtable<String, String> data) throws InterruptedException {
	
		String pindex =  data.get("pageNo");
		 int pnindex = Integer.parseInt(pindex)-1;
		 String comp =  data.get("comp");
		 String actId =data.get("actId");
		 
		try {
			
			driver.switchTo().defaultContent();
			System.out.println(" actd:"+actd);
			
			if(actd != actId) {
				Thread.sleep(1000);	
				type(nextpage.actId, actId);	
				Thread.sleep(2000);	
				click(nextpage.selectbtn);		
				driver.findElement(By.xpath("//*[@id='ext-gen40']"));		
				List<WebElement> allOptions = driver.findElements(By.xpath("//*[@id='ext-gen41']//div[1]")); //get all the options from the dropdown
				Thread.sleep(2000);
				for(WebElement option : allOptions) {   
				if (option.getText().equals("Activity")) 
				{
				option.click();
				}}		
				
				click(nextpage.gobtn);
				
				Thread.sleep(2000);
				Select pageindex = new Select(nextpage.dropdown);		
				pageindex.selectByIndex(pnindex);
			
				//!driver.findElement(By.xpath("//a/span[contains(text(), 'Edit')]")).isDisplayed() ||
				//driver.findElement(By.xpath("//*[@id='activityForm:j_id294:1:j_id296:0:engineBtnLink__1']")).isDisplayed() ||
			
				Thread.sleep(2000);
				//List<WebElement> dynamicElement = driver.findElements(By.xpath("//*[@id='activityForm:j_id294:1:j_id296:0:engineBtnLink__1']"));
				if(driver.findElements(By.xpath("//a/span[contains(text(), 'Edit')]")).size() ==0) 
				{
					driver.findElement(By.xpath("//*[@id='activityForm:j_id294:1:j_id296:0:engineBtnLink__1']")).click();
				System.out.println("Clicked on form!!!!!!!!!!!!!!!!!!!!");
				Thread.sleep(2000);				
				WebElement iframe = driver.findElement(By.tagName("iframe"));
				driver.switchTo().frame(iframe);		
				Thread.sleep(2000);		
				WebElement browse = driver.findElement(By.xpath("//input[@id='editForm:uploadFileWeb']"));
				 browse.sendKeys("E:\\Working_Projects_2021\\test-node\\Samples\\split-json\\src\\assets\\"+comp);
				driver.findElement(By.xpath("//a[@class='k12_orange_positive_btn']")).click();
				driver.switchTo().defaultContent();
				 actd=actId;
				System.out.println("actd: "+actId+ "    actd: "+actd);
				stat=true;
				
				}
				else {
					
					actd=actId;
					System.out.println("Component already Present on the Page!!!");
					stat=false;
					
				//driver.navigate().back();
				}
				
			}
			else {
				
				Thread.sleep(2000);
				Select pageindex = new Select(nextpage.dropdown);		
				pageindex.selectByIndex(pnindex);
				Thread.sleep(2000);	
				System.out.println("Clicking gaurav on form!!!!!!!!!!!!!!!!!!!!");
				if(driver.findElements(By.xpath("//a/span[contains(text(), 'Edit')]")).size() ==0) 
				{
				driver.findElement(By.xpath("//*[@id='activityForm:j_id294:1:j_id296:0:engineBtnLink__1']")).click();
				System.out.println("Clicked on form!!!!!!!!!!!!!!!!!!!!");
				Thread.sleep(2000);
				
				WebElement iframe = driver.findElement(By.tagName("iframe"));
				driver.switchTo().frame(iframe);		
				Thread.sleep(2000);		
				WebElement browse = driver.findElement(By.xpath("//input[@id='editForm:uploadFileWeb']"));
				 browse.sendKeys("E:\\WORKING\\Learning2021\\AduAcademylatest\\src\\test\\resources\\assets\\"+comp);
				driver.findElement(By.xpath("//a[@class='k12_orange_positive_btn']")).click();
				driver.switchTo().defaultContent();
				System.out.println("actd 2----: "+actId+ "    actd 2----:"+actd);
				 actd=actId;
				 stat=true;
			}
				else {
					actd=actId;
					System.out.println("Same Activity Component already Present on the Page!!!");
					stat=false;
					
				//driver.navigate().back();
				}
			}
			
		}
		catch(org.openqa.selenium.StaleElementReferenceException ex)
		{
			//driver.findElement(By.xpath("//*[@id='activityForm:j_id294:1:j_id296:0:engineBtnLink__1']")).click();
			System.out.println("Not clicked on form!!!!!!!!!!!!!!!!!!!!");
			//Thread.sleep(3000);			
			//WebElement iframe = driver.findElement(By.tagName("iframe"));
			//driver.switchTo().frame(iframe);			
			//Thread.sleep(2000);
			//WebElement browse = driver.findElement(By.xpath("//input[@id='editForm:uploadFileWeb']"));
			// browse.sendKeys("E:\\Working_Projects_2021\\test-node\\Samples\\split-json\\src\\assets\\"+comp);
			//driver.findElement(By.xpath("//a[@class='k12_orange_positive_btn']")).click();
			
		}
	}
}
