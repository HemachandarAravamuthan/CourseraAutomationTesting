package testCases;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pageObject.BusinessPage;
import pageObject.HomePage;
import testBase.BaseClass;
import utilities.Utils;

@Listeners(utilities.ExtentReportUtility.class)
public class TC003_ErrorMessageTest extends BaseClass{
	
	@Test(groups = {"Regression", "Master"})
	public void testErrorMessage() {
		try {
			logger.info("****** Starting TC003_ErrorMessageTest *****");
			HomePage home = new HomePage(driver);
			Utils.implicitWait(20);
			home.hitBusiness();
			logger.info("Navigated to enterprise page");
			
			BusinessPage business = new BusinessPage(driver);
			business.setEmail(Utils.getValue("email"));
			business.comeBack();
			logger.info("Invalid email input given");
			
			String error = business.getErrorMsg();
			
			System.out.println(error);
			logger.info("Got error message");
			
			Assert.assertEquals(error, "Must be valid email.\nexample@yourdomain.com");	
			logger.info("****** Completed TC003_ErrorMessageTest *****");
		} catch(Exception e) {
			logger.error("TC003_ErrorMessageTest Failed");
			logger.debug("Debug logs");
			Assert.fail();
		}
	}
}
