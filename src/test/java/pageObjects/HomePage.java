package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id=\"top-links\"]/ul/li[2]/a/span[1]")
    WebElement clickMe;

    @FindBy(xpath = "//*[@id=\"top-links\"]/ul/li[2]/ul/li[1]/a")
    WebElement register;
    
    @FindBy(linkText = "Login")
    WebElement linkLogin;

    public void clickMee() {
        clickMe.click();
    }

    public void clickRegister() {
        register.click();
    }
    
    public void clickLogin() {
    	linkLogin.click();
    }
}
