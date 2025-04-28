package FrameworkDesign.StepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import FrameworkDesign.PageObjects.CartPage;
import FrameworkDesign.PageObjects.CheckOutPage;
import FrameworkDesign.PageObjects.ConfirmationPage;
import FrameworkDesign.PageObjects.LandingPage;
import FrameworkDesign.PageObjects.ProductCatalogue;
import FrameworkDesign.TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest{

	public  LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public CartPage cartPage;
	public  CheckOutPage checkOut;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce page")
	public void landed_on_Ecommerce_page() throws IOException
	{
		 landingPage = launchApplication();
	}
	// mention(.+) meaning regular expn ie takes arugument of any type 
	// ^ mention at start $ mention at last 
	// ^ $ means, @Given has some regular expression
	@Given("^Logged in with username (.+) and password (.+)$") 
	public void Logged_in_with_username_and_password(String username, String password)
	{
		productCatalogue = landingPage.loginApplication(username, password);
	}
	
	@When("^I add product (.+) to the cart$")
	public void add_product_to_cart(String productName) throws InterruptedException
	{
		productCatalogue.addingProductToCart(productName);
	}
	
//	@When("^I checkout (.+) and submit the order$") - can use this as well. can use before annotation of AND
	@And("^I checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) 
	{
		cartPage = productCatalogue.goToCart();
		boolean match = cartPage.crossCheckProduct(productName);
		Assert.assertTrue(match);
		CheckOutPage checkOut = cartPage.goToCheckOut();
		checkOut.fillingCardDetails("1111222233334444", "293", "GeetaKishor");
		checkOut.countrySelection("India");
		confirmationPage =checkOut.sumbitOrder();
	}
	 
	 @Then ("{string} message is displayed on the confirmationPage")
	 public void message_displayed_confimationPage(String string)
	 {
		boolean orderMsg = confirmationPage.orderSuccessMessage(string);
		Assert.assertTrue(orderMsg);
		driver.close();
	 }
	 
	 @Then ("{string} message is displayed")
	    public void Error_messsage_displayed(String string)
		 {
			Assert.assertEquals(string, landingPage.getErrorMessage());	
			driver.close();
		 }
	
	
	
	
	
}
