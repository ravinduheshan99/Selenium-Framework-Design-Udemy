package ravinduheshan99.tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ravinduheshan99.TestComponents.BaseTest;
import ravinduheshan99.pageobjects.CartPage;
import ravinduheshan99.pageobjects.CheckoutPage;
import ravinduheshan99.pageobjects.ConfirmationPage;
import ravinduheshan99.pageobjects.OrderPage;
import ravinduheshan99.pageobjects.ProductCatalogue;
public class StandAloneTestModified extends BaseTest{
	
	String productName = "ADIDAS ORIGINAL";

	@Test(dataProvider="getData", groups= {"Purchase"})
	public void placeOrder(String email, String password, String productName) throws InterruptedException, IOException{
		ProductCatalogue productCatalogue = landingPage.loginApplication(email, password);
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
	
	@DataProvider
	public Object[][] getData() {
		return new Object[][] {{"test@gmail.com","test@123","ADIDAS ORIGINAL"},{" raviya@gmail.com","Raviya@123","IPHONE 13 PRO"}};
	}
}
