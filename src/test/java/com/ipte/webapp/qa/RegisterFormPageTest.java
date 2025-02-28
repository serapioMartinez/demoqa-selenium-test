package com.ipte.webapp.qa;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ipte.webapp.base.Hooks;
import com.ipte.webapp.base.TestListener;
import com.ipte.webapp.pageobject.RegisterFormPage;

@Listeners(TestListener.class)
public class RegisterFormPageTest extends Hooks {

    RegisterFormPage register = new RegisterFormPage();

    @Test
    void validateFormIsNotSentWhenInpuFieldIsEmpty() throws IOException {
        // Given
        register.visitPagePath();

        // When
        register.scrollToPoint(register.getSubmitElement().getLocation());
        register.getSubmitElement().click();

        // Then
        assertTrue(register.isFirstNameRequired());
        assertTrue(register.isLastNameRequired());
        assertTrue(register.isUsernameRequired());
        assertTrue(register.isPasswordRequired());

    }

    @Test
    @Parameters({"username","password"})
    void validateSuccesfullRegistration(String username, String password) throws Exception {
        final String FIRSTNAME = "Name";
        final String LASTNAME = "LastName";
        // String username = "username";
        // String password = "Acceptable@Pass23";
        // Given
        register.visitPagePath();
        register.scrollToPoint(register.getFirstName().getLocation());

        // When
        register.getFirstName().sendKeys(FIRSTNAME);
        register.getLastName().sendKeys(LASTNAME);
        register.getUserName().sendKeys(username);
        register.getPassword().sendKeys(password);
        register.completeCaptcha();
        try {
            register.getSubmitElement().click();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        register.getSubmitElement().click();

        // Then
        assertNotNull(waitForAlertToBeDisplayed(Duration.ofSeconds(5)));

    }

    @Test(dataProvider = "badFormatPassword",dependsOnMethods = {"validateSuccesfullRegistration"})
    void validatePasswordBadFormatIsRejected(String password) throws IOException{
        final String USERNAME = "NONEXISTUSERNAME";
        final String FIRSTNAME = "Name";
        final String LASTNAME = "LastName";
        // Given
        register.visitPagePath();
        register.scrollToPoint(register.getFirstName().getLocation());

        // When
        register.getFirstName().sendKeys(FIRSTNAME);
        register.getLastName().sendKeys(LASTNAME);
        register.getUserName().sendKeys(USERNAME);
        register.getPassword().sendKeys(password);
        register.completeCaptcha();
        try {
            register.getSubmitElement().click();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        register.getSubmitElement().click();

        // Then
        assertTrue(register.waitErrorBannerToBeDisplayed().getText().contains("Password"));


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
