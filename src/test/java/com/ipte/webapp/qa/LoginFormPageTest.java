package com.ipte.webapp.qa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ipte.webapp.base.Hooks;
import com.ipte.webapp.base.TestListener;
import com.ipte.webapp.pageobject.LoginFormPage;
import com.ipte.webapp.pageobject.ProfilePage;

@Listeners(TestListener.class)
public class LoginFormPageTest extends Hooks{

    private LoginFormPage login = new LoginFormPage();
    private ProfilePage profile = new ProfilePage();

    @Test(
        dependsOnMethods = {"com.ipte.webapp.qa.RegisterFormPageTest.validateSuccesfullRegistration"}, 
        alwaysRun = false, 
        ignoreMissingDependencies = false)
    @Parameters({"username","password"})
    void succesfullLoginWhenCredentialsValid(String username, String password, ITestContext testContext) throws IOException{
        ExtentTest test = Hooks.extentTests.get();
        test.assignCategory(testContext.getName());

        login.visitPagePath();
        test.log(Status.PASS, "User visits Register Form Page path");

        //When
        login.scrollToPoint(login.getUsernameElement().getLocation());
        test.log(Status.PASS, "User scrolls to Username element");
        login.getUsernameElement().sendKeys(username);
        test.log(Status.PASS, "User input username: "+username);
        login.getPasswordElement().sendKeys(password);
        test.log(Status.PASS, "User input password: "+password);
        login.getLoginButtonElement().click();
        test.log(Status.PASS, "User click submit button");

        //Then
        assertEquals(username,profile.getUsernameLabelElement().getText());
        assertTrue(getDriver().getCurrentUrl().contains(ProfilePage.PATH));
        test.log(Status.PASS, "User is logged into the profile page");
    }

    @Test
    void loginFailedWhenCredentialsAreInvalid(ITestContext testContext) throws IOException{
        ExtentTest test = Hooks.extentTests.get();
        test.assignCategory(testContext.getName());

        final String USERNAME = "nonexistsusername";
        final String PASSWORD = "password";

        login.visitPagePath();
        test.log(Status.PASS, "User visits Register Form Page path");

        //When
        login.scrollToPoint(login.getUsernameElement().getLocation());
        test.log(Status.PASS, "User scrolls to Username element");
        login.getUsernameElement().sendKeys(USERNAME);
        test.log(Status.PASS, "User input username: "+USERNAME);
        login.getPasswordElement().sendKeys(PASSWORD);
        test.log(Status.PASS, "User input password: "+PASSWORD);
        login.getLoginButtonElement().click();
        test.log(Status.PASS, "User click submit button");

        //Then
        assertTrue(login.waitErrorBannerToBeDisplayed().getText().contains("Invalid"));
        test.log(Status.PASS, "Error banner is displayed by Invalid credntials");
    }
}
