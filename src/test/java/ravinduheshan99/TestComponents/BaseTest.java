package ravinduheshan99.TestComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import ravinduheshan99.pageobjects.LandingPage;

public class BaseTest {
	
	WebDriver driver;

	public WebDriver initializeDriver() throws IOException {

		Properties properties = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//ravinduheshan99//resources//GlobalData.properties");
		properties.load(fis);
		String browserName = properties.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;

	}
	
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}

}