package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/Cucumber", glue="FrameworkDesign.StepDefinitions", monochrome=true, tags = "@Regression", plugin = {"html:target/cucumber.html"})

//if you are running using JUnit, then this is not required because by default, 
//cucumber have the ability to run the tests which have JUnit code, it comes in inbuilt,
//but TestNG is not something which comes inbuilt in cucumber, so have to extend wrapper class so it'll be able to run
public class TestNGTestRunner extends AbstractTestNGCucumberTests{

}
