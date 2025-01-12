package ravinduheshan99.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ravinduheshan99.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents{
	
	WebDriver driver;

	public CheckoutPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	@FindBy(xpath="//span[@class='ng-star-inserted']")
	WebElement selectCountry;
	
	By countryElements = By.xpath("//span[@class='ng-star-inserted']");
	By submitButton = By.cssSelector(".action__submit");
	
	public void selectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(countryElements);
		selectCountry.click();
	}
	
	public ConfirmationPage submitOrder() {
		waitForElementToAppear(submitButton);
		submit.click();
		return new ConfirmationPage(driver);
	}
	
}
