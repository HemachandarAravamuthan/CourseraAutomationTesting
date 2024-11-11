package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import utilities.Utils;

// Class extends FilterPage class
public class BusinessPage extends BasePage{
	
	//Constructor
	public BusinessPage (WebDriver driver){
		super(driver);
	}
	
	//Web Elements in the page
	@FindBy(xpath = "//input[@id='Email']")
	WebElement emailInput;
	
	@FindBy(xpath = "//input[@id='Title']")
	WebElement titleInput;
	
	@FindBy(xpath = "//div[@id='ValidMsgEmail']")
	WebElement errorMsg;
	
	/* 
	 * Scroll to the email input field and fill email
	 * Parameter - String input
	 * Return - N/A
	 */
	public void setEmail(String email) {
		new Actions(driver)
        .scrollToElement(emailInput)
        .perform();
		emailInput.sendKeys(email);
	}
	
	/* 
	 * Get back to the input field
	 * Parameter - N/A
	 * Return - N/A
	 */
	public void comeBack() {
		titleInput.sendKeys("");
		emailInput.click();
		Utils.implicitWait(20);
	}
	
	/* 
	 * Get error message
	 * Parameter - N/A
	 * Return - String error message
	 */
	public String getErrorMsg() {
		return errorMsg.getText();
	}
	
	
	

}
