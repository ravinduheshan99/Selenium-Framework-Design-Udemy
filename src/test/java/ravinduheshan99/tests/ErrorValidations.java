package ravinduheshan99.tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import ravinduheshan99.TestComponents.BaseTest;
public class ErrorValidations extends BaseTest{

	@Test
	public void placeOrder() throws InterruptedException, IOException{
		landingPage.loginApplication("test@gmail.com", "Invalid");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
}
