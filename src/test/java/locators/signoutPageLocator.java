package locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class signoutPageLocator {
	
	@FindBy(xpath="//a[contains(text(), 'Log out')]")
	public WebElement logout;	
	

}
