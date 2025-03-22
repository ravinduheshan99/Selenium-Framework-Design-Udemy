package ravinduheshan99.tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import ravinduheshan99.TestComponents.BaseTest;
import ravinduheshan99.pageobjects.CartPage;
import ravinduheshan99.pageobjects.ProductCatalogue;
public class ErrorValidations extends BaseTest{
	
	//Fail
	//This test case is define to demonstrate failure scenario intentionally changing this line-->Incorrect email or password.
	@Test(groups= {"ErrorHandling"},retryAnalyzer=ravinduheshan99.TestComponents.Retry.class)
	public void LoginErrorValidation() throws InterruptedException, IOException{
		landingPage.loginApplication("test@gmail.com", "Invalid");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
	
	//Pass
	@Test
	public void ProductErrorValidation() throws InterruptedException, IOException{
		String productName = "ADIDAS ORIGINAL";
		ProductCatalogue productCatalogue = landingPage.loginApplication("test@gmail.com", "test123");
		productCatalogue.getProductList();
		productCatalogue.addPoductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Assert.assertFalse(cartPage.VerifyProductDisplay("Invalid Product Name"));
	}
	
}
