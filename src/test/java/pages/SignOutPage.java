package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import base.CommonLibs;
import generics.Constants;
import locators.signoutPageLocator;

public class SignOutPage extends CommonLibs {
	
	public signoutPageLocator signout;
	public SignOutPage()
	
	{
		this.signout = new signoutPageLocator();
		PageFactory.initElements(driver, this.signout);
	}
	
	
public void Logout() {		
		
		driver.switchTo().parentFrame();
		click(signout.logout);
		sleep(Constants.waitpopupAlert);
		driver.switchTo().alert().accept();
		System.out.println("Application has logout !!!");
		
	}

}
