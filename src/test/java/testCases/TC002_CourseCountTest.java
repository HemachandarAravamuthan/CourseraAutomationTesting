package testCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pageObject.BasePage;
import pageObject.CourseSidePage;
import pageObject.CourseTopPage;
import pageObject.HomePage;
import testBase.BaseClass;
import utilities.Utils;

@Listeners(utilities.ExtentReportUtility.class)
public class TC002_CourseCountTest extends BaseClass{
	
	Map<String, String> languagesMap = new HashMap<>();
	Map<String, String> levelsMap = new HashMap<>();
	
	/*
	 * Test method to test the filter function
	 * Parameter : N/A
	 * Return    : N/A
	 */
	
	@Test(groups = {"Regression", "Master"})
	public void testFilterFunction() throws IOException {
		try {
			logger.info("****** Starting TC002_CourseCountTest *****");
			// HomePage page object
			HomePage home = new HomePage(driver);
			BasePage base = new BasePage(driver);
			
			// Search course
			home.searchCourse(Utils.getValue("input"));
			home.hitSearch();
			
			logger.info("Searched Web development");
			
			Utils.implicitWait(30);
			try {
				driver.findElement(By.xpath("//button[@type='button']/div[2][text()='Difficulty']"));
				CourseTopPage page = new CourseTopPage(driver);
				levelsMap = base.mapCourseCount(page.getCountByLevel());
				Utils.writeCountExcel(levelsMap, "Courses By Level");
				logger.info("Written level wise count in the excel file");
				base.printMap(levelsMap);
				
				languagesMap = base.mapCourseCount(page.getCountByLanguage());
				Utils.writeCountExcel(languagesMap, "Courses By Language");
				logger.info("Written language wise in the excel file");
				base.printMap(languagesMap);
				
			} catch (Exception e) {
				CourseSidePage page = new CourseSidePage(driver);
				levelsMap = base.mapCourseCount(page.getCountByLevel());
				Utils.writeCountExcel(levelsMap, "Courses By Level");
				logger.info("Written level wise count in the excel file");
				base.printMap(levelsMap);
				
				languagesMap = base.mapCourseCount(page.getCountByLanguage());
				Utils.writeCountExcel(languagesMap, "Courses By Language");
				logger.info("Written language wise in the excel file");
				base.printMap(languagesMap);
				
			}
			logger.info("****** Completed TC002_CourseCountTest *****");
		} catch(Exception e) {
			logger.error("TC002_CourseCountTest Failed");
			logger.debug("Debug logs");
			Assert.fail();
		}
	}
}
