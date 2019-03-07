package org.imaginarium.selenium.my.first.tests.on.java.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GoogleTests extends BasePage {

    public String textToSearch = "automation";
    private int numberOfPagesToCheck = 5;
    private String domainToCheck = "testautomationday.com";

    @FindBy(id = "rso")
    private WebElement searchResults;

    @FindBy(css = ".g")
    private WebElement elementFromResults;

    public GoogleTests(WebDriver driver) {
        super(driver);
    }

    public void enterTextToSearch() {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(textToSearch);
        searchBox.sendKeys(Keys.ENTER);
    }

    public void clickFirstResult() {
        this.searchResults.findElements(By.cssSelector(".g")).get(0).findElement(By.className("LC20lb")).click();
//        this.searchResults.findElements(By.cssSelector(".g")).get(0).findElement(By.className("LC20lb")).click();
//        WebElement results = driver.findElement(By.id("rso"));
//        results.findElements(By.cssSelector(".g")).get(0).findElement(By.className("LC20lb")).click();
    }

    public boolean checkIfDomainPresentInResultOnXPages() {
        //initial conditions
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

            //navigate to the next page
            driver.findElement(By.id("pnnext")).click();
            page++;
        }
        return isDomainExists;
    }
}
