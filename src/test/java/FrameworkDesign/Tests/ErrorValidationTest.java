package FrameworkDesign.Tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import FrameworkDesign.PageObjects.CartPage;
import FrameworkDesign.PageObjects.ProductCatalogue;
import FrameworkDesign.TestComponents.BaseTest;
import FrameworkDesign.TestComponents.Retry;


public class ErrorValidationTest extends BaseTest{

	
	@Test(groups = {"errorhandling"}, retryAnalyzer=Retry.class)
	public void LoginPageErrorvalidation() throws IOException, InterruptedException
	{
		landingPage.loginApplication("Geeta@gmail.com", "PassZ@1007");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());		
	}
	
	@Test
	public void ProductPageErrorvalidation() throws IOException, InterruptedException
	{
		ProductCatalogue productCatalogue = landingPage.loginApplication("Geeta@gmail.com", "Pass@1007");
		productCatalogue.addingProductToCart("ADIDAS ORIGINAL");
		CartPage cartPage = productCatalogue.goToCart();
		boolean match = cartPage.crossCheckProduct("ADIDAS ORIGINALS");
		Assert.assertFalse(match);
	}
}
