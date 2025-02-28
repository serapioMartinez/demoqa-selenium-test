package com.ipte.webapp.qa;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.time.Duration;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ipte.webapp.base.Hooks;
import com.ipte.webapp.base.TestListener;
import com.ipte.webapp.pageobject.RegisterFormPage;

@Listeners(TestListener.class)
public class RegisterFormPageTest extends Hooks {

    RegisterFormPage register = new RegisterFormPage();

    @Test
    void validateFormIsNotSentWhenInpuFieldIsEmpty(ITestContext testContext) throws IOException {
        ExtentTest test = Hooks.extentTests.get();
        test.assignCategory(testContext.getName());
        // Given
        register.visitPagePath();
        test.log(Status.PASS, "User visits Register Form Page path");

        // When
        register.scrollToPoint(register.getSubmitElement().getLocation());
        test.log(Status.PASS, "User scrolls to Submit button element");
        register.getSubmitElement().click();

        // Then
        assertTrue(register.isFirstNameRequired());
        test.log(Status.PASS, "First name input is required");
        assertTrue(register.isLastNameRequired());
        test.log(Status.PASS, "last name input is required");
        assertTrue(register.isUsernameRequired());
        test.log(Status.PASS, "username input is required");
        assertTrue(register.isPasswordRequired());
        test.log(Status.PASS, "Password input is required");

    }

    @Test
    @Parameters({"username","password"})
    void validateSuccesfullRegistration(String username, String password, ITestContext testContext) throws Exception {
        ExtentTest test = Hooks.extentTests.get();
        test.assignCategory(testContext.getName());

        final String FIRSTNAME = "Name";
        final String LASTNAME = "LastName";
        // Given
        register.visitPagePath();
        test.log(Status.PASS, "User visits Register Form Page path");
        register.scrollToPoint(register.getFirstName().getLocation());
        test.log(Status.PASS, "User scrolls to First name input element");

        // When
        register.getFirstName().sendKeys(FIRSTNAME);
        test.log(Status.PASS, "User input first name");
        register.getLastName().sendKeys(LASTNAME);
        test.log(Status.PASS, "User input last name");
        register.getUserName().sendKeys(username);
        test.log(Status.PASS, "User input username");
        register.getPassword().sendKeys(password);
        test.log(Status.PASS, "User input passrod");
        register.completeCaptcha();
        try {
            register.getSubmitElement().click();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        test.log(Status.PASS, "User completes captcha");
        register.getSubmitElement().click();
        test.log(Status.PASS, "User click submit button");

        // Then
        assertNotNull(waitForAlertToBeDisplayed(Duration.ofSeconds(5)));
        test.log(Status.PASS, "Alert message is displayed");

    }

    @Test(dataProvider = "badFormatPassword",dependsOnMethods = {"validateSuccesfullRegistration"})
    void validatePasswordBadFormatIsRejected(String password, ITestContext testContext) throws IOException{
        ExtentTest test = Hooks.extentTests.get();
        test.assignCategory(testContext.getName());
        
        final String USERNAME = "NONEXISTUSERNAME";
        final String FIRSTNAME = "Name";
        final String LASTNAME = "LastName";
        // Given
        register.visitPagePath();
        test.log(Status.PASS, "User visits Register Form Page path");
        register.scrollToPoint(register.getFirstName().getLocation());
        test.log(Status.PASS, "User scrolls to First name input element");

        // When
        register.getFirstName().sendKeys(FIRSTNAME);
        test.log(Status.PASS, "User input first name");
        register.getLastName().sendKeys(LASTNAME);
        test.log(Status.PASS, "User input last name");
        register.getUserName().sendKeys(USERNAME);
        test.log(Status.PASS, "User input lastname");
        register.getPassword().sendKeys(password);
        test.log(Status.PASS, "User input password");
        register.completeCaptcha();
        try {
            register.getSubmitElement().click();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        test.log(Status.PASS, "User completes captcha");
        register.getSubmitElement().click();
        test.log(Status.PASS, "User clicks submit button");

        // Then
        assertTrue(register.waitErrorBannerToBeDisplayed().getText().contains("Password"));
        test.log(Status.PASS, "Error banner for bad password is Displayed");


    }

    @DataProvider(name = "badFormatPassword")
    Object[][] badFormatPasswordProvider(){
        return new Object[][]{
            {"password"},
            {"Password"},
            {"Password356"}
        };
    }
}
