package com.ipte.webapp.pageobject;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.ipte.webapp.base.BasePage;

public class AutomationPracticeFormPage extends BasePage {

    public final String PATH = "/automation-practice-form";

    public AutomationPracticeFormPage() {
        super();
    }

    public void visitPagePath() throws IOException {
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

    public WebElement getDialogElement() throws IOException {
        return getDriver().findElement(dialog);
    }

    public WebElement getFirstNameInput() throws IOException {
        return getDriver().findElement(firstNameInput);
    }

    public WebElement getLastNameInput() throws IOException {
        return getDriver().findElement(lastNameInput);
    }

    public WebElement getGenderRadio(int radio) throws IOException {
        switch (radio) {
            case 1:
                return getDriver().findElement(maleRadio);
            case 2:
                return getDriver().findElement(femaleRadio);
            case 3:
                return getDriver().findElement(otherRadio);
        }
        return getDriver().findElement(maleRadio);
    }

    public WebElement getPhoneNumberInput() throws IOException {
        return getDriver().findElement(phoneNumberInput);
    }

    public WebElement getSubmitButton() throws IOException {
        return getDriver().findElement(submitButton);
    }

    public WebElement getForm() throws IOException {
        return getDriver().findElement(form);
    }

    public boolean isFormFieldRequired(By req) throws IOException{
        try {
            getDriver().findElement(req);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isFirstNameFieldRequired() throws IOException {
        return isFormFieldRequired(firstNameInputInvalid);
    }

    public boolean isPhoneNumberFieldRequired() throws IOException {
        return isFormFieldRequired(phoneNumberInputInvalid);
    }

    public boolean isLastNameFieldRequired() throws IOException {
        return isFormFieldRequired(lastNameInputInvalid);
    }

    public boolean isradioGroupRequired() throws IOException {
        return isFormFieldRequired(radioGroupInvalid);
    }

}
