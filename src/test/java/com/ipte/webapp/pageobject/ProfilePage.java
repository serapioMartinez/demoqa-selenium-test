package com.ipte.webapp.pageobject;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.ipte.webapp.base.BasePage;

public class ProfilePage extends BasePage{
    public static final String PATH = "/profile";
    public static final String CONFIRMATION_ACCOUNT_DELETION_MESSAGE = "Do you want to delete your account?";
    public static final String SUCCESSFULL_ACCOUNT_DELETION_MESSAGE = "User Deleted."; // An alert is displayed

    public ProfilePage(){
        super();
    }

    private By usernameLabel = By.cssSelector("#userName-value");
    private By logoutButton = By.cssSelector("div#books-wrapper button#submit");
    private By deleteAccountButton = By.cssSelector("div#app div.text-center.button > button#submit");
    private By confirmationModal = By.cssSelector("body > div.fade.modal.show > div > div");
    private By cancelButtonModal = By.cssSelector("#closeSmallModal-cancel");
    private By confirmButtonModal = By.cssSelector("#closeSmallModal-ok");


    public void visitPagePath() {
        visitPage(PATH);
    }

    public WebElement getUsernameLabelElement(){
        return getDriver().findElement(usernameLabel);
    }

    public WebElement getLogoutButtonElement() {
        return getDriver().findElement(logoutButton);
    }
    
    public WebElement getdeleteAccountButtonElement() {
        return getDriver().findElement(deleteAccountButton);
    }

    public WebElement getConfirmationModalElement() {
        return getDriver().findElement(confirmationModal);
    }

    public WebElement getCancelButtonModalElement() {
        return getDriver().findElement(cancelButtonModal);
    }
    
    public WebElement getConfirmButtonModalElement() {
        return getDriver().findElement(confirmButtonModal);
    }

}
