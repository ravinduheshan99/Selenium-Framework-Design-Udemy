package ravinduheshan99.tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import ravinduheshan99.TestComponents.BaseTest;
import ravinduheshan99.pageobjects.CartPage;
import ravinduheshan99.pageobjects.CheckoutPage;
import ravinduheshan99.pageobjects.ConfirmationPage;
import ravinduheshan99.pageobjects.OrderPage;
import ravinduheshan99.pageobjects.ProductCatalogue;
public class StandAloneTestModified extends BaseTest{
	
	String productName = "ADIDAS ORIGINAL";

	@Test
	public void placeOrder() throws InterruptedException, IOException{
		ProductCatalogue productCatalogue = landingPage.loginApplication("test@gmail.com", "test@123");
		productCatalogue.getProductList();
		productCatalogue.addPoductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Assert.assertTrue(cartPage.VerifyProductDisplay(productName));
		CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
		checkoutPage.selectCountry("Sri");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		Assert.assertTrue(confirmationPage.getConfirmationMessage().equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	@Test(dependsOnMethods = {"placeOrder"})
	public void OrderHistoryTest() throws InterruptedException, IOException{
		ProductCatalogue productCatalogue = landingPage.loginApplication("test@gmail.com", "test@123");
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
	}
}
