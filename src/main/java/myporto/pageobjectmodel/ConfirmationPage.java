package myporto.pageobjectmodel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import myporto.abstractcomponent.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {
	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Page Factory
	@FindBy(css=".hero-primary")
	WebElement textConfirmation;
	
	public String getText() {
		return textConfirmation.getText();
	}
	
	

}
