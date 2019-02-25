package org.imaginarium.selenium.my.first.tests.on.java;

import org.imaginarium.selenium.my.first.tests.on.java.PageObject.GoogleTestConditions;
import org.junit.*;

public class AutomatedTests extends TestsSettings{

    @Test
    public void checkTitleOfFirstResult() {
        GoogleTestConditions google = new GoogleTestConditions(driver);

        google.enterTextToSearch();
        google.clickFirstResult();

        String pageTitle = driver.getTitle();

        Assert.assertTrue(pageTitle.toLowerCase().contains(google.textToSearch));
    }

    @Test
    public void checkIfDomainIsPresent() {

        GoogleTestConditions google = new GoogleTestConditions(driver);

        google.enterTextToSearch();

        //requirement is to check pages 1-5
        boolean isDomainExists = google.checkIfDomainPresentInResultOnXPages();

        Assert.assertTrue(isDomainExists);
    }
}
