package FrameworkDesign.AbsrtactComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import FrameworkDesign.PageObjects.CartPage;

public class AbstractComponent {

	WebDriver driver;
	public AbstractComponent(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="button[routerlink*='cart']")
	WebElement goToCart;
	
	@FindBy(css="button[routerlink*='myorders']")
	WebElement goToOrders;
	
	
	public void waitForElementToAppear(By FindBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(FindBy));
	}
	
	public void waitForWebElementToAppear(WebElement FindBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(FindBy));
	}
	public void waitForWebElementToDisappear(WebElement spinner) throws InterruptedException
	{
		Thread.sleep(3000);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.invisibilityOf(spinner));
	}

	public CartPage goToCart()
	{
		goToCart.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	
	}
	
	public OrdersPage goToOrders()
	{
		goToOrders.click();
		OrdersPage ordersPage = new OrdersPage(driver);
		return ordersPage;
	
	}
	
}
