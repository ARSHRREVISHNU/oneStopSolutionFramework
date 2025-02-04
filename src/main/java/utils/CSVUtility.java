package utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CSVUtility {

    private CSVUtility() {
        // Private constructor to prevent instantiation
    }

    /**
     * Reads a specific element from a CSV file.
     *
     * @param filePath   The path to the CSV file.
     * @param rowNumber  The 1-based index of the row to read (excluding headers).
     * @param columnName The name of the column.
     * @return The value at the specified row and column, or null if not found.
     * @throws IOException            If there is an issue reading the file.
     * @throws CsvValidationException If the CSV file is invalid.
     */
    public static String readSpecificElement(String filePath, int rowNumber, String columnName)
            throws IOException, CsvValidationException {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            // Read the header row
            String[] headers = reader.readNext();
            if (headers == null) {
                throw new IllegalArgumentException("CSV file is empty.");
            }

            // Find the column index for the specified column name
            int columnIndex = getColumnIndex(headers, columnName);
            if (columnIndex == -1) {
                throw new IllegalArgumentException("Column not found: " + columnName);
            }

            // Read rows until the specified row number is reached
            String[] row = null;
            int currentRow = 0;
            while ((row = reader.readNext()) != null) {
                currentRow++;
                if (currentRow == rowNumber) {
                    return row[columnIndex];
                }
            }
        }
        return null; // Row not found
    }

    /**
     * Gets the index of the column based on the header name.
     *
     * @param headers    The array of column headers.
     * @param columnName The name of the column.
     * @return The index of the column, or -1 if not found.
     */
    private static int getColumnIndex(String[] headers, String columnName) {
        for (int i = 0; i < headers.length; i++) {
            if (headers[i].equalsIgnoreCase(columnName)) {
                return i;
            }
        }
        return -1;
    }
}
