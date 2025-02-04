package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;

public class DriverManager {

    private ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    public WebDriver initializeDriver() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();
        WebDriver driver;

        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                if(System.getProperty("headless").contentEquals("yes")) {
                    FirefoxOptions options = new FirefoxOptions();
                    options.addArguments("--headless");
                    driver = new FirefoxDriver(options);
                }
                else{
                    driver = new FirefoxDriver();
                }
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                if(System.getProperty("headless").contentEquals("yes")) {
                    EdgeOptions options = new EdgeOptions();
                    options.addArguments("--headless");
                    driver = new EdgeDriver(options);
                }
                else{
                    driver = new EdgeDriver();
                }
                break;
            case "chrome":
                WebDriverManager.chromedriver().setup();
                if(System.getProperty("headless").contentEquals("yes")){
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("--remote-debugging-port=9222");
                    driver = new ChromeDriver(chromeOptions);
                }
                else {
                    driver = new ChromeDriver();
                }
                break;
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
             break;
        }
        driverThreadLocal.set(driver);
        return driverThreadLocal.get();
    }
}
