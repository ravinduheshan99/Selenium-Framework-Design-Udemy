package ravinduheshan99.tests;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;

public class StandAloneTest {

    public static void main(String[] args) {
        
        String productName = "ADIDAS ORIGINAL";
        
        // Set up WebDriver for Chrome
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        
        // Implicit wait for element availability
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        // Maximize browser window for better visibility
        driver.manage().window().maximize();
        
        // Open the application URL
        driver.get("https://rahulshettyacademy.com/client");
        
        // Enter login credentials and log in
        driver.findElement(By.id("userEmail")).sendKeys("test@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("test@123");
        driver.findElement(By.id("login")).click();
        
        // Explicit wait to ensure product cards are visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        
        // Find all products and filter for the desired product
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        WebElement prod = products.stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        
        // Add the selected product to the cart
        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
        
        // Wait for the toast message (confirmation of addition) to appear and disappear
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        
        // Navigate to the cart page
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
        
        // Verify that the selected product is in the cart
        List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean match = cartItems.stream().anyMatch(cartItem -> cartItem.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);
        
        // Proceed to checkout
        driver.findElement(By.cssSelector(".totalRow button")).click();
        
        // Enter the country name using Actions class
        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "Sri").build().perform();
        
        // Wait for the country suggestions to load and select one
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ng-star-inserted']")));
        driver.findElement(By.xpath("//span[@class='ng-star-inserted']")).click();

        // Scroll down to make the "Place Order" button visible
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)"); // Scrolls down 500 pixels; adjust as needed
        
        // Wait for the submit button to become visible and click it
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".action__submit")));
        driver.findElement(By.cssSelector(".action__submit")).click();
        
        // Confirm the success message after placing the order
        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        
        // Close the browser
        //driver.close();
    }
}
