package pageObject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CourseTopPage extends BasePage{
	
	List<String> languages = new ArrayList<>();
	List<String> levels = new ArrayList<>();
	
	public CourseTopPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//button[@data-testid='chip-button-active']//div[text() = 'English']")
	WebElement languagefilter;
	
	@FindBy(xpath = "//span[text()='English']")
	WebElement english;
	
	@FindBy(xpath = "//span[text()='Show results']")
	WebElement showresult;
	
	@FindBy(xpath = "//button[@data-testid='chip-button-inactive']//div[text() = 'Difficulty']")
	WebElement levelfilter;
	
	@FindBy(xpath = "//span[text()='Beginner']")
	WebElement beginner;
	
	@FindBy(xpath = "//div[@data-testid='search-filter-group-Language']//div[@class='cds-checkboxAndRadio-labelText']")
	List<WebElement> languageElements;
	
	@FindBy(xpath = "//div[@data-testid='search-filter-group-Level']//div[@class='cds-checkboxAndRadio-labelText']")
	List<WebElement> levelElements;
	
	public void applyLanguageFilter() {
		languagefilter.click();
		english.click();
		showresult.click();
	}
	
	public void applyLevelFilter() {
		levelfilter.click();
		beginner.click();
		showresult.click();
	}
	
	public List<String> getCountByLanguage(){
		languagefilter.click();
		for(WebElement language: languageElements) {
			languages.add(language.getText());
		}
		showresult.click();
		return languages;
	}
	
	public List<String> getCountByLevel(){
		levelfilter.click();
		for(WebElement level: levelElements) {
			levels.add(level.getText());
		}
		showresult.click();
		return levels;
	}

}
