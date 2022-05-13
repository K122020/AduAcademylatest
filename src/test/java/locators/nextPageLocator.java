package locators;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class nextPageLocator {
	
		
	@FindBy(xpath="//input[@id='ext-comp-1004']")
	public WebElement actId;
	/*
	 * public WebElement Lg_actId() { return actId; }
	 */
	@FindBy(xpath="//*[@id='ext-comp-1005']")
	public WebElement selectbtn;
	
	
	@FindBy(xpath="//*[@id='ext-comp-1006']")
	public WebElement gobtn;
	
	@FindBy(xpath="//*[@id='activityForm:pageSequenceNumberSelect']")
	public WebElement dropdown;
	
	@FindBy(xpath="//*[@id='activityForm:masterObjectiveId']")
	public WebElement usmoid;
	
	@FindBy(xpath="//*[@id='activityForm:addBtnLink']")
	public WebElement usmobtn;
}
