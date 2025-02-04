package TestRunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
@CucumberOptions
        (plugin = {"html:target/api-cucumber-report/api-report.html"
                ,"json:target/api-cucumber-report/api-cucumber.json","pretty","html:target/api-cucumber-report/api-cucumber-pretty",
                "json:target/api-cucumber-report/API-CucumberTestReport.json","rerun:src/test/java/TestRunners/APIrerun.txt"},
                //monochrome = true,
                features = "src/test/resources/API/features/WeatherAPI.feature",
                glue = {"stepdefinitions"})

    public class RunAPITests extends AbstractTestNGCucumberTests {
        @Override
        @DataProvider(parallel = true)
        public Object[][] scenarios() {
            return super.scenarios();
        }
    }
