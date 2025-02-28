package com.ipte.webapp.qa;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ipte.webapp.base.Hooks;
import com.ipte.webapp.base.TestListener;
import com.ipte.webapp.pageobject.AutomationPracticeFormPage;

@Listeners(TestListener.class)
public class AutomationFormPageTest extends Hooks {

    AutomationPracticeFormPage form = new AutomationPracticeFormPage();

    @Test
    void validateFormIsSendWhenRequiredFieldsAreSet() throws Exception {
        String firstName = "Name";
        String lastName = "LastName";
        String phoneNumber = "1234567890";

        form.visitPagePath();
        form.scrollToPoint(form.getFirstNameInput().getLocation());

        form.getFirstNameInput().sendKeys(firstName);
        form.getLastNameInput().sendKeys(lastName);
        form.getPhoneNumberInput().sendKeys(phoneNumber);
        form.getGenderRadio(1).click();

        form.getSubmitButton().click();

        // Then
        assertTrue(waitForElementVisible(form.getDialogElement(), 200));

    }

    @Test
    void validateFormIsNotSentWhenRequiredFieldsAreEmpty() throws IOException{
        form.visitPagePath();
        form.scrollToPoint(form.getSubmitButton().getLocation());

        form.getSubmitButton().click();

        
        assertTrue(form.isFirstNameFieldRequired());
        assertTrue(form.isLastNameFieldRequired());
        assertTrue(form.isPhoneNumberFieldRequired());
        assertTrue(form.isradioGroupRequired());
    }
    
    void validateFirstNameFieldIsRequired() throws Exception {
        form.visitPagePath();
        form.scrollToPoint(form.getSubmitButton().getLocation());
        form.getSubmitButton().click();
        assertTrue(form.isFirstNameFieldRequired());
    }

    void validateFirstNameFieldNotRequiredWhenValueSet() throws Exception {
        form.visitPagePath();
        form.getFirstNameInput().sendKeys("My name");
        form.scrollToPoint(form.getSubmitButton().getLocation());
        form.getSubmitButton().click();
        assertFalse(form.isFirstNameFieldRequired());
    }

    void validateLastNameFieldIsRequired() throws Exception {
        form.visitPagePath();
        form.scrollToPoint(form.getSubmitButton().getLocation());
        form.getSubmitButton().click();
        assertTrue(form.isLastNameFieldRequired());
    }

    void validateLastNameFieldIsNotRequiredWhenValueSet() throws Exception {
        form.visitPagePath();
        form.getLastNameInput().sendKeys("Mylastname");
        form.scrollToPoint(form.getSubmitButton().getLocation());
        form.getSubmitButton().click();
        assertFalse(form.isLastNameFieldRequired());
    }

    void validatePhoneNumberFieldIsRequired() throws Exception {
        form.visitPagePath();
        form.scrollToPoint(form.getSubmitButton().getLocation());
        form.getSubmitButton().click();
        assertTrue(form.isPhoneNumberFieldRequired());
    }

    void validatePhoneNumberFieldIsNotRequiredWhenValueIsSet() throws Exception {
        form.visitPagePath();
        form.getPhoneNumberInput().sendKeys("6589124578");
        form.scrollToPoint(form.getSubmitButton().getLocation());
        form.getSubmitButton().click();
        assertFalse(form.isPhoneNumberFieldRequired());
    }

    void validateRadioGroupIsRequired() throws Exception {
        form.visitPagePath();
        form.scrollToPoint(form.getSubmitButton().getLocation());
        form.getSubmitButton().click();
        assertTrue(form.isradioGroupRequired());
    }

    void validateRadioGroupIsNotRequiredWhenRadioIsSelected() throws Exception {
        form.visitPagePath();
        form.scrollToPoint(form.getSubmitButton().getLocation());
        form.getGenderRadio(2).click();
        form.getSubmitButton().click();
        assertFalse(form.isradioGroupRequired());
    }

}
