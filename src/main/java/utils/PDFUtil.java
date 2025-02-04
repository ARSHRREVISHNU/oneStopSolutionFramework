package utils;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class PDFUtil {

    // Static method to read text from a PDF file
    public static String readPDF(String filePath) {
        File pdfFile = new File(filePath);
        try (PDDocument document = PDDocument.load(pdfFile)) {
            if (!document.isEncrypted()) {
                return extractTextFromPDF(document);
            } else {
                throw new IOException("The PDF is encrypted and cannot be read.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read PDF file: " + e.getMessage(), e);
        }
    }

    // Private static method to extract text from a PDF document
    private static String extractTextFromPDF(PDDocument document) throws IOException {
        PDFTextStripper pdfStripper = new PDFTextStripper();
        return pdfStripper.getText(document);
    }

    // Example usage
    public static void main(String[] args) {
        String filePath = "path/to/your/pdf-file.pdf";
        
        try {
            String pdfContent = PDFUtil.readPDF(filePath);
            System.out.println("PDF Content:\n" + pdfContent);
        } catch (RuntimeException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
