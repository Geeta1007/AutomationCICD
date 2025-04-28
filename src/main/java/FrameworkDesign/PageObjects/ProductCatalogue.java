package FrameworkDesign.PageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import FrameworkDesign.AbsrtactComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{

	WebDriver driver;
	public ProductCatalogue(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

			
	@FindBy(css=".col-sm-10")
	List<WebElement> itemList;
	
	@FindBy(css=".ng-trigger")
	WebElement spinner;
	
	By productsBy = By.cssSelector(".col-sm-10");
	By addTocart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productsBy);
		return itemList;
	}
	
	public WebElement getProductByName(String productName )
	{
		WebElement output = getProductList().stream().filter(item->item.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return output;
		
	}
	
	public void addingProductToCart(String productName) throws InterruptedException
	{
		WebElement output =getProductByName(productName);
		output.findElement(addTocart).click();
		waitForElementToAppear(toastMessage);
		waitForWebElementToDisappear(spinner);
		
	}

	

}
