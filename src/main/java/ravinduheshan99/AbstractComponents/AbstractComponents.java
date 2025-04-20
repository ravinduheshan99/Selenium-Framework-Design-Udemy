package ravinduheshan99.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ravinduheshan99.pageobjects.CartPage;
import ravinduheshan99.pageobjects.OrderPage;

public class AbstractComponents {
	
	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;

	public void waitForElementToAppear(By findBy) {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	     wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement findBy) {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	     wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitForElementToDisappear(WebElement element) throws InterruptedException {
		Thread.sleep(1000);
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    //wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public CartPage goToCartPage() {
		cartHeader.click();
		return new CartPage(driver);
	}
	
	public OrderPage goToOrdersPage() {
		orderHeader.click();
		return new OrderPage(driver);
	}

}
