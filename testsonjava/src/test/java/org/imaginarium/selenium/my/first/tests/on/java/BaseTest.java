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

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
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
        juWriter.write("<table>" + "<tr>"
                + "<th>" + "STATUS" + "</th>"
                + "<th>" + "TEST" + "</th>"
                + "</tr>" + "</table>");
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

        private Date startTime;
        private Date stopTime;
        private Icon okTest = new ImageIcon("src/report/images/icons8-ok-48.png");
        private Icon failTest = new ImageIcon("src/report/images/icons8-cancel-48.png");
        private Icon skipTest = new ImageIcon("src/report/images/icons8-skip-48.png");

        @Override
        public Statement apply(Statement base, Description description) {
            return super.apply(base, description);
        }

        @Override
        protected void succeeded(Description description) {
            try {
                juWriter.write("<img src=" + okTest + " width=\"12\" height=\"12\">" + "&emsp;"
                        + description.getDisplayName().split("\\(", 2)[0] + " "
                        + "<font color=\"green\">" + "&emsp;Passed" + "</font>"); // html
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
                juWriter.write("<img src=" + failTest + " width=\"12\" height=\"12\">" + "&emsp;"
                        + description.getDisplayName().split("\\(", 2)[0] + " "
                        + "<font color=\"red\">" + "&emsp;Failed" + "</font>" + "&emsp;"
                        + e.getClass().getSimpleName() + "&emsp;" + e.getMessage()); // html
                System.out.println(description.getDisplayName().split("\\(", 2)[0] + " "
                        + e.getClass().getSimpleName() + " " + e.getMessage()); // console
                juWriter.write("<br/>");
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
        }

        @Override
        protected void starting(Description description) {
            this.startTime = new Date();
        }

        @Override
        protected void finished(Description description) {
            try {
                DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                this.stopTime = new Date();
                Duration duration = Duration.between(startTime.toInstant(), stopTime.toInstant());

                juWriter.write(String.format(
                        "<table>" + "<tr>"/* + description.*/
                        + "<td>" + "&emsp;&emsp;" + "Start time: " + dateFormat.format(startTime) + "</td>"
                        + "<td>" + "&emsp;" + "Stop time: " + dateFormat.format(stopTime) + "</td>"
                        + "<td>" + "&emsp;" + "Duration: " + duration.toMinutes() + ":" + duration.getSeconds() % 60
                                + "." + String.format("%1$tL", duration.toMillis() % 1000) + "</td>"
                        + "</tr>" + "</table>"
                ));
                // number of tests: 2. passed: 1. failed: 1. skipped: 0
                System.out.println("  Start time: " + dateFormat.format(startTime)
                        + "  Stop time: " + dateFormat.format(stopTime)
                        + "  Duration: " + duration.toMinutes() + ":" + duration.getSeconds() % 60
                        + "." + String.format("%1$tL", duration.toMillis() % 1000));
                juWriter.write("<br/>");
            } catch (Exception e4) {
                System.out.println(e4.getMessage());
            }
        }
    };
    //endregion
}
