package org.imaginarium.selenium.my.first.tests.on.java;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public final class Singleton {

    private static Singleton sInstance = new Singleton();

//    public static setInstance(Singleton instance) {
//        sInstance = instance;
//    }

    private static WebDriver driver = null;
    private static String browser = "chrome";

    protected Singleton() {}

//    public static Singleton getDriver() {
//        if (driver == null) {
//            driver = new Singleton();
//
//                if (browser.contains("chrome")) {
//                    WebDriverManager.chromedriver().setup();
////        WebDriverManager.iedriver().arch32().setup();
////        System.setProperty("webdriver.chrome.logfile", "chromedriver.log");
//                    driver = new ChromeDriver(); // InternetExplorerDriver();
//                } else if (browser.contains("ie")) {
//                    WebDriverManager.iedriver().arch32().setup();
//
//                    driver = new InternetExplorerDriver();
//                }
//        }
//        return driver;
//    }
}
