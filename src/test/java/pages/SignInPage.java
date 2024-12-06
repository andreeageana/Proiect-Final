package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage extends BasePage {

    @FindBy(css = ".whb-general-header-inner a[title='My account']")
    private WebElement myAccountButton;

    @FindBy(id ="username")
    private WebElement usernameField;

    @FindBy(id ="password")
    private WebElement passwordField;

    @FindBy(css ="[value='Log in']")
    private WebElement loginButton;


    public SignInPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void setUsernameField(String name){
        waitUntilElementVisible(usernameField);
        usernameField.sendKeys(name);
    }

    public void setPasswordField(String pass){
        passwordField.sendKeys(pass);
    }

    public void clickLoginButton(){
        loginButton.click();
    }

    public void clickMyAccountButton(){
        myAccountButton.click();
    }
}

