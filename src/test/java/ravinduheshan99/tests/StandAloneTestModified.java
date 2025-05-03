package ravinduheshan99.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ravinduheshan99.TestComponents.BaseTest;
import ravinduheshan99.pageobjects.*;

/**
 * This class contains test cases for placing an order and verifying order history.
 * It extends the BaseTest class to utilize common setup and teardown methods.
 */
public class StandAloneTestModified extends BaseTest {

    // Product name to be used in the OrderHistoryTest
    String productName = "ADIDAS ORIGINAL";

    /**
     * Test to place an order for a product.
     *
     * @param map A HashMap containing test data (email, password, productName).
     * @throws InterruptedException If thread sleep is interrupted.
     * @throws IOException          If there is an issue with file handling.
     */
    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void placeOrder(HashMap<String, String> map) throws InterruptedException, IOException {

        // Step 1: Register a new user
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.completeRegistration(
                "John", "Doe", "john.doe@example.com", "1234567890",
                "Engineer", "Male", "Password123", "Password123"
        );

        // Step 2: Log in to the application
        ProductCatalogue productCatalogue = landingPage.loginApplication(map.get("email"), map.get("password"));

        // Step 3: Add the specified product to the cart
        productCatalogue.getProductList();
        productCatalogue.addPoductToCart(map.get("productName"));

        // Step 4: Verify the product is displayed in the cart
        CartPage cartPage = productCatalogue.goToCartPage();
        Assert.assertTrue(cartPage.VerifyProductDisplay(map.get("productName")));

        // Step 5: Proceed to checkout and place the order
        CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
        checkoutPage.selectCountry("Sri");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();

        // Step 6: Verify the order confirmation message
        Assert.assertTrue(confirmationPage.getConfirmationMessage().equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

    /**
     * Test to verify that the placed order appears in the order history.
     *
     * @throws InterruptedException If thread sleep is interrupted.
     * @throws IOException          If there is an issue with file handling.
     */
    @Test(dependsOnMethods = {"placeOrder"})
    public void OrderHistoryTest() throws InterruptedException, IOException {

        // Step 1: Log in to the application
        ProductCatalogue productCatalogue = landingPage.loginApplication("test@gmail.com", "test@123");

        // Step 2: Navigate to the orders page
        OrderPage orderPage = productCatalogue.goToOrdersPage();

        // Step 3: Verify the product is displayed in the order history
        Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
    }

    /**
     * Data provider to supply test data for the placeOrder test.
     *
     * @return A 2D Object array containing test data.
     * @throws IOException If there is an issue with file handling.
     */
    @DataProvider
    public Object[][] getData() throws IOException {

        // Read test data from a JSON file and convert it to a list of HashMaps
        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//ravinduheshan99//data//PurchaseOrder.json");

        // Create a 2D Object array with the size of the data list
        Object[][] testData = new Object[data.size()][1];

        // Populate the 2D array using a for loop
        for (int i = 0; i < data.size(); i++) {
            testData[i][0] = data.get(i);
        }

        return testData;
    }
}