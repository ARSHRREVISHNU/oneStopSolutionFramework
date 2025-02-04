package stepdefinitions;

import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.TestContext;


public class ThenSteps extends  BaseSetup{
    public ThenSteps(TestContext testContext) {
        super(testContext);
        this.testContext = testContext;
    }
    @Then("the user should see the dashboard page with title {string}")
    public void the_user_should_see_the_dashboard_page_with_title(String expectedTitle) {
        // Validate the title of the dashboard page
        String actualTitle = driver.getTitle();
        System.out.println("Page title: "+actualTitle);
        System.out.println("Expected title: "+expectedTitle);
        Assert.assertEquals( expectedTitle, actualTitle, "Page title did not match");

        // Close the browser
       // driver.quit();
    }


}

