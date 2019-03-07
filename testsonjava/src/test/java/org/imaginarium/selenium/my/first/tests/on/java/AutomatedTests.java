package org.imaginarium.selenium.my.first.tests.on.java;

import org.imaginarium.selenium.my.first.tests.on.java.PageObject.GoogleTests;
import org.junit.*;

public class AutomatedTests extends BaseTest {

    @Test
    public void checkTitleOfFirstResult() {
        GoogleTests google = new GoogleTests(driver);

        google.enterTextToSearch();
        google.clickFirstResult();

        String pageTitle = driver.getTitle();

        Assert.assertTrue("Page title doesn't contain the expected text",
                pageTitle.toLowerCase().contains(google.textToSearch));
    }

    @Test
    public void checkIfDomainIsPresent() {

        GoogleTests google = new GoogleTests(driver);

        google.enterTextToSearch();

        boolean isDomainExists = google.checkIfDomainPresentInResultOnXPages();

        Assert.assertTrue("Domain wasn't found", isDomainExists);
    }
}
