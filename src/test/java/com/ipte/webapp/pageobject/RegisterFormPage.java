package com.ipte.webapp.pageobject;

import java.time.Duration;

import org.openqa.selenium.By;
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
    private By backToLoginButton = By.cssSelector("#gotologin");

    private By errorBanner = By.cssSelector("#name");

    private By captcha = By.cssSelector("#recaptcha-anchor");
    private By capchaFrame = By
            .xpath("//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.google.com/recaptcha')]");

    public RegisterFormPage() {
        super();
    }

    public void visitPagePath() {
        visitPage(PATH);
    }

    public WebElement getFirstName() {
        return getDriver().findElement(firstName);
    }

    public WebElement getLastName() {
        return getDriver().findElement(lastName);
    }

    public WebElement getUserName() {
        return getDriver().findElement(userName);
    }

    public WebElement getPassword() {
        return getDriver().findElement(password);
    }

    public WebElement getSubmitElement() {
        return getDriver().findElement(submit);
    }

    public WebElement getErrorElement() {
        return getDriver().findElement(errorBanner);
    }

    public WebElement getBackToLoginButtonElement(){
        return getDriver().findElement(backToLoginButton);
    }

    private boolean isFieldRequired(By req) {
        try {
            getDriver().findElement(req);
            return true;
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean isFirstNameRequired() {
        return isFieldRequired(firstNameInvalid);
    }

    public boolean isLastNameRequired() {
        return isFieldRequired(lastNameInvalid);
    }

    public boolean isUsernameRequired() {
        return isFieldRequired(userNameInvalid);
    }

    public boolean isPasswordRequired() {
        return isFieldRequired(passwordInvalid);
    }

    public void completeCaptcha() {
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
        captchaElement.click();
        boolean captchaSolved = new WebDriverWait(getDriver(), Duration.ofMillis(1500))
                .until(ExpectedConditions.attributeToBe(captchaElement, "aria-checked", "true"));

        getDriver().switchTo().defaultContent();
        if (!captchaSolved) {
            throw new RuntimeException("Captcha couldn't be completed by the test");
        }

    }

    public WebElement waitErrorBannerToBeDisplayed() {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(errorBanner));

    }
}
