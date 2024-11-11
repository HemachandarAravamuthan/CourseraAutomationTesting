package stepDefinition;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import factory.DriverSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.BusinessPage;
import pageObject.HomePage;
import utilities.Utils;

public class Steps {
	
	// Attributes
	WebDriver driver;
	private HomePage searchPage;
	private BusinessPage businessPage;
	
	/* Navigates to the home page
	 * Parameter - browser name as string
	 * Return - N/A
	 */
	@Given("User is on coursera home page in {string}")
	public void user_is_on_coursera_home_page_in(String browser) throws Exception {
		if(browser.equalsIgnoreCase("chrome")) {
			  driver = DriverSetUp.getChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			  driver = DriverSetUp.getEdgeDriver();
		}
		driver.get("https://www.coursera.org/");
		driver.manage().window().maximize();
		searchPage = new HomePage(driver);
		searchPage.hitBusiness();
		Utils.grabScreen("BusinessPageCucumber");
		Assert.assertEquals(driver.getTitle(), "Custom Employee Development Programs | Coursera for Business | Coursera for Business");
	}
	
	/* Fill invalid email
	 * Parameter - email as string
	 * Return - N/A
	 */
	@When("the user enters invalid email {string}")
	public void the_user_enters_invalid_email(String email) throws Exception {
		businessPage = new BusinessPage(driver);
		businessPage.setEmail(email);
	}
	
	/* Navigates to the other input
	 * Parameter - N/A
	 * Return - N/A
	 */
	@Then("navigate to other field and come back")
	public void navigate_to_other_field_and_come_back() throws Exception {
		businessPage.comeBack();
	    
	}
	
	/* Validate error message
	 * Parameter - N/A
	 * Return - N/A
	 */
	@Then("it should throw error")
	public void it_should_throw_error() {
		String error = businessPage.getErrorMsg();
		Assert.assertEquals(error, "Must be valid email.\nexample@yourdomain.com");
		driver.quit();
	}

}
