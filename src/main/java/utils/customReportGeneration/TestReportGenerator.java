package utils.customReportGeneration;

import java.util.List;

public class TestReportGenerator {
    public static void main(String[] args) throws Exception {
        // Path to JSON file
        String jsonFilePath = "src/test/resources/CustomReport/test_results.json";

        // Read JSON data
        List<TestRun> testRuns = JsonReader.readTestResults(jsonFilePath);

        // Generate HTML report
        String htmlContent = HtmlGenerator.generateHtmlReport(testRuns);

        // Save HTML to file
        String htmlFilePath = "src/test/resources/CustomReport/test_report.html";
        HtmlWriter.writeHtmlToFile(htmlContent, htmlFilePath);

        System.out.println("HTML report generated at: " + htmlFilePath);
    }
}
