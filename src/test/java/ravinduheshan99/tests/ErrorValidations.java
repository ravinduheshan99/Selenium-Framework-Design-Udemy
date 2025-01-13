package ravinduheshan99.tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import ravinduheshan99.TestComponents.BaseTest;
import ravinduheshan99.pageobjects.CartPage;
import ravinduheshan99.pageobjects.ProductCatalogue;
public class ErrorValidations extends BaseTest{
	
	//This test case is define to demonstrate failure scenario intentionally changing this line-->Incorrect email or password.
	@Test(groups= {"ErrorHandling"})
	public void LoginErrorValidation() throws InterruptedException, IOException{
		landingPage.loginApplication("test@gmail.com", "Invalid");
		Assert.assertEquals("Incorrect email password.", landingPage.getErrorMessage());
	}
	
	@Test
	public void ProductErrorValidation() throws InterruptedException, IOException{
		String productName = "ADIDAS ORIGINAL";
		ProductCatalogue productCatalogue = landingPage.loginApplication("test@gmail.com", "test@123");
		productCatalogue.getProductList();
		productCatalogue.addPoductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Assert.assertFalse(cartPage.VerifyProductDisplay("Invalid Product Name"));
	}
	
}
