package myporto.pageobjectmodel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import myporto.abstractcomponent.AbstractComponent;

public class ProductCatalog extends AbstractComponent {
	WebDriver driver;
	public ProductCatalog (WebDriver driver){
		super(driver);
		this.driver =  driver;
		PageFactory.initElements(driver, this);
	}
	
	//page factory
	@FindBy(css = ".mb-3")
	List<WebElement> product;
	
	@FindBy(css = ".ng-animating")
	WebElement loading;
	
	
	
	By byproducts = By.cssSelector(".mb-3");
	By byAddToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessageBy = By.cssSelector("#toast-container");
	public List<WebElement> getProductList() {
		waitForElementToAppear(byproducts);
		return product;
	}
	
	public WebElement getProductByName (String productName) {
		WebElement prod  = getProductList().stream().filter(
				products -> products.findElement(By.cssSelector("b"))
				.getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prod = getProductByName(productName);
		prod.findElement(byAddToCart).click();
		waitForElementToAppear(toastMessageBy);
		waitForElementDissapear(loading);
	}
	
	

}
