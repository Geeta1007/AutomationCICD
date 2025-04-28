package FrameworkDesign.AbsrtactComponents;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrdersPage extends AbstractComponent {
		
	WebDriver driver;
	
	public OrdersPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> ordersName;
	
	public boolean verifyOrdersProduct(String productName)
	{
		boolean match = ordersName.stream().anyMatch(product->product.getText().equals(productName));
		return match;
	}
		
}
