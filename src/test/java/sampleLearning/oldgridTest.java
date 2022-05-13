package sampleLearning;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class oldgridTest {
	
	 //private RemoteWebDriver driver;
	//static DesiredCapabilities cap=null;	
	//@Test
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
			
		//cap = DesiredCapabilities.firefox();
		//cap.setBrowserName("firefox");
		//cap.setPlatform(Platform.ANY);
		// driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
		//driver.get("https://gmail.com");
//		from selenium.webdriver.firefox.options import Options
//		options = Options()
//		options.add_argument("-profile")
//		# put the root directory your default profile path here, you can check it by opening Firefox and then pasting 'about:profiles' into the url field 
//		options.add_argument("/home/username/snap/firefox/common/.mozilla/firefox/dhklfoeb.default-18236362302398")
//		browser=webdriver.Firefox(options=options)
		 ProfilesIni settings = new ProfilesIni();
	    FirefoxProfile profile = settings.getProfile("user");
		FirefoxOptions opt = new FirefoxOptions();
		opt.setProfile(profile);
		//ChromeOptions opt = new ChromeOptions();
		//http://192.168.0.4:4444
		//http://192.168.29.29:4444
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.0.4:4444"),opt);
		driver.get("http://google.com");
		driver.findElement(By.name("q")).sendKeys("Hi Gaurav");
		Thread.sleep(10000);
		driver.quit();
		
	}

}
