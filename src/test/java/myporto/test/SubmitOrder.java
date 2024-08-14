package myporto.test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import myporto.pageobjectmodel.CartPage;
import myporto.pageobjectmodel.CheckoutPage;
import myporto.pageobjectmodel.ConfirmationPage;
import myporto.pageobjectmodel.LandingPage;
import myporto.pageobjectmodel.OrderPage;
import myporto.pageobjectmodel.ProductCatalog;
import myporto.testcomponent.BaseTest;
import myporto.testcomponent.Retry;

public class SubmitOrder extends BaseTest{
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		ProductCatalog productCatalog = landingPage.LoginApplication(input.get("email"), input.get("password"));
		List<WebElement> productsList = productCatalog.getProductList();
		productCatalog.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalog.goToCartPage();
		
		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		
		checkoutPage.actionDropdown("Indonesia");
		ConfirmationPage confirmationPage = checkoutPage.actionSubmit();
		
		String text = confirmationPage.getText();
		Assert.assertTrue(text.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	@Test(dependsOnMethods = {"submitOrder"}, retryAnalyzer = Retry.class)
	public void OrderHistoryTest() {
		ProductCatalog productCatalog = landingPage.LoginApplication("Test@example.com", "Test1234");
		OrderPage orderPage = productCatalog.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> dataHashMaps = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\myporto\\data\\PurchaseOrder.json");
		return new Object[][] {{dataHashMaps.get(0)}, {dataHashMaps.get(1)}};
	}

}
