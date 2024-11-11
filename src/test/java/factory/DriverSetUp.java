package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import utilities.Utils;

public class DriverSetUp {
	
	// Attribute
	static WebDriver driver;
	
	/* Creates Chrome driver
	 * Parameter - N/A
	 * Return - N/A
	 */
	public static WebDriver getChromeDriver() {
		driver = new ChromeDriver();
		return driver;
	}
	
	/* Creates Edge driver
	 * Parameter - N/A
	 * Return - N/A
	 */
	public static WebDriver getEdgeDriver() {
		driver = new EdgeDriver();
		return driver;
	}
	
	/* Navigates to the website and maximize the window
	 * Parameter - N/A
	 * Return - N/A
	 */
	public static void navigateTo() {
		driver.get(Utils.getValue("sitelink"));
		driver.manage().window().maximize();
	}

}
