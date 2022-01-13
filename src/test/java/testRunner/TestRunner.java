package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features", 
		glue = { "stepDef" }, 
		monochrome = true,
		publish = true,
		
		plugin = {"pretty"}
		)
public class TestRunner {
}