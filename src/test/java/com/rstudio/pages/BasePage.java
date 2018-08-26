package com.rstudio.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * BasePage.java - a helper class for managing shared page objects.
 * @author  Maciej Kocol
 * @version 1.0
 */
public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;

    public int sleepTime = 1000;

    public BasePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }

    /**
     * This method gets the current page title.
     * @return Page title as string
     */
    public String getPageTitle(){
        return driver.getTitle();
    }

    /**
     * This method waits for a given element on page to load.
     */
    public void waitForPageToLoad(WebElement element) throws InterruptedException{
        wait.until(d -> element.isDisplayed());
    }
}