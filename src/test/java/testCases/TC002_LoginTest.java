package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	@Test(groups = {"sanity","master"})
	public void verify_login() {
		logger.info("/*******Starting TC002_LoginTest******");
		try {
		HomePage  hp = new HomePage(driver);
		hp.clickMee();
		hp.clickLogin();
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPwd(p.getProperty("password"));
		lp.clickLogin();
		MyAccountPage ac= new MyAccountPage(driver);
		boolean status = ac.isMyaccountExists();
		System.out.println(status);
		Assert.assertEquals(status,true, "Login failed");
		//Assert.assertTrue(status);
		
		}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("*******Finished TC_002_loginTest**********");
	}
}
