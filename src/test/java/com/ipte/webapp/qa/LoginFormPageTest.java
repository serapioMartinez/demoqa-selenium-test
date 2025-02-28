package com.ipte.webapp.qa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

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
    void succesfullLoginWhenCredentialsValid(String username, String password) throws IOException{

        login.visitPagePath();

        //When
        login.scrollToPoint(login.getUsernameElement().getLocation());
        login.getUsernameElement().sendKeys(username);
        login.getPasswordElement().sendKeys(password);
        login.getLoginButtonElement().click();

        //Then
        assertEquals(username,profile.getUsernameLabelElement().getText());
        assertTrue(getDriver().getCurrentUrl().contains(ProfilePage.PATH));
    }

    @Test
    void loginFailedWhenCredentialsAreInvalid() throws IOException{
        final String USERNAME = "nonexistsusername";
        final String PASSWORD = "password";

        login.visitPagePath();

        //When
        login.scrollToPoint(login.getUsernameElement().getLocation());
        login.getUsernameElement().sendKeys(USERNAME);
        login.getPasswordElement().sendKeys(PASSWORD);
        login.getLoginButtonElement().click();

        //Then
        assertTrue(login.waitErrorBannerToBeDisplayed().getText().contains("Invalid"));
    }
}
