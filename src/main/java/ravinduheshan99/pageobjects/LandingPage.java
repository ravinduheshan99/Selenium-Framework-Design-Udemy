package ravinduheshan99.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	//WebElement password = driver.findElement(By.id("userPassword"));
	//WebElement login = driver.findElement(By.id("login"));
	
	//PageFactory
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement login;
	
	public void loginApplication(String email, String pwrd) {
		userEmail.sendKeys(email);
		password.sendKeys(pwrd);
		login.click();	
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

}
