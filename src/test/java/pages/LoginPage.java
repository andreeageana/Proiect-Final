package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage extends BasePage {
    @FindBy(id = "user-account")
    private WebElement authenticationHoverButton;

    @FindBy(id = "login-email")
    private WebElement emailInput;

    @FindBy(id = "login-password")
    private WebElement passwordInput;

    @FindBy(xpath = "//form[@class='cf']/a")
    private WebElement loginButton;

    @FindBy(xpath = "//*[@id=\"account-logged\"]/p[contains(text(), 'Bine ati venit!')]")
    private WebElement welcomeMessage;

    Actions actions;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);
    }

    public WebElement getAuthenticationHoverButton() {
        return authenticationHoverButton;
    }

    public void login(String email, String password) {
        this.authenticationButtonDisplayed();
        this.moveToAuthenticationButton();
        this.enterEmail(email);
        this.enterPassword(password);
        this.submit();
    }

    public boolean authenticationButtonDisplayed() {
        waitUntilElementVisible(authenticationHoverButton);
        return authenticationHoverButton.isDisplayed();
    }

    public void moveToAuthenticationButton() {
        actions.moveToElement(authenticationHoverButton).perform();
    }

    public void enterEmail(String email) {
        waitUntilElementVisible(emailInput);
        System.out.println("Enter email address: " + email);
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        waitUntilElementVisible(emailInput);
        System.out.println("Enter password: " + password);
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void submit() {
        waitUntilElementVisible(loginButton);
        System.out.println("Click on Login button");
        loginButton.click();
    }

    public boolean verifyLoginSuccessful(String username) {
        moveToAuthenticationButton();
        waitUntilElementVisible(welcomeMessage);
        System.out.println("Welcome message is displayed: " + welcomeMessage.getText());
        return welcomeMessage.isDisplayed();
    }

    public String getErrorMessage() {
        return driver.getCurrentUrl();
    }

    public boolean verifyLoginFailed(String error) {
        String errorMessage = getErrorMessage();
        return errorMessage.contains(error);
    }

    public boolean verifyValidationMessage(String validationError) {
        String message;
        String text = emailInput.getAttribute("value");
        if (text.isEmpty()) {
            message = emailInput.getAttribute("validationMessage");
        } else {
            message = passwordInput.getAttribute("validationMessage");
        }
        return validationError.equals(message);
    }
}
