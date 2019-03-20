package org.imaginarium.selenium.my.first.tests.on.java;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.awt.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected static WebDriver driver;

    static File juReport;
    static BufferedWriter juWriter;

    @BeforeClass
    public static void setUp() throws IOException {

        //region HTML Report
        String projectDir = System.getProperty("user.dir");
        String juReportFile = projectDir + "\\ReportFile.html";
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        Date date = new Date();
        juReport = new File(juReportFile);
        juWriter = new BufferedWriter(new FileWriter(juReport, true));
        juWriter.write("<html><body>");
        juWriter.write("<h1>Test Execution Report - " + dateFormat.format(date)
                + "</h1>");
        //endregion

        WebDriverManager.chromedriver().setup();
//        WebDriverManager.iedriver().arch32().setup();
//        System.setProperty("webdriver.chrome.logfile", "chromedriver.log");

        driver = new ChromeDriver(); // InternetExplorerDriver();
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
        // useless for now;
    }

    @AfterClass
    public static void tearDown() throws IOException {

        //region HTML Report
        juWriter.write("</body></html>");
        juWriter.close();
        Desktop.getDesktop().browse(juReport.toURI());
        //endregion

        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

    //region HTML Report Watcher
    @Rule
    public TestRule testWatcher = new TestWatcher() {

        @Override
        public Statement apply(Statement base, Description description) {
            return super.apply(base, description);
        }

        @Override
        protected void succeeded(Description description) {
            try {
                juWriter.write(description.getDisplayName().split("\\(", 2)[0] + " "
                        + "Passed"); // html
                System.out.println(description.getDisplayName().split("\\(", 2)[0] + " "
                        + "Passed"); // console
                juWriter.write("<br/>");
            } catch (Exception e1) {
                System.out.println(e1.getMessage());
            }
        }

        @Override
        protected void failed(Throwable e, Description description) {
            try {
                juWriter.write(description.getDisplayName().split("\\(", 2)[0] + " "
                        + e.getClass().getSimpleName() + " " + e.getMessage()); // html
                System.out.println(description.getDisplayName().split("\\(", 2)[0] + " "
                        + e.getClass().getSimpleName() + " " + e.getMessage()); // console
                juWriter.write("<br/>");
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
        }
    };
    //endregion
}
