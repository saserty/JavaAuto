package org.imaginarium.selenium.my.first.tests.on.java.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GoogleTests extends BasePage {

    public GoogleTests(WebDriver driver) {
        super(driver);
    }

    public void enterTextToSearch(String textToSearch) {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(textToSearch);
        searchBox.sendKeys(Keys.ENTER);
    }

    public void clickFirstResult() {
        WebElement results = driver.findElement(By.id("rso"));
        results.findElements(By.cssSelector(".g")).get(0).findElement(By.className("LC20lb")).click();
    }

    public boolean checkIfDomainPresentInResultOnXPages(int numberOfPagesToCheck, String domainToCheck) {
        // initial conditions
        int page = 1;
        boolean isDomainExists = false;

        outerloop:
        while (page <= numberOfPagesToCheck) {
            WebElement results = driver.findElement(By.id("rso"));

            List<WebElement> resultsList = results.findElements(By.cssSelector(".g"));
            for (WebElement we : resultsList) {
                if (we.findElement(By.className("iUh30")).getText().contains(domainToCheck)) {
                    isDomainExists = true;
                    break outerloop;
                } else {
                    isDomainExists = false;
                }
            }

            // navigate to the next page. 'if' to not move to the extra page
            if (page != numberOfPagesToCheck) {
                driver.findElement(By.id("pnnext")).click();
                page++;
            } else {page++;}
        }
        return isDomainExists;
    }
}
