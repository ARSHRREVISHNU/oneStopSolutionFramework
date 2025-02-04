package utils.customReportGeneration;

import java.io.FileWriter;

public class HtmlWriter {
    public static void writeHtmlToFile(String htmlContent, String filePath) throws Exception {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(htmlContent);
        }
    }
}
