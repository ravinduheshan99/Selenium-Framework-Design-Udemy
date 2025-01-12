package ravinduheshan99.tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import ravinduheshan99.TestComponents.BaseTest;
import ravinduheshan99.pageobjects.CartPage;
import ravinduheshan99.pageobjects.CheckoutPage;
import ravinduheshan99.pageobjects.ConfirmationPage;
import ravinduheshan99.pageobjects.LandingPage;
import ravinduheshan99.pageobjects.ProductCatalogue;
public class StandAloneTestModified extends BaseTest{

	@Test
	public void placeOrder() throws InterruptedException, IOException{

		String productName = "ADIDAS ORIGINAL";

		// Set up WebDriver for Chrome
		// Implicit wait for element availability
		// Maximize browser window for better visibility
		// Open the application URL
		// Enter login credentials and log in
		LandingPage landingPage= launchApplication();
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
		// Wait for the submit button to become visible and click it
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();

		// Confirm the success message after placing the order
		Assert.assertTrue(confirmationPage.getConfirmationMessage().equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		// Close the browser
		// driver.close();
	}
}
