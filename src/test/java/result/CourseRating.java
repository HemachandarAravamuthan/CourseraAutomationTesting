package result;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CourseRating {
	
	// Attributes
	static List<WebElement> courses;
	public static List<String> courseTitle = new ArrayList<>();
	public static List<String> courseDuration = new ArrayList<>();
	public static List<String> courseRating = new ArrayList<>();
	
	/* 
	 * Gets first two course title, rating and duration
	 * Parameter : WebDriver
	 * Return    : N/A
	 */
	public static void getResult(WebDriver driver) {
		courses = driver.findElements(By.xpath("//div[@class='cds-ProductCard-content']"));
		for(int i = 0; i < 2; i++) {
			
			// Course title
			String title = courses.get(i).findElement(By.tagName("h3")).getText();
			System.out.println("Course title : "+ title);
			courseTitle.add(title);
			
			// Course duration
			String duration = courses.get(i).findElement(By.xpath("//div[@class='cds-CommonCard-metadata']//p[@class=' css-vac8rf']")).getText().substring(20);
			System.out.println("Course duration : "+ duration);
			courseDuration.add(duration);
			
			// Course rating
			String rating = courses.get(i).findElement(By.xpath("//div[@class='cds-CommonCard-ratings']")).getText().substring(0, 3);
			System.out.println("Course rating : "+ rating);
			courseRating.add(rating);
		}
	}
	
	

}
