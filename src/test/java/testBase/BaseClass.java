package testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import factory.DriverSetUp;
import utilities.Utils;

public class BaseClass {
	
	protected WebDriver driver;
	Utils util;
	public Logger logger;
	
	@BeforeClass(groups = {"Sanity", "Regression", "Master"})
	  @Parameters({"browser"})
	  public void fireUp(String browser) throws Exception {
		logger = (Logger) LogManager.getLogger(this.getClass());
		
		switch (browser.toLowerCase()) {
		 case "edge":
			 driver = DriverSetUp.getEdgeDriver(); 
			 break;
		 case "chrome":
			 driver = DriverSetUp.getChromeDriver(); 
			 break;
		 }

		 util = new Utils(driver);
		 DriverSetUp.navigateTo();
		 driver.manage().window().maximize();
		 Utils.grabScreen("Homepage");
	  }
	
	@AfterClass(groups = {"Sanity", "Regression", "Master"})
	  public void coolDown() {
		  driver.quit();
	  }
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}
}
