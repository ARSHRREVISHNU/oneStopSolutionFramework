package TestRunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
@CucumberOptions
        (
                plugin = {"html:target/cucumber-report/report.html"
                        ,"json:target/cucumber-report/cucumber.json","pretty","html:target/cucumber-report/cucumber-pretty",
                        "json:target/cucumber-report/CucumberTestReport.json","rerun:src/test/java/TestRunners/rerun.txt"},
                //monochrome = true,
                features = "src/test/resources/ui/features/GoogleSearch/",
                glue = {"stepdefinitions"}

                //tags = "@tag1"
                )

public class RunUITest extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
