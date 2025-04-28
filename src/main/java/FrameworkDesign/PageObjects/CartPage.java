package FrameworkDesign.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FrameworkDesign.AbsrtactComponents.AbstractComponent;

public class CartPage extends AbstractComponent{

	WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".cartSection h3")
	List<WebElement> cartProduct;
	
	@FindBy(css=".totalRow button")
	WebElement checkOut;
	
	public boolean crossCheckProduct(String productName)
	{
		boolean match = cartProduct.stream().anyMatch(product->product.getText().equals(productName));
		return match;
	}
	
	public CheckOutPage goToCheckOut()
	{
		checkOut.click();
		CheckOutPage checkOut = new CheckOutPage(driver);
		return checkOut;
	}
	
	
}
