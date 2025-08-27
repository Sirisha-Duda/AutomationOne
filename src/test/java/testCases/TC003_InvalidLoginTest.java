package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC003_InvalidLoginTest extends BaseClass {
    
    @Test(groups = {"regression","master"})
    public void verify_invalid_login() {
        logger.info("******* Starting TC003_InvalidLoginTest *******");
        try {
            HomePage hp = new HomePage(driver);
            hp.clickMee();
            hp.clickLogin();
            
            LoginPage lp = new LoginPage(driver);
            lp.setEmail(p.getProperty("email1"));   // from config
            lp.setPwd(p.getProperty("password1"));  // from config
            lp.clickLogin();
            
            // Instead of going to MyAccount, app should show error
            boolean errorMsgDisplayed = lp.isErrorMessageDisplayed();
            
            Assert.assertTrue(errorMsgDisplayed, "Error message not displayed for invalid login");
        } 
        catch (Exception e) {
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
        logger.info("******* Finished TC003_InvalidLoginTest *******");
    }
}
