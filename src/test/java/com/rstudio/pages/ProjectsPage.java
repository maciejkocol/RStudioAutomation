package com.rstudio.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * ProjectsPage.java - a helper class for managing Projects page objects.
 * @author  Maciej Kocol
 * @version 1.0
 */
public class ProjectsPage extends BasePage {

    // buttons
    @FindBy(xpath="//button[@type='submit']")
    private WebElement newSpaceButton;
    @FindBy(xpath="//button[contains(@class,'Project menuInstant')]/span[text()='New Project']")
    private WebElement newProjectWithinSpaceButton;

    // links
    @FindBy(xpath="//div[@class='menuItem newSpace']")
    private WebElement newSpaceLink;
    @FindBy(xpath="//a[@class='menuItem space  active']")
    private WebElement activeSpaceLink;

    // fields
    @FindBy(xpath="//div[@class='modalDialog']/form//input[@id='name']")
    private WebElement newSpaceNameField;

    // IDE panes
    @FindBy(xpath="//body[@class='macintosh rstudio-themes-flat']//div[contains(@class,'GC1LDUECIV')]//span[@class='GC1LDUECLVB']")
    private WebElement ideConsolePane;

    // frames
    private By ideFrame = By.xpath("//iframe[@id='contentIFrame']");

    // titles
    public String pageTitle = "RStudio Cloud";

    public ProjectsPage(WebDriver driver) throws InterruptedException{
        super(driver);
    }

    /**
     * This method creates a new space.
     * @param name Name of space to create
     */
    public void createNewSpace(String name){
        wait.until(d -> newSpaceLink.isDisplayed());
        newSpaceLink.click();
        wait.until(d -> newSpaceNameField.isDisplayed());
        wait.until(d -> newSpaceButton.isDisplayed());
        newSpaceNameField.sendKeys(name);
        newSpaceButton.click();
    }

    /**
     * This method creates a new project within an active space.
     */
    public void createNewProjectWithinSpace(){
        wait.until(d -> newProjectWithinSpaceButton.isDisplayed());
        newProjectWithinSpaceButton.click();
    }

    /**
     * This method indicates whether IDE console pane is displayed.
     * @return Boolean indicating true or false
     */
    public Boolean ideConsolePaneDisplayed(){
        wait.until(d -> ideConsolePane.isDisplayed());
        return ideConsolePane.isDisplayed();
    }

    /**
     * This method gets the text inside the IDE console.
     * @return Console text
     */
    public String getIdeConsoleText(){
        return ideConsolePane.getText();
    }

    /**
     * This method switches to IDE frame once available.
     */
    public void switchToIdeFrame(){
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(ideFrame));
    }

    /**
     * This method gets the active space name.
     * @return Space name
     */
    public String getActiveSpaceName(){
        return activeSpaceLink.getText();
    }
}