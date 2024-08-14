package myporto.test;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import myporto.pageobjectmodel.CartPage;
import myporto.pageobjectmodel.CheckoutPage;
import myporto.pageobjectmodel.ConfirmationPage;
import myporto.pageobjectmodel.ProductCatalog;
import myporto.testcomponent.BaseTest;
import myporto.testcomponent.Retry;

public class ErrorValidation extends BaseTest {
	
	@Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void errorCheck () {
		landingPage.LoginApplication("Test@example.com", "Test123");
		Assert.assertEquals("Incorrect email password.", landingPage.getErrorMessage());
	}
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		String productName = "ZARA COAT 3";
		ProductCatalog productCatalog = landingPage.LoginApplication("Test@example.com", "Test1234");
		List<WebElement> productsList = productCatalog.getProductList();
		productCatalog.addProductToCart(productName);
		CartPage cartPage = productCatalog.goToCartPage();
		
		Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}

}
