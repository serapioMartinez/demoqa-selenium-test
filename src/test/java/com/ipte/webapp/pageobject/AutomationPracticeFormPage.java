package com.ipte.webapp.pageobject;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.ipte.webapp.base.BasePage;

public class AutomationPracticeFormPage extends BasePage {

    public final String PATH = "/automation-practice-form";
    public static final int MALE_RADIO = 1;
    public static final int FEMALE_RADIO = 2;
    public static final int OTHER_RADIO = 3;

    public AutomationPracticeFormPage() {
        super();
    }

    public void visitPagePath() {
        visitPage(PATH);
    }


    private By firstNameInput = By.cssSelector("#firstName");
    private By firstNameInputInvalid = By.cssSelector("#firstName:invalid");
    private By lastNameInput = By.cssSelector("#lastName");
    private By lastNameInputInvalid = By.cssSelector("#lastName:invalid");
    private By maleRadio = By.cssSelector("label[for='gender-radio-1']");
    private By radioGroupInvalid = By.cssSelector("#gender-radio-1:invalid");
    private By femaleRadio = By.cssSelector("label[for='gender-radio-2']");
    private By otherRadio = By.cssSelector("label[for='gender-radio-3']");
    private By phoneNumberInput = By.cssSelector("#userNumber");
    private By phoneNumberInputInvalid = By.cssSelector("#userNumber:invalid");
    private By submitButton = By.cssSelector("#submit");
    private By form = By.cssSelector("#userForm");
    private By dialog = By.cssSelector("body > div.fade.modal.show");

    public WebElement getDialogElement(){
        return getDriver().findElement(dialog);
    }

    public WebElement getFirstNameInput() {
        return getDriver().findElement(firstNameInput);
    }

    public WebElement getLastNameInput() {
        return getDriver().findElement(lastNameInput);
    }

    public WebElement getGenderRadio(int radio) {
        switch (radio) {
            case MALE_RADIO:
                return getDriver().findElement(maleRadio);
            case FEMALE_RADIO:
                return getDriver().findElement(femaleRadio);
            case OTHER_RADIO:
                return getDriver().findElement(otherRadio);
        }
        return getDriver().findElement(maleRadio);
    }

    public WebElement getPhoneNumberInput() {
        return getDriver().findElement(phoneNumberInput);
    }

    public WebElement getSubmitButton() {
        return getDriver().findElement(submitButton);
    }

    public WebElement getForm() {
        return getDriver().findElement(form);
    }

    public boolean isFormFieldRequired(By req) {
        try {
            getDriver().findElement(req);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isFirstNameFieldRequired() {
        return isFormFieldRequired(firstNameInputInvalid);
    }

    public boolean isPhoneNumberFieldRequired() {
        return isFormFieldRequired(phoneNumberInputInvalid);
    }

    public boolean isLastNameFieldRequired() {
        return isFormFieldRequired(lastNameInputInvalid);
    }

    public boolean isradioGroupRequired() {
        return isFormFieldRequired(radioGroupInvalid);
    }

}
