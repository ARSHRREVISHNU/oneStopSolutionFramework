package utils.customReportGeneration;

import java.util.List;

public class TestRun {
    private String run_date;
    private int total_tests;
    private int passed_tests;
    private int failed_tests;
    private List<Scenario> scenarios;

    // Getters and Setters
    public String getRun_date() {
        return run_date;
    }

    public void setRun_date(String run_date) {
        this.run_date = run_date;
    }

    public int getTotal_tests() {
        return total_tests;
    }

    public void setTotal_tests(int total_tests) {
        this.total_tests = total_tests;
    }

    public int getPassed_tests() {
        return passed_tests;
    }

    public void setPassed_tests(int passed_tests) {
        this.passed_tests = passed_tests;
    }

    public int getFailed_tests() {
        return failed_tests;
    }

    public void setFailed_tests(int failed_tests) {
        this.failed_tests = failed_tests;
    }

    public List<Scenario> getScenarios() {
        return scenarios;
    }

    public void setScenarios(List<Scenario> scenarios) {
        this.scenarios = scenarios;
    }

    // Static Nested Classes
    public static class Scenario {
        private String name;
        private String status;
        private List<Step> steps;

        // Getters and Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<Step> getSteps() {
            return steps;
        }

        public void setSteps(List<Step> steps) {
            this.steps = steps;
        }
    }

    public static class Step {
        private String name;
        private String status;

        // Getters and Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
