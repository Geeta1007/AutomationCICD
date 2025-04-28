package FrameworkDesign.Tests;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import FrameworkDesign.AbsrtactComponents.OrdersPage;
import FrameworkDesign.PageObjects.CartPage;
import FrameworkDesign.PageObjects.CheckOutPage;
import FrameworkDesign.PageObjects.ConfirmationPage;
import FrameworkDesign.PageObjects.LandingPage;
import FrameworkDesign.PageObjects.ProductCatalogue;
import FrameworkDesign.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest{

	String productName = "ADIDAS ORIGINAL";
	@Test(dataProvider = "getData",groups="Purchase")
	public void SubmitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	{
		
		String countryName = "India";
		String successMessage = "Thankyou for the order.";
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		productCatalogue.addingProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCart();
		boolean match = cartPage.crossCheckProduct(input.get("product"));
		Assert.assertTrue(match);
		CheckOutPage checkOut = cartPage.goToCheckOut();
		checkOut.fillingCardDetails("1111222233334444", "293", "GeetaKishor");
		checkOut.countrySelection(countryName);
		ConfirmationPage confirmationPage =checkOut.sumbitOrder();
//		checkOut.selectCountry(countryName); ------------------------using action class
		boolean orderMsg = confirmationPage.orderSuccessMessage(successMessage);
		Assert.assertTrue(orderMsg);

	}
	
	@Test(dependsOnMethods = {"SubmitOrder"})
	public void OrderHistoryTest()
	{
		ProductCatalogue productCatalogue = landingPage.loginApplication("Geeta@gmail.com", "Pass@1007");
		OrdersPage ordersPage = productCatalogue.goToOrders();
		ordersPage.verifyOrdersProduct(productName);
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//FrameworkDesign//Data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};	
	}
	

//	@DataProvider
//	public Object[][] getData()
//	{
//		return new Object [][] {{"Geeta@gmail.com", "Pass@1007", "ADIDAS ORIGINAL"}, {"Kishor@gmail.com", "Pass@1007", "IPHONE 13 PRO" }};
//	}
	
//	@DataProvider
//	public Object[][] getData()
//	{
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "Geeta@gmail.com");
//		map.put("password", "Pass@1007");
//		map.put("product", "ADIDAS ORIGINAL");
//		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "Kishor@gmail.com");
//		map1.put("password", "Pass@1007");
//		map1.put("product", "IPHONE 13 PRO");
//		
//		return new Object[][] {{map},{map1}};
//	}

}
