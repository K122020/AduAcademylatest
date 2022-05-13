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
import generics.ExcelReader;
import locators.nextPageLocator;


public class NextPage extends CommonLibs {
	public nextPageLocator nextpage;
	//int pindex=0;
	//String actId="892181";
	static String actd;
	public static boolean stat;
	public static boolean stat1;
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\Testdata.xlsx");
	public NextPage()

	{
		this.nextpage = new nextPageLocator();
		PageFactory.initElements(driver, this.nextpage);
	}
	public void Next_Page(Hashtable<String, String> data) throws InterruptedException {


		//	mufun(data);
		publish_brk(data);
		//published(data);
	}

	
	public void publish_brk(Hashtable<String, String> data) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver,120);	
		Actions actions = new Actions(driver);		
		String pindex =  data.get("pageNo");
		int pnindex = Integer.parseInt(pindex)-1;
		String comp =  data.get("comp");
		String actId =data.get("actId");

		try {	
			driver.switchTo().defaultContent();
			System.out.println(" actd:"+actd);
			if(actd != actId) {
				driver.switchTo().defaultContent();
				Thread.sleep(2000);	
				type(nextpage.actId, actId);	
				Thread.sleep(1000);	
				String actstr = driver.findElement(By.xpath("//div[@id='ext-gen38']/input[@id='gotoDestination']")).getAttribute("value");
				System.out.println("=====actstr===getAttribute======"+actstr+"====");
				//value="ACTIVITIES"
				String strvalue="ACTIVITIES";
				if(actstr.length() != strvalue.length()) {
					System.out.println("=====actstr="+actstr.length()+"==getAttribute======"+strvalue.length()+"====");
					click(nextpage.selectbtn);		
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
				click(wait.until(ExpectedConditions.elementToBeClickable(nextpage.gobtn)));

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
					Thread.sleep(10000);	
					// here coming soon code
					
					
					mufun_update(data);
					//mufun_up2(data);
					actd=actId;
									

				}
				else {
					// here coming soon code
					mufun_update(data);
					//mufun_up2(data);
					actd=actId;
				}

			}	
			else {
				// here coming soon code
				mufun_update(data);
				//mufun_up2(data);
				actd=actId;
			}





		}
		catch(org.openqa.selenium.StaleElementReferenceException ex)
		{

			System.out.println("Not clicked on form!!!!!!!!!!!!!!!!!!!!");


		}
	}	
		
	public void mufun_update(Hashtable<String, String> data) throws InterruptedException {

		String pindex =  data.get("pageNo");
		int pnindex = Integer.parseInt(pindex)-1;
		String comp =  data.get("comp");
		String actId =data.get("actId");
		String Status =  data.get("Status");

		try {
			Thread.sleep(2000);
			
			Select pageindex = new Select(nextpage.dropdown);		
			pageindex.selectByIndex(pnindex);
			Thread.sleep(2000);	
			String CALMSvaltxt=driver.findElement(By.xpath("//*[@id='activityForm:pageNum']")).getText();
			System.out.println(CALMSvaltxt+ " :Value1 of CALMS Page "+ pindex+" : datavalue1");
			int pndex = Integer.parseInt(pindex);
			int clmtxt= Integer.parseInt(CALMSvaltxt);
			Thread.sleep(2000);
			if(comp!="") {
				Thread.sleep(1000);
			if(pndex==clmtxt) {			
				System.out.println(clmtxt+ " : IN Value of CALMS Page "+ pndex+" : IN datavalue");
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
				actd=actId;
				System.out.println("actd: "+actId+ "    actd: "+actd);
				stat=true;

			}
			else {
				driver.findElement(By.cssSelector("span[id='activityForm:pageViewPanel'] > div:nth-child(2) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > div:nth-child(1) > a:nth-child(1)")).click();
				System.out.println("Clicking on form!!!!!!!!!!!!!!!!!!!!");
				Thread.sleep(2000);				
				WebElement iframe = driver.findElement(By.xpath("//iframe[@id='editSectionIMP']"));
				driver.switchTo().frame(iframe);		
				Thread.sleep(2000);					
				driver.findElement(By.xpath("//*[@id='editForm:deleteConfigFileWeb']")).click();
				driver.switchTo().alert().accept();
				Thread.sleep(2000);	
				WebElement browse = driver.findElement(By.xpath("//input[@id='editForm:uploadFileWeb']"));
				browse.sendKeys("E:\\WORKING\\Learning2021\\AduAcademylatest\\src\\test\\resources\\assets\\"+comp);
				driver.findElement(By.xpath("//a[@class='k12_orange_positive_btn']")).click();
				driver.switchTo().defaultContent();

				actd=actId;
				System.out.println("Component already Present on the Page!!!");
				stat=true;

				//driver.navigate().back();
			}
			}
			else {
				actd=actId;
				System.out.println("CLAMS page# not match!!!");
				System.out.println(CALMSvaltxt+ " :Value2 of CALMS Page "+ pindex+" : datavalue2");
				stat=false;
				//publish_brk(data);
			}

		}
		
		else {
			WebElement dselement = driver.findElement(By.xpath("//div/span[contains(text(), 'Design Specs')]"));
			String dstxt =dselement.getText();
			System.out.println("DS Text ---> "+dstxt);
			String mdstxt ="Design Specs";
			if(dstxt.length()==mdstxt.length()) {
				actd=actId;
				System.out.println("this page already is blank!!!");				
				stat1=true;
			}
			else {
				
				driver.findElement(By.cssSelector("span[id='activityForm:pageViewPanel'] > div:nth-child(2) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > div:nth-child(1) > a:nth-child(1)")).click();
				System.out.println("Clicking on form!!!!!!!!!!!!!!!!!!!!");
				Thread.sleep(2000);				
				WebElement iframe = driver.findElement(By.xpath("//iframe[@id='editSectionIMP']"));
				driver.switchTo().frame(iframe);		
				Thread.sleep(2000);	
				WebElement myelement  = driver.findElement(By.xpath("//*[@id='editForm:j_id62']"));					
				myelement.click();
				
				driver.findElement(By.id("editForm:j_id64:anchor")).click();
				driver.switchTo().alert().accept();
				Thread.sleep(2000);			
				driver.findElement(By.xpath("//td/a/span[contains(text(), 'Save')]")).click();
				Thread.sleep(5000);	
				//Change Type
				driver.switchTo().defaultContent();
				System.out.println("this page should be blank!!!");
				actd=actId;
				stat1=true;
			}
			
		}
		}
		catch(org.openqa.selenium.StaleElementReferenceException ex)
		{

			System.out.println("Not clicked on form!!!!!!!!!!!!!!!!!!!!");


		}
	}
	public void mufun_update1(Hashtable<String, String> data) throws InterruptedException {

		String pindex =  data.get("pageNo");
		int pnindex = Integer.parseInt(pindex)-1;
		String comp =  data.get("comp");
		String actId =data.get("actId");

		try {
			Thread.sleep(2000);
			Select pageindex = new Select(nextpage.dropdown);		
			pageindex.selectByIndex(pnindex);

			Thread.sleep(2000);	
			String CALMSvaltxt=driver.findElement(By.xpath("//*[@id='activityForm:pageNum']")).getText();
			System.out.println(CALMSvaltxt+ " :Value1 of CALMS Page "+ pindex+" : datavalue1");
			int pndex = Integer.parseInt(pindex);
			int clmtxt= Integer.parseInt(CALMSvaltxt);
			Thread.sleep(2000);
			if(pndex==clmtxt) {			
				System.out.println(clmtxt+ " : IN Value of CALMS Page "+ pndex+" : IN datavalue");
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
				actd=actId;
				System.out.println("actd: "+actId+ "    actd: "+actd);
				stat=true;

			}
			else {
				driver.findElement(By.cssSelector("span[id='activityForm:pageViewPanel'] > div:nth-child(2) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > div:nth-child(1) > a:nth-child(1)")).click();
				System.out.println("Clicking on form!!!!!!!!!!!!!!!!!!!!");
				Thread.sleep(2000);				
				WebElement iframe = driver.findElement(By.xpath("//iframe[@id='editSectionIMP']"));
				driver.switchTo().frame(iframe);		
				Thread.sleep(2000);					
				driver.findElement(By.xpath("//*[@id='editForm:deleteConfigFileWeb']")).click();
				driver.switchTo().alert().accept();
				Thread.sleep(2000);	
				WebElement browse = driver.findElement(By.xpath("//input[@id='editForm:uploadFileWeb']"));
				browse.sendKeys("E:\\WORKING\\Learning2021\\AduAcademylatest\\src\\test\\resources\\assets\\"+comp);
				driver.findElement(By.xpath("//a[@class='k12_orange_positive_btn']")).click();
				driver.switchTo().defaultContent();

				actd=actId;
				System.out.println("Component already Present on the Page!!!");
				stat=true;

				//driver.navigate().back();
			}
			}
			else {
				actd=actId;
				System.out.println("CLAMS page# not match!!!");
				System.out.println(CALMSvaltxt+ " :Value2 of CALMS Page "+ pindex+" : datavalue2");
				stat=false;
				//publish_brk(data);
			}

		}
		catch(org.openqa.selenium.StaleElementReferenceException ex)
		{

			System.out.println("Not clicked on form!!!!!!!!!!!!!!!!!!!!");


		}
	}
	public void mufun_up2(Hashtable<String, String> data) throws InterruptedException {

		String pindex =  data.get("pageNo");
		int pnindex = Integer.parseInt(pindex)-1;
		String comp =  data.get("comp");
		String actId =data.get("actId");

		try {
			Thread.sleep(2000);
			Select pageindex = new Select(nextpage.dropdown);		
			pageindex.selectByIndex(pnindex);

			Thread.sleep(2000);	
			String CALMSvaltxt=driver.findElement(By.xpath("//*[@id='activityForm:pageNum']")).getText();
			System.out.println(CALMSvaltxt+ " :Value1 of CALMS Page "+ pindex+" : datavalue1");
			int pndex = Integer.parseInt(pindex);
			int clmtxt= Integer.parseInt(CALMSvaltxt);
			Thread.sleep(2000);
			if(pndex==clmtxt) {			
				System.out.println(clmtxt+ " : IN Value of CALMS Page "+ pndex+" : IN datavalue");

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
				actd=actId;
				System.out.println("CLAMS page# not match!!!");
				System.out.println(CALMSvaltxt+ " :Value2 of CALMS Page "+ pindex+" : datavalue2");
				stat=false;
			}
		}
		catch(org.openqa.selenium.StaleElementReferenceException ex)
		{

			System.out.println("Not clicked on form!!!!!!!!!!!!!!!!!!!!");


		}
	}
	public void published(Hashtable<String, String> data) throws InterruptedException {

		String actId =data.get("actId");
		System.out.println("Next_Page3 == actId is : "+ actId);

		try {	
			driver.switchTo().defaultContent();
			System.out.println(" actd:"+actd);
			if(actd != actId) {
				driver.switchTo().defaultContent();
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
				String published = driver.findElement(By.xpath("//span[@id='activityHeader_form:titleStatus']")).getText();
				String publish ="Published";
				System.out.println("Get Text: "+published);

				if(published.length() == publish.length()) 
				{
					System.out.println("Status: Already "+published);
					//actd=actId;
					//statb=true;
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
					//statb=true;
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

				Thread.sleep(2000);

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
				System.out.println("Clicking on form!!!!!!!!!!!!!!!!!!!!");
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

			System.out.println("Not clicked on form!!!!!!!!!!!!!!!!!!!!");


		}
	}

}
