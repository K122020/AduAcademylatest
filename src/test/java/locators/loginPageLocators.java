package locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class loginPageLocators {
	
	@FindBy(xpath="//*[@id='login:username']")
	public WebElement username;
	public WebElement Lg_username()
	{
		return username;
	}
	
	@FindBy(xpath="//*[@id='login:password']")
	public WebElement password;
	public WebElement Lg_password()
	{
		return password;
	}
	
	@FindBy(xpath="//*[@id='login:submitLink']")
	public WebElement signInbtn;
	public WebElement Lg_signInbtn()
	{
		return signInbtn;
	}
}
