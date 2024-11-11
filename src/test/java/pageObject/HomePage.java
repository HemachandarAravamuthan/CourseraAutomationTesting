package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	
	// Attribute
    protected static WebDriver driver;
	
    // Constructor
    public HomePage(WebDriver driver) {
		super(driver);
	}
	
    // WebElement in the page
	@FindBy(xpath = "//input[@placeholder='What do you want to learn?']")
	WebElement searchInput;
	
	@FindBy(xpath = "//button[@class='nostyle search-button']//div[@class='magnifier-wrapper']")
	WebElement searchButton;
	
	@FindBy(xpath = "//div[@role='navigation']//ul[1]//li[2]")
	WebElement businessButton;
	
	/* 
	 * Send search
	 * Parameter : String search input
	 * Return    : N/A
	 */
	public void searchCourse(String search) {
		searchInput.sendKeys(search);
	}
	
	/* Click search button
	 * Parameter - N/A
	 * Return - N/A
	 */
	public void hitSearch() {
		searchButton.click();
	}
	
	/* Click business button
	 * Parameter - N/A
	 * Return - N/A
	 */
	public void hitBusiness() {
		businessButton.click();
	}
}
