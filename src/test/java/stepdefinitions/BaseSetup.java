package stepdefinitions;

import org.openqa.selenium.WebDriver;
import utils.DriverManager;
import utils.TestContext;

public class BaseSetup {

    protected TestContext testContext;
    protected WebDriver driver;
//    public BaseSetup(TestContext testContext) {
//        this.testContext = testContext;
//        driver = testContext.getDriverManager().getDriver();
//        configuration =  ConfigurationManager.getConfiguration();
//        driverManagerUtils = new DriverManagerUtils(driver);
//    }


    public BaseSetup(TestContext testContext) {
        this.testContext = testContext;

        if (testContext.getDriver() == null) {
            DriverManager driverManager = new DriverManager();
            testContext.setDriver(driverManager.initializeDriver());
        }
        driver = testContext.getDriver();
    }
}
