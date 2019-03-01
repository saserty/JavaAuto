package org.imaginarium.selenium.my.first.tests.on.java;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class TestsSettings {

    protected static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
//        WebDriverManager.iedriver().arch32().setup();

//        System.setProperty("webdriver.chrome.logfile", "chromedriver.log");

        driver = new ChromeDriver(); //InternetExplorerDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Before
    public void testSetUp() {
        driver.get("https://www.google.com/");
        driver.navigate().refresh();
    }

    @After
    public void cleanUp() {
        //driver.manage().deleteAllCookies();
    }

    @AfterClass
    public static void tearDown() {
        driver.close();
        driver.quit();
    }

}
