package com.ipte.webapp.pageobject;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.ipte.webapp.base.BasePage;

public class ProfilePage extends BasePage{
    public static final String PATH = "/profile";
    public ProfilePage(){
        super();
    }

    private By usernameLabel = By.cssSelector("#userName-value");

    public void visitPagePath() throws IOException{
        visitPage(PATH);
    }

    public WebElement getUsernameLabelElement() throws IOException{
        return getDriver().findElement(usernameLabel);
    }

}
