package myporto.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src\\test\\java\\myporto\\cucumber", glue = "myporto.stepDefinition",
monochrome = true, plugin = {"html:target/cucumber.html"})

public class TestNGRunner extends AbstractTestNGCucumberTests{
	

}
