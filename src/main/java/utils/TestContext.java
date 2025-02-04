package utils;

import org.openqa.selenium.WebDriver;

public class TestContext {

     private ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
     public void setDriver(WebDriver driver) {
          driverThreadLocal.set(driver);
     }

     // Method to get the driver for the current thread
     public WebDriver getDriver() {
          return driverThreadLocal.get();
     }
}
