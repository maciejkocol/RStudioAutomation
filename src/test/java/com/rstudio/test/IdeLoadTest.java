package com.rstudio.test;

import com.rstudio.pages.LoginPage;
import com.rstudio.pages.ProjectsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * ProjectsTest.java - a simple class for validating that the RStudio IDE loads.
 * @author  Maciej Kocol
 * @version 1.0
 * @see ProjectsPage
 */
public class IdeLoadTest extends BaseTest {
    ProjectsPage projectsPage;

    @BeforeMethod
    @Parameters({"user", "pass"})

    public void _setup(@Optional("") String user, @Optional("") String pass) throws Exception{

        // Navigate to RStudio
        driver.get("https://client.login.rstudio.cloud/oauth/login?show_auth=0&show_login=1&show_setup=1");

        // Init login
        loginPage = new LoginPage(driver, user, pass);

        // Verify page title
        Assert.assertEquals(loginPage.getPageTitle(), loginPage.pageTitle);

        // Log in
        loginPage.login();
    }

    /**
     * This test case will navigate to https://rstudio.cloud/projects
     * Create a space, a project within the space, and verify that RStudio IDE loads
     * @throws Exception
     */
    @Test(priority = 0)
    @Parameters({"user", "pass"})
    public void test_Project_IDE_Loads(@Optional("") String user, @Optional("") String pass) throws Exception{

        // Create Projects page object
        projectsPage = new ProjectsPage(driver);

        // Verify projects page title
        Assert.assertEquals(projectsPage.getPageTitle(), projectsPage.pageTitle);

        // Create new space
        projectsPage.createNewSpace("testspace");

        // Verify new space is active
        Assert.assertEquals(projectsPage.getActiveSpaceName(), "testspace");

        // Create new project within space
        projectsPage.createNewProjectWithinSpace();

        // Switch to IDE iframe
        projectsPage.switchToIdeFrame();

        // Verify IDE console is present
        Assert.assertTrue(projectsPage.ideConsolePaneDisplayed(), "IDE console pane not displayed.\n");

        // Verify IDE console contains default text
        Assert.assertTrue(projectsPage.getIdeConsoleText().contains("R version 3.5.0 (2018-04-23) -- \"Joy in Playing\""), "IDE console text not found.\n");

    }
}
