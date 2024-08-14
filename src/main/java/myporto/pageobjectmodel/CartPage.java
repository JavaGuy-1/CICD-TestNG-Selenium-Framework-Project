package myporto.pageobjectmodel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import myporto.abstractcomponent.AbstractComponent;

public class CartPage extends AbstractComponent {
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Page Factory
	@FindBy(css = ".cartSection h3")
	List<WebElement> cartList;
	
	@FindBy(css =".totalRow button")
	WebElement checkoutButton;
	
	public Boolean verifyProductDisplay(String productName) {
		Boolean match = cartList.stream().anyMatch(products -> products.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout() {
		checkoutButton.click();
		return new CheckoutPage(driver);
	}
	

	
	
	

}
