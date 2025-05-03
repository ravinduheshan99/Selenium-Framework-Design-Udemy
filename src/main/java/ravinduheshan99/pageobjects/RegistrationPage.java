package ravinduheshan99.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import ravinduheshan99.AbstractComponents.AbstractComponents;

public class RegistrationPage extends AbstractComponents {

    WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "firstName")
    WebElement firstName;

    @FindBy(id = "lastName")
    WebElement lastName;

    @FindBy(id = "userEmail")
    WebElement userEmail;

    @FindBy(id = "userMobile")
    WebElement userMobile;

    @FindBy(css = "select[formcontrolname='occupation']")
    WebElement occupationDropdown;

    @FindBy(css = "input[type='radio'][value='Male']")
    WebElement genderMale;

    @FindBy(css = "input[type='radio'][value='Female']")
    WebElement genderFemale;

    @FindBy(id = "userPassword")
    WebElement userPassword;

    @FindBy(id = "confirmPassword")
    WebElement confirmPassword;

    @FindBy(css = "input[type='checkbox'][formcontrolname='required']")
    WebElement ageCheckbox;

    @FindBy(id = "login")
    WebElement registerButton;

    @FindBy(css = ".registration-success")
    WebElement successMessage;

    @FindBy(css = ".registration-error")
    WebElement errorMessage;

    public void enterFirstName(String fName) {
        firstName.sendKeys(fName);
    }

    public void enterLastName(String lName) {
        lastName.sendKeys(lName);
    }

    public void enterEmail(String email) {
        userEmail.sendKeys(email);
    }

    public void enterMobile(String mobile) {
        userMobile.sendKeys(mobile);
    }

    public void selectOccupation(String occupation) {
        Select select = new Select(occupationDropdown);
        select.selectByVisibleText(occupation);
    }

    public void selectGender(String gender) {
        if (gender.equalsIgnoreCase("Male")) {
            genderMale.click();
        } else if (gender.equalsIgnoreCase("Female")) {
            genderFemale.click();
        }
    }

    public void enterPassword(String password) {
        userPassword.sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPwd) {
        confirmPassword.sendKeys(confirmPwd);
    }

    public void checkAgeCheckbox() {
        ageCheckbox.click();
    }

    public void clickRegister() {
        registerButton.click();
    }

    public String getSuccessMessage() {
        waitForWebElementToAppear(successMessage);
        return successMessage.getText();
    }

    public String getErrorMessage() {
        waitForWebElementToAppear(errorMessage);
        return errorMessage.getText();
    }

    public void completeRegistration(String fName, String lName, String email, String mobile, String occupation, String gender, String password, String confirmPwd) {
        enterFirstName(fName);
        enterLastName(lName);
        enterEmail(email);
        enterMobile(mobile);
        selectOccupation(occupation);
        selectGender(gender);
        enterPassword(password);
        enterConfirmPassword(confirmPwd);
        checkAgeCheckbox();
        clickRegister();
    }
}