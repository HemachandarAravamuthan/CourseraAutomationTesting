package pageObject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CourseSidePage extends BasePage{
	
	List<String> languages = new ArrayList<>();
	List<String> levels = new ArrayList<>();
	
	public CourseSidePage(WebDriver driver){
		super(driver);
	}
	
	@FindBy(xpath = "//span[text()='English']")
	WebElement languagefilter;
	
	@FindBy(xpath = "//span[text()='Beginner']")
	WebElement levelfilter;
	
	@FindBy(xpath = "//span[text() = 'Show 22 more']")
	WebElement showMore;
	
	@FindBy(xpath = "//div[@data-testid='search-filter-group-Language']//div[@class='cds-checkboxAndRadio-labelText']")
	List<WebElement> languageElements;
	
	@FindBy(xpath = "//div[@data-testid='search-filter-group-Level']//div[@class='cds-checkboxAndRadio-labelText']")
	List<WebElement> levelElements;
	
	public void applyLanguageFilter() {
		languagefilter.click();
	}
	
	public void applyLevelFilter() {
		levelfilter.click();
	}
	
	public List<String> getCountByLanguage() {
		showMore.click();
		for(WebElement language: languageElements) {
			languages.add(language.getText());
		}
		return languages;
	}
	
	public List<String> getCountByLevel() {
		for(WebElement level: levelElements) {
			levels.add(level.getText());
		}
		return levels;
	}

}
