package FrameworkDesign.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FrameworkDesign.AbsrtactComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{

	WebDriver driver;
	public LandingPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

			
	@FindBy(id="userEmail")
	WebElement usermail;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement login;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public ProductCatalogue loginApplication(String mailID, String password)
	{
		usermail.sendKeys(mailID);
		passwordEle.sendKeys(password);
		login.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public String getErrorMessage()
	{	
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}

	
}
