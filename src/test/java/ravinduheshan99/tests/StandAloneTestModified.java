package ravinduheshan99.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
	
//	mvn test: Runs all tests with default settings.
//	mvn test -PRegression: Runs tests with the Regression profile.
//	mvn test -PRegression -Dbrowser=Firefox: Runs tests with the Regression profile in Firefox.
	
	String productName = "ZARA COAT 3";

	//Pass-2 test cases
	@Test(dataProvider="getData", groups= {"Purchase"})
	public void placeOrder(HashMap<String,String> map) throws InterruptedException, IOException{
		ProductCatalogue productCatalogue = landingPage.loginApplication(map.get("email"), map.get("password"));
		productCatalogue.getProductList();
		productCatalogue.addPoductToCart(map.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();
		Assert.assertTrue(cartPage.VerifyProductDisplay(map.get("productName")));
		CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
		checkoutPage.selectCountry("Sri");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		Assert.assertTrue(confirmationPage.getConfirmationMessage().equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	//Pass
	@Test(dependsOnMethods = {"placeOrder"})
	public void OrderHistoryTest() throws InterruptedException, IOException{
		ProductCatalogue productCatalogue = landingPage.loginApplication("test@gmail.com", "test@123");
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put("email", "test@gmail.com");
//		map1.put("password", "test@123");
//		map1.put("productName", "ADIDAS ORIGINAL");
//		
//		HashMap<String,String> map2 = new HashMap<String,String>();
//		map2.put("email", "raviya@gmail.com");
//		map2.put("password", "Raviya@123");
//		map2.put("productName", "IPHONE 13 PRO");
		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//ravinduheshan99//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
}
