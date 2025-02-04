package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.TestContext;

//import static stepdefinitions.BasicFunctionality.driver;

public class WhenSteps extends BaseSetup{

    public WhenSteps(TestContext testContext) {
        super(testContext);
        this.testContext = testContext;
    }
    @When("the user enters username {string} and password {string}")
    public void the_user_enters_username_and_password(String username, String password) {
        // Locate and fill in the username field
        WebElement usernameField = driver.findElement(By.id("email_idss"));
        usernameField.sendKeys(username);

        // Locate and fill in the password field
        WebElement passwordField = driver.findElement(By.id("pass_word"));
        passwordField.sendKeys(password);

        // Click the login button
        WebElement loginButton = driver.findElement(By.id("send2"));
        loginButton.click();
    }

}
