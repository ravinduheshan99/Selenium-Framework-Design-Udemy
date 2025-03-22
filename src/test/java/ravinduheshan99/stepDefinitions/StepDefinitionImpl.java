package ravinduheshan99.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ravinduheshan99.TestComponents.BaseTest;
import ravinduheshan99.pageobjects.CartPage;
import ravinduheshan99.pageobjects.CheckoutPage;
import ravinduheshan99.pageobjects.ConfirmationPage;
import ravinduheshan99.pageobjects.LandingPage;
import ravinduheshan99.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public CartPage cartPage;
	public CheckoutPage checkoutPage;
	public ConfirmationPage confirmationPage;

	@Given("I Landed on Ecommerce Page")
	public void I_Landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username, String password) {
		productCatalogue = landingPage.loginApplication(username, password);
	}

	@When("^I add product (.+) from Cart$")
	public void I_add_product_from_Cart(String productName) throws InterruptedException {
		productCatalogue.getProductList();
		productCatalogue.addPoductToCart(productName);
	}

	@And("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName) {
		cartPage = productCatalogue.goToCartPage();
		Assert.assertTrue(cartPage.VerifyProductDisplay(productName));
		checkoutPage = cartPage.goToCheckoutPage();
		checkoutPage.selectCountry("Sri");
	}

	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmationPage(String string) {
		confirmationPage = checkoutPage.submitOrder();
		Assert.assertTrue(confirmationPage.getConfirmationMessage().equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void error_message_displayed_logingPage(String string) {
		Assert.assertEquals(string, landingPage.getErrorMessage());
		driver.close();
	}
}
