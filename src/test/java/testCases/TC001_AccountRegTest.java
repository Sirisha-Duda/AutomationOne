package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegTest extends BaseClass {
    
	public Logger logger = LogManager.getLogger(TC001_AccountRegTest.class);
    @Test(groups = {"Regression","master"})
    public void verifyReg() throws InterruptedException {
    	logger.info("*******Starting TC001_AccountRegTest**********");
    	try {
        HomePage hp = new HomePage(driver);
        hp.clickMee();
        logger.info("Clicked on myaccount link");
        hp.clickRegister();
        logger.info("clicked on register link");
        AccountRegistrationPage ar = new AccountRegistrationPage(driver);
        logger.info("providing customer details");
        ar.setFirstName(randomeString());
        ar.setLastName(randomeString());
        String email = randomeString();
        ar.setEmail(email+ "@gmail.com");
        System.out.println("username: "+email+"@gmail.com");
        ar.setTel(randomNum());
        
        String pwd = randomAlphaNum();
        ar.setPwd(pwd);
        ar.setConPwd(pwd);
        System.out.println("password: "+pwd);
        ar.setPolicy();
        ar.clickContinue();
        
        Thread.sleep(2000);
        
        logger.info("validating expected message....");
        String confmsg = ar.getConfirmation();
        Assert.assertEquals(confmsg, "Your Account Has Been Created!");
    	}
    			catch(Exception e) {
    				logger.error("Test failed...8");
    				logger.debug("Debug logs...");
    				Assert.fail();
    			}
    	logger.info("*****Finished TC001_AccountRegTest********");
    }

   
}
