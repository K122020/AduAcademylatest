package pages;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import base.CommonLibs;
import generics.Constants;
import locators.loginPageLocators;

public class LoginPage extends CommonLibs {
	
	public loginPageLocators login;
	
	public LoginPage()
	{
		this.login = new loginPageLocators();
		PageFactory.initElements(driver, this.login);
	}
	public void Lms_Login(Hashtable<String, String> data) throws InterruptedException
	{
			
		//sleep(Constants.waittimeBeforeLogin);	
//		type(login.username, Constants.Username);
//		type(login.password, Constants.Password);
		System.out.println(data.get("emailId")+data.get("password") );
		type(login.Lg_username(), data.get("emailId"));
		type(login.Lg_password(), data.get("password"));
		//click(login.captchaClick);
		//sleep(Constants.captchawaittime);
		click(login.Lg_signInbtn());
		//sleep(Constants.waittimeAfterLogin);
		System.out.println("Successfully Login");
		
	}
	
	
	

}
