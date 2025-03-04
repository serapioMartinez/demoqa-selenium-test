package com.ipte.webapp.stepdefinition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ipte.webapp.pageobject.ProfilePage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class ProfileSteps {
    ProfilePage profile = new ProfilePage();

    @Given("User visits profile page")
    public void userVisitProfilePage(){
        profile.visitPagePath();
    }

    @And("User clicks logout button")
    public void userClicksLogoutButton(){
        profile.getLogoutButtonElement().click();
    }

    @And("User clicks delete account button")
    public void userClicksDeleteAccountButton(){
        profile.getdeleteAccountButtonElement().click();
    }
    
    @And("User confirm account deletion")
    public void userConfirmAccountDeletion(){
        profile.getConfirmButtonModalElement().click();
    }


    @Then("User {string} is logged into the profile page")
    public void userLoggedIntoProfilePage(String username) {
        assertEquals(username, profile.getUsernameLabelElement().getText());
        assertTrue(profile.getDriver().getCurrentUrl().contains(ProfilePage.PATH));
    }

    @Then("Delete account confirmation message is displayed")
    public void deleteAccountConfirmationisDisplayed(){
        WebElement confirmation = new WebDriverWait(profile.getDriver(), Duration.ofMillis(1000))
            .until(ExpectedConditions.visibilityOf(profile.getConfirmationModalElement()));
        assertNotNull(confirmation);
        assertEquals(ProfilePage.CONFIRMATION_ACCOUNT_DELETION_MESSAGE, confirmation.getText());
    }

    @Then("Succesfull deletion message is displayed")
    public void successfullDeletionMessageDisplayed(){
        assertNotNull(profile.waitForAlertToBeDisplayed(Duration.ofMillis(1000)));
    }
}
