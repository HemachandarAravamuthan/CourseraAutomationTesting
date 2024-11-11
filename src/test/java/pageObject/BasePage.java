package pageObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	
	// Data member
	protected static WebDriver driver;
	Map<String, String> courseCountMap = new HashMap<>();
	
	// Constructor
	public BasePage(WebDriver driver) {
		BasePage.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public Map<String, String> mapCourseCount(List<String> courses){
		for(String course: courses) {
			// Replaces all characters other than alphabets
			String label = course.replaceAll("[^a-zA-Z]", "");
			// Replaces all characters other than number
			String count = course.replaceAll("[^0-9]", "");
			courseCountMap.put(label, count);
		}
		return courseCountMap;
	}
	
	public void printMap(Map<String, String> map) {
		for(Entry<String, String> entry : map.entrySet()) {
			System.out.print(entry.getKey() + " - ");
			System.out.print(entry.getValue());
			System.out.println();
		}
	}

}
