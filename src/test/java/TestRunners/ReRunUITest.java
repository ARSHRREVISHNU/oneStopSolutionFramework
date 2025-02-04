package TestRunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
@CucumberOptions
        (
                plugin = {"html:target/cucumber-report/rerunreport.html"
                        ,"json:target/cucumber-report/reruncucumber.json","pretty","html:target/cucumber-report/cucumber-pretty",
                        "json:target/cucumber-report/rerunCucumberTestReport.json"},
                //monochrome = true,
                features = "src/test/java/TestRunners/rerun.txt",
                glue = {"stepdefinitions"}

                //tags = "@tag1"
                )

public class ReRunUITest extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
