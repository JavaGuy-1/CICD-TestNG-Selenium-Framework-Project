package myporto.pageobjectmodel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import myporto.abstractcomponent.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	WebDriver driver;
	
	public CheckoutPage (WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Page Factory
	@FindBy(css = "[placeholder='Select Country']")
	WebElement actionDropdown;
	
	@FindBy(xpath = "(//button[contains(@class, 'ta-item')])")
	WebElement selectCountryButton;
	
	@FindBy(css = ".action__submit")
	WebElement actionSubmit;
	
	By byResult = By.cssSelector(".ta-results");
	
	public void actionDropdown(String country) {
		Actions actions = new Actions(driver);
		actions.sendKeys(actionDropdown, country).build().perform();
		waitForElementToAppear(byResult);
		selectCountryButton.click();
	}
	
	
	public ConfirmationPage actionSubmit() {
		actionSubmit.click();
		return new ConfirmationPage(driver);
	}
	
	

}
