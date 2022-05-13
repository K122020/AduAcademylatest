package generics;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import com.mcmena.ExtentManager.ExtentManager;
import generics.Constants;
import com.mcmena.listeners.WebEventListener;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;


public class CommonLibs {
	public static final String addtobasket = null;
	public static WebDriver driver = null;
	public static String countryCode = null;
	public static String countryCodeArabic = null;
	public static String brandName = null;
	public static String language = null;
	public static String brw = null;
	public static String Payment_Method = null;
	public static String existingUrl = null;
	WebDriverWait wait = null;
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\Testdata.xlsx");
	public static ExcelReader writeOrderNoexcel = new ExcelReader(System.getProperty("user.dir") +
			"\\src\\test\\resources\\TestData\\OrderStatus.xlsx");
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static String OrderNo;
	public static String screenshotPath;
	public static String screenshotName;
	public static String validSize="2";
	public static int validQty= 1;
	public  int rowValue=1;
	public static String BrandNameForSheet;
	public static String marketNameforSheet;
	
	
	
	Hashtable<String, String> data = new Hashtable<String, String>();
	String filepath = Constants.EXCEL_PATH;
	XSSFSheet shtobj;
	
	// AddressDetails addressdetails = new AddressDetails();

	// ================================ *****WebDriver******
	
	public static WebDriver getDriverObject(String browser, String url) throws InterruptedException {
		System.out.println("Will launch : "+ browser);
		switch (browser) {
		case "firefox":

			WebDriverManager.firefoxdriver().setup();
			/*System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\Drivers\\geckodriver.exe");*/
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			break;
		case "ie":

			WebDriverManager.iedriver().setup();
			/*System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\Drivers\\IEDriverServer.exe");
			*/
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
			break;

		case "chrome":
			/*System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\Drivers\\chromedriver.exe");*/
			
			WebDriverManager.chromedriver().setup();
			//driver.manage().deleteAllCookies();
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("profile.default_content_setting_values.media_stream_camera", 1);
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-infobars");
			//options.addArguments("--incognito");
			
			//Additional options
			options.addArguments("chrome.switches", "--disable-extensions");
			options.addArguments("--start-maximized");
			options.addArguments("--disable-popup-blocking");
			options.addArguments("test-type", "start-maximized", "no-default-browser-check");

			driver = new ChromeDriver(options);
			log.debug("Chrome is launching!!!");
			
			
			break;
		}
		
		
		waitForPageToLoad();
		
		e_driver = new EventFiringWebDriver(driver);
		//Now create the object of EventListnerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
			
		 driver.manage().deleteAllCookies();
		    /*driver.get("chrome://settings/clearBrowserData");
		    driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);*/
		 	log.debug("Get the URL :-"+url);
		 	driver.get(url);
		 	
			
		return driver;
	}
	
	public static String getCountryName()
	{
		String currency = null;
		try {
			System.out.println("Get the Country Name only in English Language!!!");
			
			//Get market name from here
			WebElement element = driver.findElement(By.xpath("//html[@lang='en']/body"));
			 currency = element.getAttribute("gtm-currency");
			System.out.println(currency);
			
			//Get Brand name from here
			BrandNameForSheet = driver.findElement(By.xpath("//*[@id='block-branding']//img")).getAttribute("alt");
			marketNameforSheet = currency;
			return currency;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			return currency;
		}
	}
	
	
	public static String getCountryNameArabic() throws InterruptedException
	{
		String currency = null;
		try {
			driver.findElement(By.cssSelector("#block-languageswitcher > ul:nth-child(1) > li:nth-child(1) > a")).click();
			Thread.sleep(5000);
			
			WebElement element = driver.findElement(By.xpath("//html[@lang='en']/body"));
			 currency = element.getAttribute("gtm-currency");
			System.out.println(currency);
			
			
			//For brand Name
			String title = driver.getTitle();
			String[] arrBrand = title.split("\\| +");
			String[] arr2 = arrBrand[1].split(" ");
			brandName = arr2[0];
			System.out.println(brandName);
			
			driver.findElement(By.cssSelector("#block-languageswitcher > ul:nth-child(1) > li:nth-child(2) > a:nth-child(1)")).click();
			return currency;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
		return currency;
		
	}
	
	public boolean isloadComplete(WebDriver driver)
	{
	    return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("loaded")
	            || ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
	}
	
	/**
	 * Move the screenshots inside Archivescreenshots folder
	 * And delete the exists screenshots folder with existed screenshots.
	 * 
	 */
	public void archiveScreenshots() {

		//String reportName = "TestReport.html";

		String lastScreenshotsFilePath = "./test-output/Screenshots";
		String archiveScreenshotsPath = System.getProperty("user.dir") + "/src/test/resources/archiveScreenshots/";

		Date date = new Date();
		SimpleDateFormat dateFormate = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String formatedDate = dateFormate.format(date);
		String archiveScreenshotsName = formatedDate; //+ "_" + reportName;

		File oldReport = new File(lastScreenshotsFilePath);

		File newFile = new File(archiveScreenshotsPath + archiveScreenshotsName);

		System.out.println(oldReport.exists());

		if (oldReport.exists()) {
			System.out.println("inside if");
			oldReport.renameTo(newFile);
			oldReport.delete();
			
		}
			Reporter.log("Archive the screenshots successfully!!!", true);
			log.debug("Archive the screenshot successfully!!!");
			//test.log(LogStatus.INFO, "Successfully Archived the existing screenshots: Path is-->>src/test/resources/archiveScreenshots");
	}

	
	/**
	 * 
	 * wait for 20 seconds
	 * 
	 */
	public static void waitForPageToLoad() {
		driver.manage().timeouts().implicitlyWait(Constants.MAX_TIME_LOAD, TimeUnit.SECONDS);
	}

	/**
	 * 
	 * @param xpath
	 * @return 
	 */
	public void waitForXpath(String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, Constants.MAX_TIME_LOAD);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		test.log(LogStatus.INFO, "Wait for presence of Element Located and then click the element!!!");
	}
	
	public WebElement waitForXpath1(String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, Constants.MAX_TIME_LOAD);
		test.log(LogStatus.INFO, "Wait for presence of Element Located and then click the element!!!");
		return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		
	
	}
	
	/**
	 * 
	 * @param link
	 */
	public void waitForLink(String link) {
		WebDriverWait wait = new WebDriverWait(driver, Constants.MAX_TIME_LOAD);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(link)));
		test.log(LogStatus.INFO, "Wait for presence of Element Located and then click the Link element!!!");
	}

	// ================================ *****Select Tag******
	// =======================================

	/**
	 * 
	 * @param element
	 * @param value
	 */
		public void selectList(WebElement element, String value) throws InterruptedException {
		try {
			
			Select sel = new Select(element);
			Thread.sleep(1000);
			sel.selectByValue(value);
			Reporter.log("selected Value = " + value, true);
			test.log(LogStatus.INFO, "Select the value :  "+ value);

		} catch (Exception ex) {
			Reporter.log("Value is not available in drop down");

		}
	}

	public void selectList(WebElement element) throws InterruptedException {
		try {

			Select sel = new Select(element);
			Thread.sleep(1000);
			sel.selectByValue("2");
			Reporter.log("selected Value = 2", true);
			test.log(LogStatus.INFO, "Select the value :  2");
			
		} catch (Exception ex) {
			Reporter.log("Value is not available in drop down");
		}
	}

	/**
	 * 
	 * @param xpath
	 * @param Index
	 */
	public void selectListByIndex(WebElement element, Integer Index) {
		
		Select sel = new Select(element);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		sel.selectByIndex(Index);
		test.log(LogStatus.INFO, "Select the value : " + Index);
	}

	/**
	 * 
	 * @param xpath
	 * @param VisibleText
	 * @throws InterruptedException 
	 */
	public void selectListByVisibleText(WebElement ele, String VisibleText) throws InterruptedException {
		
		Select sel = new Select(ele);
		Thread.sleep(1000);
		sel.selectByVisibleText(VisibleText);
		Reporter.log("selected Value = " + VisibleText, true);
		test.log(LogStatus.INFO, "Select the value :  "+ VisibleText);
	}

	/**
	 * 
	 * @param element
	 * 
	 */
	public void deSelectAll(WebElement ele) {
		
		Select sel = new Select(ele);
		sel.deselectAll();
	}

	/**
	 * 
	 * @param xpath
	 * @param value
	 */
	public void deSelectByValue(WebElement ele, String value) {
		
		Select sel = new Select(ele);
		sel.deselectByValue(value);
	}

	/**
	 * 
	 * @param xpath
	 * @param index
	 */
	public void deSelectByIndex(WebElement ele, Integer index) {
		
		Select sel = new Select(ele);
		sel.deselectByIndex(index);
	}

	/**
	 * 
	 * @param xpath
	 * @param text
	 */
	public void deSelectListByVisibleText(WebElement ele, String VisibleText) {
		
		Select sel = new Select(ele);
		sel.deselectByVisibleText(VisibleText);
	}

	// ================================ *****Action Class******
	// =======================================

	/**
	 * 
	 * @param xpath
	 */
	public void actionPerform(WebElement ele) {
		
		Actions act = new Actions(driver);
		act.moveToElement(ele).click().perform();
		test.log(LogStatus.INFO, "Clicking on an element");
		
		
	}
	
		
	public static void click(WebElement ele) {

		
		try {
			/*log.debug("Clicking on an Element : "+ele);
			test.log(LogStatus.INFO, "Clicking on : " + ele);*/
			ele.click();
			
		} catch (Exception e) {
			
			Assert.fail("Element is not found!!!");
		}
		
	}
	
	
	public static void type(WebElement element, String value) {
		
		try {
			
			log.debug("entered value as : "+value);
			test.log(LogStatus.INFO, " entered value as " + value);
			element.clear();
			element.sendKeys(value);

		} catch (Exception e) {
			Assert.fail("Element is not found for typing");		
			}
		

	}
	
	public void actionMouseMove()
	{
		Actions action = new Actions(driver);
		action.moveByOffset(200, 0).perform();
	}
	
	public void actionScrollDown() {
		Actions act = new Actions(driver);
		act.sendKeys(Keys.PAGE_DOWN).build().perform();
	}
	
	public void actionScrollUp() {
		Actions act = new Actions(driver);
		act.sendKeys(Keys.PAGE_UP).build().perform();
	}

	

	/**
	 * 
	 * @param xpath1
	 * @param xpath2
	 * 
	 */
	public void actionDragAndDrop(WebElement ele1, WebElement ele2) {
		
		Actions act = new Actions(driver);
		act.dragAndDrop(ele1, ele2).perform();
	}

	/**
	 * 
	 * @param xPath1
	 * @param xPath2
	 */
	public void actionPerform(WebElement ele1, WebElement ele2) {
		
		Actions act = new Actions(driver);
		act.moveToElement(ele1).perform();
		act.moveToElement(ele2).perform();
		test.log(LogStatus.INFO, "Clicking on an element  : " + ele1);
		test.log(LogStatus.INFO, "Clicking on an element  : " + ele2);
	}

	// ================================ *****Windows Handling******
	// =======================================

	/**
	 * 
	 */
	public void singleWindowHandling() {
		Set<String> set = driver.getWindowHandles();
		Iterator<String> itr = set.iterator();
		String parent = itr.next();
		String child = itr.next();
		driver.switchTo().window(child);
	}

	// ================================ *****Alert Handling******
	// =======================================
	public String AlertHandler(WebDriver drv, int action) throws InterruptedException {

		wait = new WebDriverWait(drv, Constants.MAX_TIME_ALERT);
		String alertText = null;
		if (wait.until(ExpectedConditions.alertIsPresent()) != null) {

			try {
				Alert alert = drv.switchTo().alert();
				Thread.sleep(1000);

				switch (action) {

				case 1:
					alertText = alert.getText();
					alert.accept();
					break;

				case 2:
					alertText = alert.getText();
					alert.dismiss();
					break;

				default:
					alertText = alert.getText();
					break;
				}
			} catch (Exception exe) {
				exe.getMessage();
			}
		}

		else {
			System.out.println("Alert is not found");
		}

		return alertText;
	}

	protected void select(WebElement element, String visibleText) {
		elementHighlight(element);
		org.openqa.selenium.support.ui.Select selection = new org.openqa.selenium.support.ui.Select(element);
		selection.selectByVisibleText(visibleText);
	}

	protected void select(WebElement element, int index) {
		elementHighlight(element);
		org.openqa.selenium.support.ui.Select selection = new org.openqa.selenium.support.ui.Select(element);
		selection.selectByIndex(index);
	}

	
	public void sleep(int seconds) {
		try {
			
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			e.getMessage();
		}
	}
	public void sleeph(int seconds) {
		try {
			Thread.sleep(seconds * 1000);			
		} catch (InterruptedException e) {
			e.getMessage();
		}
	}

	public boolean verifyPresenceOfElement(WebElement element) {
		try {
			if (element.isDisplayed())
				elementHighlight(element);
			return element.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}

	}

	public boolean verifyAbsenceOfElement(WebElement element) {
		try {
			if (element.isDisplayed())
				return false;
			return (!element.isDisplayed());
		} catch (NoSuchElementException e) {
			return true;
		}

	}

	public void highlightAndClick(WebElement element) throws IOException {
		elementHighlight(element);
		// screenshot();
		// element.click();
		waitForPresenceOfElementAndClick(element);

	}

	public void waitForPresenceOfElementAndClick(WebElement element) {
		Wait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		elementHighlight(element);
		element.click();
	}

	public void waitForPresenceOfElement(WebElement element) {
		Wait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForAjaxCallToFinish() {

		try {
			if (driver instanceof JavascriptExecutor) {
				JavascriptExecutor jsDriver = (JavascriptExecutor) driver;
				int i;
				while (true) {
					Boolean ajaxIsComplete = (Boolean) (jsDriver).executeScript("return jQuery.active == 0");
					if (ajaxIsComplete)
						break;
				}
				Thread.sleep(1000);
			}

			else {
				System.out.println("Web driver: " + driver + " cannot execute javascript");
			}
		} catch (InterruptedException e) {
			System.out.println(e);
		}
	}

	public void screenshotB() throws IOException {

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {

			DateFormat dateFormat = new SimpleDateFormat("HH_mm_ssa yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();

			FileUtils.copyFile(src, new File("C:\\Users\\kumar.marrireddy\\workspace\\MCM\\screenshot\\"
					+ dateFormat.format(cal.getTime()) + System.currentTimeMillis() + "text.png"));
		}

		catch (IOException e)

		{

			System.out.println(e.getMessage());

		}

	}

	public static String Filename;
	public void screenshot() throws IOException, InterruptedException {
		try {
			Throwable t = new Throwable();
			StackTraceElement[] elements = t.getStackTrace();

			String className = elements[2].getClassName();

			System.out.println("classname= " + className);
			// ---------------------------------------------------------------------------

			Thread.sleep(1000);
			Robot robot = new Robot();
			String format = "png";
			String filepath = Constants.Screenshot_PATH;
			
			//Created new file directory if file directory is not available.
			File file = new File(filepath);
			if (!file.exists()) {
				System.out.println("File created " + file);
				file.mkdir();
			}


			String separator = ".";

			int pos = className.lastIndexOf(separator);
			
			System.out.println("pos :"+pos);
			String refNo = (className.substring(pos + separator.length())).trim();
			System.out.println("refNo : "+ refNo);

			//String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			
			Date d = new Date();
			String timestamp = d.toString().replace(":", "_").replace(" ", "_") + ".png";

			Filename = refNo + "_" + timestamp;

			String File = filepath + "/" + Filename;

			System.out.println("Filename=" + Filename);
			System.out.println("File=" + File);

			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
			ImageIO.write(screenFullImage, format, new File(File));

			System.out.println("A full screenshot saved!");
			String Fileref = "./Screenshots/" + Filename;

			System.out.println("Fileref=" + Fileref);

			Reporter.log("<a href=\"" + Fileref + "\">  Screen Shot of Failed step </a>");
			test.log(LogStatus.INFO, "Captured the Screenshots");

		} catch (Exception ex) {
			ex.getMessage();
		}
	}

	public void elementHighlight(WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='2px solid red'", element);
		// ss();

		js.executeScript("arguments[0].style.border=''", element);
	}

	public void mouseOver(WebElement element) throws IOException, InterruptedException {
		Actions action = new Actions(driver);
		elementHighlight(element);
		action.moveToElement(element).perform();
		action.moveToElement(element).build().perform();
		screenshot();
	}
	
		

	public void waitTime(int i) {
		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) {
			e.getMessage();
		}
	}

	public void navigateBack() {
		driver.navigate().back();
	}

	// ...................................................................................

	public void textEquals(String actual, String expected) {
		if (actual.equals(expected)) {
			Reporter.log(actual + " Verified", true);
		} else {
			Reporter.log(actual + " Failed to verify", true);
		}
	}

	public void textContains(String actual, String expected) {
		if (actual.contains(expected)) {
			Reporter.log(expected + " Verified", true);
		} else {
			Reporter.log(expected + " Failed to verify", true);
		}
	}

	public void selectByValue(WebElement element, String value) {
		elementHighlight(element);
		Select selection = new Select(element);
		selection.selectByValue(value);
	}

	public void selectByText(WebElement element, String Text) {
		elementHighlight(element);
		Select selection = new Select(element);
		selection.selectByVisibleText(Text);
	}

	// ............................................................................................

	@DataProvider(name="dp")
	public Object[][] getData(Method m) {

		String sheetName = m.getName();
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 1][1];
		
		Hashtable<String,String> table = null;

		for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2

			table = new Hashtable<String,String>();
			
			for (int colNum = 0; colNum < cols; colNum++) {

				// data[0][0]
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum - 2][0] = table;
			}
		}
		return data;
	}
	
	
	

	public static void captureScreenshot() throws IOException {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

		FileUtils.copyFile(scrFile,
				new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\" + screenshotName));

		test.log(LogStatus.INFO, "Captured the Screenshots");
	}
	
public static boolean isTestRunnable(String testName, ExcelReader excel) {
		
		String sheetName= "Footlocker";
		int rows = excel.getRowCount(sheetName);
		
		
		for(int rNum=2; rNum<=rows; rNum++){
			
			String testCase = excel.getCellData(sheetName, "TCID", rNum);
			
			if(testCase.equalsIgnoreCase(testName))
			{
				
				String runmode = excel.getCellData(sheetName, "Runmode", rNum);
				//String brw = excel.getCellData(sheetName, "Browser", rNum);
				
				if(runmode.equalsIgnoreCase("Y"))
				
					{
					//getDriverObject(brw);
						return true;
					}
				
				else
					return false;
			}
			
			
		}
		return false;
	}


public static String isTestBrand() throws InterruptedException
{
	
	String sheetName="brandName";
	String testCase ="HNM_Default";
	int rows = excel.getRowCount(sheetName);
	
	
	for(int rNum=2; rNum<=rows; rNum++)
	{
		
		String testCase1 = excel.getCellData(sheetName, "Brand", rNum);
		
		
			String runmode = excel.getCellData(sheetName, "Runmode", rNum);
			
			
			if(runmode.equalsIgnoreCase("Y"))
			{
				
				System.out.println(testCase1);
				return testCase1;
				
			}
			else
				System.out.println("RunMode is N "+testCase1);
				
			
		}
			
	return testCase;
}

public String getlanguage()
{
	System.out.println("Call the getLanguage method");
	String lang;
	
	String currentURL = driver.getCurrentUrl();
	String[] arr = currentURL.split("/");
	lang = arr[3];
	System.out.println("language is :-" +lang);
	
	return lang;
}


public void archiveOrderStatusReport() throws IOException {

	String OrderreportName = "OrderStatus.xlsx";

	String lastFilePath = System.getProperty("user.dir") + "/src/test/resources/TestData/OrderStatus.xlsx";
	String archivePath = System.getProperty("user.dir") + "/src/test/resources/archiveOrderStatusReport/";

	Date date = new Date();
	SimpleDateFormat dateFormate = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
	String formatedDate = dateFormate.format(date);
	String archiveOrderStatusName = formatedDate + "_" + OrderreportName;

	File oldReport = new File(lastFilePath);

	File newFile = new File(archivePath + archiveOrderStatusName);

	System.out.println(oldReport.exists());

	if (oldReport.exists()) {
		System.out.println("inside if");
		
		Files.copy(oldReport.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		//oldReport.renameTo(newFile);
		//oldReport.delete();
		
	}
		Reporter.log("Archive the OrderStatus Report successfully!!!", true);
		log.debug("Archive the OrderStatus Report successfully!!!");
		//test.log(LogStatus.INFO, "Successfully Archived the existing screenshots: Path is-->>src/test/resources/archiveScreenshots");
}



public void archiveExtentReport() throws IOException {

	String reportName = "extent.html";

	String lastFilePath = System.getProperty("user.dir") + "/target/surefire-reports/html/extent.html";
	String archivePath = System.getProperty("user.dir") + "/src/test/resources/archiveExtentReports/";

	Date date = new Date();
	SimpleDateFormat dateFormate = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
	String formatedDate = dateFormate.format(date);
	String archiveReportsName = formatedDate + "_" + reportName;

	File oldReport = new File(lastFilePath);

	File newFile = new File(archivePath + archiveReportsName);

	System.out.println(oldReport.exists());

	if (oldReport.exists()) {
		System.out.println("inside if");
		
		Files.copy(oldReport.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		//oldReport.renameTo(newFile);
		//oldReport.delete();
		
	}
		Reporter.log("Archive the Extent Report successfully!!!", true);
		log.debug("Archive the Extent Report successfully!!!");
		//test.log(LogStatus.INFO, "Successfully Archived the existing screenshots: Path is-->>src/test/resources/archiveScreenshots");
}






	
}
