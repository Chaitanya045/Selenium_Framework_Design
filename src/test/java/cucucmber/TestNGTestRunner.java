package cucucmber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = {".\\src\\test\\java\\cucucmber"},
		glue = {"chaitanya.StepDefinitions"},
		plugin = {"pretty", "html:target/cucumber.html"},
		dryRun = false,
		monochrome = true
		)
public class TestNGTestRunner extends AbstractTestNGCucumberTests{
	
}
