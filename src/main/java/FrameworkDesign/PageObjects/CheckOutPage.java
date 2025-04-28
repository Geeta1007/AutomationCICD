package FrameworkDesign.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FrameworkDesign.AbsrtactComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent{

	WebDriver driver;
	public CheckOutPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="(//form//div//div//div//input[@type='text'])[1]")
	WebElement cardRowDetails;
	
	@FindBy(xpath="(//form//div//div//div//input[@type='text'])[2]")
	WebElement cvvRowDetails;
	
	@FindBy(xpath="(//form//div//div//div//input[@type='text'])[3]")
	WebElement nameRowDetails;
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement countryRowDetails;
	
	@FindBy(xpath="//section//button[@type='button']")
	List<WebElement> countryCollection;
	
	@FindBy(css=".action__submit")
	WebElement sumbit;	
		
	@FindBy(xpath="(//button[contains(@class, 'ta-item')])[2] ")
	WebElement selectCountry;
	
	By countryDisplay = By.xpath("(//button[contains(@class, 'ta-item')])[2] ");
	
	public void fillingCardDetails(String cardNumber, String cvvNumber, String cardName)
	{
		cardRowDetails.clear();
		cardRowDetails.sendKeys(cardNumber);
		cvvRowDetails.sendKeys(cvvNumber);
		nameRowDetails.sendKeys(cardName);
	}
	
	public void countrySelection(String countryName)
	{
		countryRowDetails.sendKeys(countryName);
		countryCollection.stream().filter(s->s.getText().equalsIgnoreCase(countryName)).findFirst().get().click();
	}
	public ConfirmationPage sumbitOrder()
	{
		sumbit.click();
//		ConfirmationPage confirmationPage =new ConfirmationPage(driver);
		return new ConfirmationPage(driver);
	}
	
	
	
//	public void selectCountry(String countryName)
//	{
//		Actions a = new Actions(driver);
//		a.sendKeys(countryRowDetails, countryName).build().perform();	
//		waitForWebElementToAppear(countryDisplay);
//		selectCountry.click();
//		sumbit.click();
//	}
	
	
}
