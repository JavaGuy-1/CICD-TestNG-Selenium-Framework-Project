package myporto.pageobjectmodel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import myporto.abstractcomponent.AbstractComponent;

public class LandingPage extends AbstractComponent {
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver =  driver;
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory
	@FindBy(id = "userEmail")
	WebElement userEmail;
	
	@FindBy(id = "userPassword")
	WebElement userPassword;
	
	@FindBy(id = "login")
	WebElement submitLogin;
	
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public ProductCatalog LoginApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submitLogin.click();
		return new ProductCatalog(driver);
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}
