package testCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pageObject.CourseSidePage;
import pageObject.CourseTopPage;
import pageObject.HomePage;
import result.CourseRating;
import testBase.BaseClass;
import utilities.Utils;

@Listeners(utilities.ExtentReportUtility.class)
public class TC001_CourseSearchingTest extends BaseClass{
	
	/*
	 * Test method to test the searching function
	 * Parameter : N/A
	 * Return    : N/A
	 */
	@Test(groups = {"Sanity", "Master"})
	public void testSearchFunction() {
		try {
			logger.info("****** Starting TC001_CourseSearchingTest *****");
			// HomePage page object
			HomePage home = new HomePage(driver);
			// Search course
			Utils.implicitWait(20);
			home.searchCourse(Utils.getValue("input"));
			Utils.implicitWait(20);
			home.hitSearch();
			Utils.grabScreen("Course Result");
			logger.info("Searched Web development");
			
			try {
				driver.findElement(By.xpath("//button[@type='button']/div[2][text()='Difficulty']"));
				CourseTopPage page = new CourseTopPage(driver);
				Utils.implicitWait(20);
				page.applyLanguageFilter();
				Utils.implicitWait(20);
				Utils.grabScreen("Language Filter");
				logger.info("Applied language filter");
				page.applyLevelFilter();
				Utils.implicitWait(20);
				Utils.grabScreen("Level Filter");
				logger.info("Applied level filter");
				CourseRating.getResult(driver);
				
			} catch (Exception e) {
				CourseSidePage page = new CourseSidePage(driver);
				Utils.implicitWait(20);
				page.applyLanguageFilter();
				Utils.implicitWait(20);
				Utils.grabScreen("Language Filter");
				logger.info("Applied language filter");
				page.applyLevelFilter();
				Utils.implicitWait(20);
				Utils.grabScreen("Level Filter");
				logger.info("Applied level filter");
				CourseRating.getResult(driver);
			}
			// Writing the detail in excel
			try {
				Utils.writeRatingExcel();
				logger.info("Written output in the excel file");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// Assertion
			Assert.assertEquals(driver.getTitle(), "Top Web Development Courses - Learn Web Development Online");
			logger.info("****** Completed TC001_CourseSearchingTest *****");
		} catch (Exception e) {
			logger.error("TC001_CourseSearchingTest Failed");
			logger.debug("Debug logs");
			Assert.fail();
		}
		
		
	}

}
