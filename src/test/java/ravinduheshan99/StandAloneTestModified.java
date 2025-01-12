package ravinduheshan99;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import ravinduheshan99.pageobjects.CartPage;
import ravinduheshan99.pageobjects.CheckoutPage;
import ravinduheshan99.pageobjects.ConfirmationPage;
import ravinduheshan99.pageobjects.LandingPage;
import ravinduheshan99.pageobjects.ProductCatalogue;
import org.openqa.selenium.JavascriptExecutor;

public class StandAloneTestModified {

	public static void main(String[] args) throws InterruptedException {

		String productName = "ADIDAS ORIGINAL";

		// Set up WebDriver for Chrome
		// Implicit wait for element availability
		// Maximize browser window for better visibility
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		// Open the application URL
		// Enter login credentials and log in
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		ProductCatalogue productCatalogue = landingPage.loginApplication("test@gmail.com", "test@123");

		// Explicit wait to ensure product cards are visible
		// Find all products and filter for the desired product
		// Add the selected product to the cart
		// Wait for the toast message (confirmation of addition) to appear and disappear
		// Navigate to the cart page
		productCatalogue.getProductList();
		productCatalogue.addPoductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();

		// Verify that the selected product is in the cart
		// Proceed to checkout
		Assert.assertTrue(cartPage.VerifyProductDisplay(productName));
		CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
		
		// Enter the country name using Actions class
		// Wait for the country suggestions to load and select one
		checkoutPage.selectCountry("Sri");
		
		// Scroll down to make the "Place Order" button visible
		// Scrolls down 500 pixels; adjust as needed
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)"); 

		// Wait for the submit button to become visible and click it
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();

		// Confirm the success message after placing the order
		Assert.assertTrue(confirmationPage.getConfirmationMessage().equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		// Close the browser
		// driver.close();
	}
}
