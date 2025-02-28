package com.ipte.webapp.pageobject;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;

import com.ipte.webapp.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterFormPage extends BasePage {
    public static final String PATH = "/register";
    private By firstName = By.cssSelector("#firstname");
    private By firstNameInvalid = By.cssSelector("#firstname:invalid");
    private By lastName = By.cssSelector("#lastname");
    private By lastNameInvalid = By.cssSelector("#lastname:invalid");
    private By userName = By.cssSelector("#userName");
    private By userNameInvalid = By.cssSelector("#userName:invalid");
    private By password = By.cssSelector("#password");
    private By passwordInvalid = By.cssSelector("#password:invalid");
    private By submit = By.cssSelector("#register");

    private By errorBanner = By.cssSelector("#name");

    private By captcha = By.cssSelector("#recaptcha-anchor");
    private By capchaFrame = By
            .xpath("//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.google.com/recaptcha')]");

    public RegisterFormPage() {
        super();
    }

    public void visitPagePath() throws IOException {
        visitPage(PATH);
    }

    public WebElement getFirstName() throws IOException {
        return getDriver().findElement(firstName);
    }

    public WebElement getLastName() throws IOException {
        return getDriver().findElement(lastName);
    }

    public WebElement getUserName() throws IOException {
        return getDriver().findElement(userName);
    }

    public WebElement getPassword() throws IOException {
        return getDriver().findElement(password);
    }

    public WebElement getSubmitElement() throws IOException {
        return getDriver().findElement(submit);
    }

    public WebElement getErrorElement() throws IOException{
        return getDriver().findElement(errorBanner);
    }

    public boolean isFieldRequired(By req) throws IOException{
        try{
            getDriver().findElement(req);
            return true;
        }catch(NoSuchElementException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean isFirstNameRequired() throws IOException {
        return isFieldRequired(firstNameInvalid);
    }

    public boolean isLastNameRequired() throws IOException {
        return isFieldRequired(lastNameInvalid);
    }

    public boolean isUsernameRequired() throws IOException {
        return isFieldRequired(userNameInvalid);
    }

    public boolean isPasswordRequired() throws IOException {
        return isFieldRequired(passwordInvalid);
    }

    public void completeCaptcha() throws IOException {
        // try {
            new WebDriverWait(getDriver(), Duration.ofMillis(1000))
                    .until(ExpectedConditions
                            .frameToBeAvailableAndSwitchToIt(capchaFrame));

            WebElement captchaElement = new WebDriverWait(getDriver(), Duration.ofMillis(1000))
                    .until(ExpectedConditions
                            .elementToBeClickable(captcha));
            Actions actions = new Actions(getDriver());
            actions.moveToLocation(captchaElement.getLocation().getX() + 10, captchaElement.getLocation().getY() - 10);
            actions.moveToLocation(captchaElement.getLocation().getX() + 5, captchaElement.getLocation().getY() - 10);
            actions.moveToElement(captchaElement);
            // Thread.sleep(1000);
            captchaElement.click();
            // Thread.sleep(1000);
            getDriver().switchTo().defaultContent();
        // } catch (InterruptedException e) {
        // }

    }

    public WebElement waitErrorBannerToBeDisplayed() throws IOException{
        return new WebDriverWait(getDriver(), Duration.ofSeconds(5))
            .until(ExpectedConditions.presenceOfElementLocated(errorBanner));
        
    }
}
