package com.rstudio.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * LoginPage.java - a helper class for managing RStudio login page objects.
 * @author  Maciej Kocol
 * @version 1.0
 */
public class LoginPage extends BasePage{

    // fields
    @FindBy(xpath="//input[@name='email']")
    private WebElement userField;
    @FindBy(xpath="//input[@name='password']")
    private WebElement passField;

    // buttons
    @FindBy(xpath="//button[text()='Log in']")
    private WebElement loginButton;

    // credentials
    private String user = "";
    private String pass = "";

    // titles
    public String pageTitle = "RStudio";

    public LoginPage(WebDriver driver, String user, String pass){
        super(driver);
        this.user = user;
        this.pass = pass;
    }

    /**
     * This method logs a user into RStudio.
     */
    public void login() throws InterruptedException {
        wait.until(d -> userField.isDisplayed());
        wait.until(d -> passField.isDisplayed());
        wait.until(d -> loginButton.isDisplayed());
        fillForm(userField, user);
        fillForm(passField, pass);
        loginButton.click();
    }

    /**
     * This method fills out a form.
     */
    public void fillForm(WebElement element, String keys) throws InterruptedException {
        element.clear();
        element.sendKeys(keys);
    }
}
