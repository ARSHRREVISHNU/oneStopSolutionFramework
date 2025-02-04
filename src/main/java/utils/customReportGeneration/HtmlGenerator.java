package utils.customReportGeneration;

import java.util.List;

public class HtmlGenerator {
    public static String generateHtmlReport(List<TestRun> testRuns) {
        StringBuilder html = new StringBuilder();

        // HTML Header
        html.append("<!DOCTYPE html>");
        html.append("<html lang='en'>");
        html.append("<head>");
        html.append("<meta charset='UTF-8'>");
        html.append("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        html.append("<title>Test Report</title>");
        html.append("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css'>");
        html.append("<script src='https://cdn.jsdelivr.net/npm/chart.js'></script>");
        html.append("<style>");
        html.append("body { font-family: 'Roboto', Arial, sans-serif; margin: 0; padding: 0; background-color: #f8f9fa; color: #333; }");
        html.append("header { background: linear-gradient(135deg, #007bff, #0056b3); color: white; padding: 20px; text-align: center; }");
        html.append("h1 { margin: 0; font-size: 2rem; }");
        html.append("p { margin: 0; font-size: 1rem; }");
        html.append("main { padding: 20px; max-width: 1200px; margin: 0 auto; }");
        html.append("table { width: 100%; border-collapse: collapse; margin-bottom: 20px; background: white; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); border-radius: 8px; overflow: hidden; }");
        html.append("th, td { padding: 12px; text-align: left; }");
        html.append("th { background-color: #007bff; color: white; font-weight: bold; }");
        html.append("tr:nth-child(even) { background-color: #f2f2f2; }");
        html.append(".chart-container { margin: 30px 0; background: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); }");
        html.append(".chart-header { text-align: center; margin-bottom: 20px; }");
        html.append(".chart-header h2 { margin: 0; font-size: 1.5rem; color: #007bff; }");
        html.append(".footer { text-align: center; padding: 15px 0; color: #555; font-size: 0.9rem; }");
        html.append("</style>");
        html.append("</head>");

        // Body Start
        html.append("<body>");
        html.append("<header>");
        html.append("<h1>Test Report Dashboard</h1>");
        html.append("<p>Visual representation of test results across multiple runs</p>");
        html.append("</header>");
        html.append("<main>");

        // Summary Table
        html.append("<h2>Summary of Test Runs</h2>");
        html.append("<table>");
        html.append("<tr><th>Run Date</th><th>Total Tests</th><th>Passed</th><th>Failed</th><th>Pass Percentage</th></tr>");
        for (TestRun run : testRuns) {
            int totalTests = run.getTotal_tests();
            int passedTests = run.getPassed_tests();
            double passPercentage = totalTests > 0 ? (passedTests * 100.0) / totalTests : 0;

            html.append("<tr>");
            html.append("<td>").append(run.getRun_date()).append("</td>");
            html.append("<td>").append(totalTests).append("</td>");
            html.append("<td>").append("<span style='color:green; font-weight:bold;'>").append(passedTests).append("</span></td>");
            html.append("<td>").append("<span style='color:red; font-weight:bold;'>").append(run.getFailed_tests()).append("</span></td>");
            html.append("<td>").append(String.format("%.2f%%", passPercentage)).append("</td>");
            html.append("</tr>");
        }
        html.append("</table>");

        // Graph Section
        html.append("<div class='chart-container'>");
        html.append("<div class='chart-header'>");
        html.append("<h2>Pass Percentage Over Time</h2>");
        html.append("</div>");
        html.append("<canvas id='passChart'></canvas>");
        html.append("</div>");

        // Chart Script
        html.append("<script>");
        html.append("const ctx = document.getElementById('passChart').getContext('2d');");
        html.append("const passChart = new Chart(ctx, {");
        html.append("type: 'line',");
        html.append("data: {");
        html.append("labels: [");
        for (int i = 0; i < testRuns.size(); i++) {
            html.append("'Run ").append(i + 1).append("',");
        }
        html.append("],");
        html.append("datasets: [{");
        html.append("label: 'Pass Percentage',");
        html.append("data: [");
        for (TestRun run : testRuns) {
            int totalTests = run.getTotal_tests();
            int passedTests = run.getPassed_tests();
            double passPercentage = totalTests > 0 ? (passedTests * 100.0) / totalTests : 0;
            html.append(passPercentage).append(",");
        }
        html.append("], backgroundColor: 'rgba(54, 162, 235, 0.2)', borderColor: 'rgba(54, 162, 235, 1)', borderWidth: 2, tension: 0.3 }]");
        html.append("},");
        html.append("options: { responsive: true, plugins: { legend: { position: 'top', }, title: { display: true, text: 'Pass Percentage per Run' } }, scales: { y: { beginAtZero: true, max: 100 } } }");
        html.append("});");
        html.append("</script>");

        // Footer
        html.append("</main>");
        html.append("<footer class='footer'>");
        html.append("<p>Generated by Test Automation Framework &copy; 2025</p>");
        html.append("</footer>");
        html.append("</body>");
        html.append("</html>");

        return html.toString();
    }
}
