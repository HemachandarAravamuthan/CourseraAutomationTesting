package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"features"}, 
					glue = "stepDefinition",
					plugin = {"pretty", "html: CucumberReport.html"})

public class Runner {

}
