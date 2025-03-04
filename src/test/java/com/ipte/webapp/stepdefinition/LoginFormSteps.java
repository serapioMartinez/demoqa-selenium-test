package com.ipte.webapp.stepdefinition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import com.ipte.webapp.pageobject.LoginFormPage;
import com.ipte.webapp.pageobject.ProfilePage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class LoginFormSteps {

    LoginFormPage login = new LoginFormPage();

    @Given("User visits login page")
    public void userVisitsLoginPage() {
        login.visitPagePath();
    }

    @And("User scrolls to login {string} element")
    public void userScrollsToLoginElement(String element) {
        switch (element) {
            case "username":
                login.scrollToPoint(login.getUsernameElement().getLocation());
                break;
            case "password":
                login.scrollToPoint(login.getPasswordElement().getLocation());
                break;
            case "submit":
                login.scrollToPoint(login.getLoginButtonElement().getLocation());
                break;

            default:
                throw new RuntimeException("User tried scrolling into an unexisting element");
        }
    }

    @And("User input login username value {string}")
    public void userSetUsernameValue(String username) {
        login.getUsernameElement().sendKeys(username);
    }

    @And("User input login password value {string}")
    public void userSetPasswordValue(String password) {
        login.getPasswordElement().sendKeys(password);
    }

    @And("User input login credentials")
    public void userSubmitLoginCredentialds(Map<String, String> data) {
        System.out.println("Data is: "+data);
        String username = data.get("username");
        String password = data.get("password");
        if (username == null || password == null) {
            throw new RuntimeException("username and password values must be sent");
        }
        login.getUsernameElement().sendKeys(username);
        login.getPasswordElement().sendKeys(password);
    }

    @And("User clicks login button")
    public void userClickLoginButton() {
        login.getLoginButtonElement().click();
    }

    @Then("Invalid credentials error is displayed")
    public void invalidCredentialsIsDisplayed() {
        assertTrue(login.waitErrorBannerToBeDisplayed().getText().contains("Invalid"));
    }

    @Then("User is located at login page")
    public void userLocatedAtLoginPage() {
        assertTrue(LoginFormPage.getDriver().getCurrentUrl().contains(LoginFormPage.PATH));
    }
}
