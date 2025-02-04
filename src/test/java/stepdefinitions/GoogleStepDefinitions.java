package stepdefinitions;

import io.cucumber.java.en.*;

public class GoogleStepDefinitions {


    @Given("Browser open")
    public void browser_open(){
        System.out.println("Came to Given of tag1");
    }

    @Given("Browser open2")
    public void browser_open2(){
        System.out.println("Came to Given of tag2");
    }

    @Given("Browser open3")
    public void browser_open3(){
        System.out.println("Came to Given of tag3");
    }
    @When("Read the value")
    public void read_the_value(){
        System.out.println("Came to When");
    }

    @Then("Validate the text")
    public void validate_the_text(){
        System.out.println("Came to Then");
    }
}
