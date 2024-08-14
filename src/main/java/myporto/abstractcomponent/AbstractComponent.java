package myporto.abstractcomponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import myporto.pageobjectmodel.CartPage;
import myporto.pageobjectmodel.OrderPage;

public class AbstractComponent {
	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader;

	public void waitForElementToAppear (By findby) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
	}
	
	public void waitForWebElementToAppear (WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementDissapear(WebElement e) throws InterruptedException {
		Thread.sleep(3000);
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));;
		//wait.until(ExpectedConditions.invisibilityOf(e));
	}
	
	public CartPage goToCartPage () {
		cartHeader.click();
		return new CartPage(driver);
		
	}
	
	public OrderPage goToOrdersPage () {
		orderHeader.click();
		return new OrderPage(driver);
		
	}
	
	

}
