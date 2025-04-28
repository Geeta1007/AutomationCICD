package FrameworkDesign.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FrameworkDesign.AbsrtactComponents.AbstractComponent;

public class ConfirmationPage {

	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver)
		{
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
	
	@FindBy(tagName="h1")
	WebElement orderMessage;
	
	public boolean orderSuccessMessage(String successMessage)
	{
		boolean orderMsg = orderMessage.getText().equalsIgnoreCase(successMessage);
		return orderMsg;
	}
}
