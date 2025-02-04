package stepdefinitions;

import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.AxeBuilder;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import utils.TestContext;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class BasicFunctionality extends  BaseSetup{

    TestContext testContext;
    private Results accessibilityResults;
    private String testedUrl;
    private String reportPath = "./lighthouse-report.html";
    public BasicFunctionality(TestContext testContext) {
        super(testContext);
        this.testContext = testContext;
    }
    @Given("the user opens the browser and navigates to the login page")
    public void the_user_opens_the_browser_and_navigates_to_the_login_page() {
        driver.manage().window().maximize();
        // Navigate to the login page
        driver.get("https://www.thangamayil.com/customer/account/login/");
        testedUrl = "https://www.thangamayil.com/customer/account/login/";
    }

    @When("I analyze the page for accessibility")
    public void iAnalyzeThePageForAccessibility() throws IOException {
        // Perform accessibility analysis
        accessibilityResults = new AxeBuilder().analyze(driver);

        // Save the results to an HTML file with CSS styling
        try (FileWriter writer = new FileWriter("accessibility-report.html")) {
            writer.write("<!DOCTYPE html>");
            writer.write("<html lang='en'><head>");
            writer.write("<meta charset='UTF-8'>");
            writer.write("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            writer.write("<title>Accessibility Report</title>");
            writer.write("<style>");
            writer.write("body { font-family: Arial, sans-serif; margin: 20px; line-height: 1.6; }");
            writer.write("h1 { color: #333; text-align: center; }");
            writer.write("h2 { color: #555; }");
            writer.write("p { margin: 5px 0; }");
            writer.write(".violation { border: 1px solid #ddd; padding: 15px; margin-bottom: 15px; border-radius: 5px; background: #f9f9f9; }");
            writer.write(".violation.critical { border-left: 5px solid #e74c3c; }");
            writer.write(".violation.serious { border-left: 5px solid #e67e22; }");
            writer.write(".violation.moderate { border-left: 5px solid #f1c40f; }");
            writer.write(".violation.minor { border-left: 5px solid #2ecc71; }");
            writer.write("a { color: #3498db; text-decoration: none; }");
            writer.write("a:hover { text-decoration: underline; }");
            writer.write("</style>");
            writer.write("</head><body>");

            // Report header
            writer.write("<h1>Accessibility Violations Report</h1>");
            writer.write("<p><strong>Tested URL:</strong> " + testedUrl + "</p>");
            writer.write("<p><strong>WCAG Version:</strong> WCAG 2.1</p>");
            writer.write("<p><strong>Conformance Levels:</strong> A, AA, AAA</p>");
            writer.write("<p><strong>Report Generated On:</strong> " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "</p>");

            // Violations
            if (accessibilityResults.getViolations().isEmpty()) {
                writer.write("<p>No accessibility violations found!</p>");
            } else {
                writer.write("<h2>Violations Found:</h2>");
                for (Rule violation : accessibilityResults.getViolations()) {
                    String impactClass = violation.getImpact() != null ? violation.getImpact() : "minor";
                    writer.write("<div class='violation " + impactClass + "'>");
                    writer.write("<h3>" + violation.getId() + ": " + violation.getDescription() + "</h3>");
                    writer.write("<p><strong>Impact:</strong> " + violation.getImpact() + "</p>");
                    writer.write("<p><strong>Help:</strong> <a href='" + violation.getHelpUrl() + "' target='_blank'>" + violation.getHelp() + "</a></p>");
                    writer.write("<p><strong>WCAG Tags:</strong> " + String.join(", ", violation.getTags()) + "</p>");
                    writer.write("</div>");
                }
            }

            writer.write("</body></html>");
        }
    }

    @Then("I should see no critical accessibility violations")
    public void iShouldSeeNoCriticalAccessibilityViolations() {
        // Assert that there are no critical violations
        List<Rule> criticalViolations = accessibilityResults.getViolations().stream()
                .filter(violation -> "critical".equals(violation.getImpact()))
                .toList();

        assertTrue("Critical accessibility violations found!", criticalViolations.isEmpty());

        // Quit the driver
       // driver.quit();
    }
    @Then("^I run Lighthouse tests and generate a report$")
    public void runLighthouseAndGenerateReport() {
        try {
            runLighthouse(driver.getCurrentUrl(), reportPath);
            System.out.println("Lighthouse report generated at: " + reportPath);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    private void runLighthouse(String url, String outputPath) throws Exception {
        // Construct Lighthouse command
        String command = String.format(
                "lighthouse %s --output html --output-path %s --quiet",
                url, outputPath
        );

        // Execute Lighthouse command
        Process process = Runtime.getRuntime().exec(command);

        // Capture the output (if needed)
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        // Wait for the process to complete
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Lighthouse process failed with exit code " + exitCode);
        }
    }

}
