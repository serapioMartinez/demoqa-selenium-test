package com.ipte.webapp.stepdefinition;

import static org.junit.Assert.assertTrue;

import com.ipte.webapp.pageobject.AutomationPracticeFormPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AutomationFormSteps {

    AutomationPracticeFormPage automationForm = new AutomationPracticeFormPage();

    @Given("User visits automation practice form page")
    public void userVisitsAutomationFormPage() {
        automationForm.visitPagePath();
    }

    @And("User scrolls to {string} element")
    public void userScrollsToElement(String element) {
        switch (element) {
            case "firstname":
                automationForm.scrollToPoint(automationForm.getFirstNameInput().getLocation());
                break;
            case "lastname":
                automationForm.scrollToPoint(automationForm.getLastNameInput().getLocation());
                break;
            case "phone":
                automationForm.scrollToPoint(automationForm.getPhoneNumberInput().getLocation());
                break;
            case "gender":
                automationForm.scrollToPoint(
                        automationForm.getGenderRadio(AutomationPracticeFormPage.MALE_RADIO).getLocation());
                break;
            case "submit":
                automationForm.scrollToPoint(automationForm.getSubmitButton().getLocation());
                break;

            default:
                throw new RuntimeException("User tried scrolling into an unexisting element");
        }
    }

    @And("User input firstname value {string}")
    public void userInputFirstName(String firstName) {
        automationForm.getFirstNameInput().sendKeys(firstName);
    }

    @And("User input lastname value {string}")
    public void userInputLastName(String lastName) {
        automationForm.getLastNameInput().sendKeys(lastName);
    }

    @And("User input phone value {string}")
    public void userInputPhoneNumber(String phoneNumber) {
        automationForm.getPhoneNumberInput().sendKeys(phoneNumber);
    }

    @And("User select radio button {string}")
    public void userSelectRadioButton(String option) {
        switch (option) {
            case "MALE":
                automationForm.getGenderRadio(AutomationPracticeFormPage.MALE_RADIO).click();
                break;
            case "FEMALE":
                automationForm.getGenderRadio(AutomationPracticeFormPage.FEMALE_RADIO).click();
                break;
            case "OTHER":
                automationForm.getGenderRadio(AutomationPracticeFormPage.OTHER_RADIO).click();
                break;
            default:
                throw new RuntimeException("User tried clicking to an unexisting radio button");
        }
    }

    @And("User clicks submit button")
    public void userClickSubmitButton(){
        automationForm.getSubmitButton().click();
    }

    @Then("Dialog is diaplayed to user")
    public void dialogIsDisplayed(){
        assertTrue(automationForm.waitForElementVisible(automationForm.getDialogElement(), 2));
    }

    @Then("Mandatory fields are required")
    public void mandatoryFieldsAreRequired(){
        assertTrue(automationForm.isFirstNameFieldRequired());
        assertTrue(automationForm.isLastNameFieldRequired());
        assertTrue(automationForm.isPhoneNumberFieldRequired());
        assertTrue(automationForm.isradioGroupRequired());
    }

}
