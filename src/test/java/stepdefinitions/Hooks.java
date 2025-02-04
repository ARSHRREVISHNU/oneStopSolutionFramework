package stepdefinitions;

import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.TestContext;

public class Hooks {

    private WebDriver driver;
    private TestContext testContext;

    public Hooks(TestContext testContext) {
        System.out.println("Running on Thread: " + Thread.currentThread().getId());
        this.testContext = testContext;
        driver = testContext.getDriver();
    }

    @After("postUI")
    public void postConditionForUITests(Scenario scenario) {
        System.out.println("Finished on Thread: " + Thread.currentThread().getId());
        if (scenario.isFailed() && driver instanceof TakesScreenshot) {
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failed Scenario Screenshot");
        }
            driver.quit();


    }
}
