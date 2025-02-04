package utils.customReportGeneration;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;

public class JsonReader {
    public static List<TestRun> readTestResults(String filePath) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return List.of(mapper.readValue(new File(filePath), TestRun[].class));
    }
}
