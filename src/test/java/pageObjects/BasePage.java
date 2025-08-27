package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    
    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public boolean isErrorMessageDisplayed() {
        try {
            return driver.findElement(By.xpath("//div[contains(text(),'Invalid login')]")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

}
