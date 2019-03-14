package org.imaginarium.selenium.my.first.tests.on.java;

import org.imaginarium.selenium.my.first.tests.on.java.PageObject.GoogleTests;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class AutomatedTests extends BaseTest {

    private String textToSearch;
    private int numberOfPagesToCheck;
    private String domainToCheck;

    public AutomatedTests(String textToSearch, int numberOfPagesToCheck, String domainToCheck) {
        this.textToSearch = textToSearch;
        this.numberOfPagesToCheck = numberOfPagesToCheck;
        this.domainToCheck = domainToCheck;
    }

    @Parameterized.Parameters
    public static List<Object[]> testParams() {
        return Arrays.asList(new Object[][] {
                { "automation", 5, "testautomationday.com"},
        });
    }

    @Test
    public void checkTitleOfFirstResult() {
        GoogleTests google = new GoogleTests(driver);

        google.enterTextToSearch(textToSearch);
        google.clickFirstResult();

        String pageTitle = driver.getTitle();

        Assert.assertTrue("Page title doesn't contain the expected text",
                pageTitle.toLowerCase().contains(textToSearch));
    }

    @Test
    public void checkIfDomainIsPresent() {

        GoogleTests google = new GoogleTests(driver);

        google.enterTextToSearch(textToSearch);

        boolean isDomainExists = google.checkIfDomainPresentInResultOnXPages(numberOfPagesToCheck, domainToCheck);

        Assert.assertTrue("Domain wasn't found", isDomainExists);
    }
}
