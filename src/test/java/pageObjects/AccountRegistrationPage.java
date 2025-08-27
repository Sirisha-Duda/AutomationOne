package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
    
    public AccountRegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id=\"input-firstname\"]")
    WebElement txtFirstName;

    @FindBy(xpath = "//*[@id=\"input-lastname\"]")
    WebElement txtLastName;

    @FindBy(xpath = "//*[@id=\"input-email\"]")
    WebElement txtemail;

    @FindBy(xpath = "//*[@id=\"input-telephone\"]")
    WebElement txtTel;

    @FindBy(xpath = "//*[@id=\"input-password\"]")
    WebElement txtpwd;

    @FindBy(xpath = "//*[@id=\"input-confirm\"]")
    WebElement confirmPwd;

    @FindBy(xpath = "//*[@id=\"content\"]/h1")
    WebElement msgConfirmation;

    @FindBy(xpath = "//*[@id=\"content\"]/form/div/div/input[1]")
    WebElement privacy;

    @FindBy(xpath = "//*[@id=\"content\"]/form/div/div/input[2]")
    WebElement cont;

    public void setFirstName(String fname) {
        txtFirstName.sendKeys(fname);
    }

    public void setLastName(String lname) {
        txtLastName.sendKeys(lname);
    }

    public void setEmail(String email) {
        txtemail.sendKeys(email);
    }

    public void setTel(String tel) {
        txtTel.sendKeys(tel);
    }

    public void setPwd(String pwd) {
        txtpwd.sendKeys(pwd);
    }

    public void setConPwd(String conpwd) {
        confirmPwd.sendKeys(conpwd);
    }

    public void setPolicy() {
        privacy.click();
    }

    public void clickContinue() {
        cont.click();
    }

    public String getConfirmation() {
        try {
            return msgConfirmation.getText();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
