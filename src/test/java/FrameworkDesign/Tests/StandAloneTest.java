package FrameworkDesign.Tests;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import FrameworkDesign.PageObjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		String productName = "ADIDAS ORIGINAL";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		
//		Login--------------------------------------
		driver.findElement(By.id("userEmail")).sendKeys("Geeta@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Pass@1007");
		driver.findElement(By.id("login")).click();
		
//		Selecting item-------------------------------------------------
		List<WebElement> itemList=driver.findElements(By.cssSelector(".col-sm-10"));
		WebElement output = itemList.stream().filter(item->item.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		output.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-trigger"))));
		
//		Clicking cart-----------------------------------------------------------------
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
		
//		cross checking products in the cart-----------------------------------
		List<WebElement> cartProduct = driver.findElements(By.cssSelector(".cartSection h3"));
		
		boolean match = cartProduct.stream().anyMatch(product->product.getText().equals(productName));
		Assert.assertTrue(match);

//		filling card details and country name--------------------------------------------------
		String country = "India";
		driver.findElement(By.cssSelector(".totalRow button")).click();
		driver.findElement(By.xpath("(//form//div//div//div//input[@type='text'])[1]")).clear();
		driver.findElement(By.xpath("(//form//div//div//div//input[@type='text'])[1]")).sendKeys("1111222233334444");
		driver.findElement(By.xpath("(//form//div//div//div//input[@type='text'])[2]")).sendKeys("293");
		driver.findElement(By.xpath("(//form//div//div//div//input[@type='text'])[3]")).sendKeys("GeetaKishor");
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("ind");
		List<WebElement> sel = driver.findElements(By.xpath("//section//button[@type='button']"));
		sel.stream().filter(s->s.getText().equalsIgnoreCase(country)).findFirst().get().click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		
//		*******selecting country using Actions class----------------------------------------------------------
//		Actions a = new Actions(driver);
//		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "ind").build().perform();		
//		driver.findElement(By.xpath("(//button[contains(@class, 'ta-item')])[2] ")).click();
//		driver.findElement(By.cssSelector(".action__submit")).click();
		
//		placing order and grab the order placed text---------------------------------------------------
		boolean orderMsg = driver.findElement(By.tagName("h1")).getText().equalsIgnoreCase("Thankyou for the order.");
		Assert.assertTrue(orderMsg);
		
		
		
	}

}
