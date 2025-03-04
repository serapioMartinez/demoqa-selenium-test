package com.ipte.webapp.stepdefinition;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.Duration;

import javax.management.RuntimeErrorException;

import org.openqa.selenium.WebElement;

import com.ipte.webapp.pageobject.RegisterFormPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class RegisterFormSteps {

    private RegisterFormPage register = new RegisterFormPage();

    @Given("User visits register form page")
    public void userVisitsRegisterPage() {
        register.visitPagePath();
    }

    @And("User scrolls to register {string} element")
    public void userScrollsToElement(String element) {
        switch (element) {
            case "firstname":
                register.scrollToPoint(register.getFirstName().getLocation());
                break;
            case "lastname":
                register.scrollToPoint(register.getLastName().getLocation());
                break;
            case "username":
                register.scrollToPoint(register.getUserName().getLocation());
                break;
            case "password":
                register.scrollToPoint(register.getPassword().getLocation());
                break;
            case "submit":
                register.scrollToPoint(register.getSubmitElement().getLocation());
                break;

            default:
                throw new RuntimeException("User tried to scroll to an unexisting element");
        }
    }

    @And("User input firstname register value {string}")
    public void userInputFirstName(String firstname){
        register.getFirstName().sendKeys(firstname);
    }

    @And("User input lastname register value {string}")
    public void userInputLastName(String lastname){
        register.getLastName().sendKeys(lastname);
    }
    
    @And("User input username register value {string}")
    public void userInputUserName(String username){
        register.getUserName().sendKeys(username);
    }
    
    @And("User input password register value {string}")
    public void userInputPassword(String password){
        register.getPassword().sendKeys(password);
    }

    @And("User completes register captcha")
    public void userCompletesCaptcha(){
        register.completeCaptcha();
    }
    
    @And("User clicks submit register button")
    public void userClicksSubmitButton(){
        register.getSubmitElement().click();
    }

    @And("User accept alert message")
    public void userAcceptAlertMessage(){
        register.acceptAlert();
    }

    @And("User clicks back to login button")
    public void userClicksBacktoLogin(){
        register.getBackToLoginButtonElement().click();
    }

    @Then("User is located at register user path")
    public void userLocatedAtRegisterPath(){
        assertTrue(register.getDriver().getCurrentUrl().contains(RegisterFormPage.PATH));
    }

    @Then("Successfull registration message is displayed")
    public void alertMessageIsDisplayed(){
        assertNotNull(register.waitForAlertToBeDisplayed(Duration.ofSeconds(1)));
    }

    @Then("Mandatory registration fields are required")
    public void mandatoryFieldsAreRequired(){
        assertTrue(register.isFirstNameRequired());
        assertTrue(register.isLastNameRequired());
        assertTrue(register.isUsernameRequired());
        assertTrue(register.isPasswordRequired());
    }

    @Then("Password invalid format message is displayed")
    public void passwordInvalidFormatMessage(){
        WebElement error = register.waitErrorBannerToBeDisplayed();

        assertNotNull(error);
        assertTrue(error.getText().contains("Password"));
    }
}
