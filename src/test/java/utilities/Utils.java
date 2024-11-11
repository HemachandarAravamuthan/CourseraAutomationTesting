package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObject.BusinessPage;
import result.CourseRating;

public class Utils extends BusinessPage {
	
	static Properties prop;
	
	public Utils(WebDriver driver){
		super(driver);
		prop = new Properties();
		try(FileInputStream input = new FileInputStream("./src/test/resources//Input.properties")) {
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void grabScreen(String filename) throws Exception {
		try {
			TakesScreenshot tS = (TakesScreenshot)driver;
			File img = tS.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(img, new File(System.getProperty("user.dir")+"\\snaps\\" + filename + ".png"));
		}
		catch(IOException e) {
			System.out.println("Error taking screeshot: " + e.getMessage());
		}
	}
	
	public static void implicitWait(int duration) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(duration));
	}
	
	public static void explictWait(String locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait until the element is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
	}
	
	public static String getValue(String property) {
		return prop.getProperty(property);
	}
	
	public static void writeRatingExcel() throws IOException {
		FileOutputStream file = new FileOutputStream(System.getProperty("user.dir")+"\\testdata\\Top two Courses rating.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("CourseRating");
		XSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("Title");
		row.createCell(1).setCellValue("Duration");
		row.createCell(2).setCellValue("Rating");
		
		for(int i = 0; i < 2; i++) {
			XSSFRow rowText = sheet.createRow(i+1);
			rowText.createCell(0).setCellValue(CourseRating.courseTitle.get(i));
			rowText.createCell(1).setCellValue(CourseRating.courseDuration.get(i));
			rowText.createCell(2).setCellValue(CourseRating.courseRating.get(i));
			
		}
		workbook.write(file);
		workbook.close();
	}
	
	public static void writeCountExcel(Map<String, String> map, String fileName) throws IOException {
		FileOutputStream file = new FileOutputStream(System.getProperty("user.dir")+"\\testdata\\"+fileName+".xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("CourseCount");
		XSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("Label");
		row.createCell(1).setCellValue("Count");
		
		int i = 1;
		for (Map.Entry<String, String> entry : map.entrySet()) {
			
			XSSFRow rowText = sheet.createRow(i);
			rowText.createCell(0).setCellValue(entry.getKey());
			rowText.createCell(1).setCellValue(entry.getValue());
            i = i+1;
        }
		workbook.write(file);
		workbook.close();
	}

}
