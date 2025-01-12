package ravinduheshan99.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ravinduheshan99.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents{
	
	WebDriver driver;

	public OrderPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orderTitles;
	
	public Boolean VerifyOrderDisplay(String productName) {
		Boolean match = orderTitles.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
	}
		
}
