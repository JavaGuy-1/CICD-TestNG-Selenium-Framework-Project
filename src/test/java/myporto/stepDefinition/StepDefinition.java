package myporto.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import myporto.pageobjectmodel.CartPage;
import myporto.pageobjectmodel.CheckoutPage;
import myporto.pageobjectmodel.ConfirmationPage;
import myporto.pageobjectmodel.LandingPage;
import myporto.pageobjectmodel.ProductCatalog;
import myporto.testcomponent.BaseTest;

public class StepDefinition extends BaseTest{
	
	public ProductCatalog productCatalog;
	public LandingPage landingPage;
	public ConfirmationPage confirmationPage;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String email, String password) {
		productCatalog = landingPage.LoginApplication(email, password);
	}
	
	@When("^add product (.+) to cart$")
	public void add_product_to_cart(String productName) throws InterruptedException {
		List<WebElement> productsList = productCatalog.getProductList();
		productCatalog.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName) {
		CartPage cartPage = productCatalog.goToCartPage();
		
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		
		checkoutPage.actionDropdown("Indonesia");
		confirmationPage = checkoutPage.actionSubmit();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_ConfirmationPage(String string) {
		String text = confirmationPage.getText();
		Assert.assertTrue(text.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void error_message_is_displayed(String string) {
		Assert.assertEquals(string, landingPage.getErrorMessage());
		driver.close();
	}

}
