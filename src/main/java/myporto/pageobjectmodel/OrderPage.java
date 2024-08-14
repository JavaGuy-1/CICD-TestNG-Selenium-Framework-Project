package myporto.pageobjectmodel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import myporto.abstractcomponent.AbstractComponent;

public class OrderPage extends AbstractComponent{
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


		//Page Factory
		@FindBy(css = "tr td:nth-child(3)")
		List<WebElement> orderList;

	public Boolean verifyOrderDisplay(String productName) {
		Boolean match = orderList.stream().anyMatch(products -> products.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	

}
