package com.ipte.webapp.pageobject;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ipte.webapp.base.BasePage;

public class LoginFormPage extends BasePage{

    public static final String PATH = "/login";

    public LoginFormPage(){
        super();
    }

    public void visitPagePath() throws IOException{
        visitPage(PATH);
    }

    private By username = By.cssSelector("#userName");
    private By password = By.cssSelector("#password");
    private By loginButton = By.cssSelector("#login");
    private By errorBanner = By.cssSelector("#name"); //Invalid username or password!

    public WebElement getUsernameElement() throws IOException{
        return getDriver().findElement(username);
    }
    
    public WebElement getPasswordElement() throws IOException{
        return getDriver().findElement(password);
    }

    public WebElement getLoginButtonElement() throws IOException{
        return getDriver().findElement(loginButton);
    }

    public WebElement getErrorBannerElement() throws IOException{
        return getDriver().findElement(errorBanner);
    }

    public WebElement waitErrorBannerToBeDisplayed() throws IOException{
        return new WebDriverWait(getDriver(), Duration.ofSeconds(5))
            .until(ExpectedConditions.presenceOfElementLocated(errorBanner));
        
    }

}
