package com.ipte.webapp.base;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.util.Base64;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {
    protected String url;
    protected Properties props;

    private By closePopUp = By.cssSelector("#dismiss-button");
    private By googleAD = By.id("adplus-anchor");

    public BasePage() {
        this.props = new Properties();

        try {
            props.load(this.getClass().getResourceAsStream("/data.properties"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        this.url = props.getProperty("url", "");
    }

    public static WebDriver getDriver() {
        return WebDriverInstance.getDriver();
    }

    public String getURL() {
        return this.props.getProperty("url");
    }

    public void visitRoot() {
        getDriver().get(url);
    }

    protected void visitPage(String path) {
        getDriver().get(url + path);
    }

    public static String takeScreenShootAsBase64() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
    }
    
    public static byte[] takeScreenShootAsBytes() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }
    
    public static File takeScreenShootAsFile() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
    }

    public static boolean waitForElementVisible(WebElement element, int millis) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofMillis(millis));
        wait.until(ExpectedConditions.visibilityOf(element));
        return true;
    }

    public void closePopUpElement() {
        try {
            waitForElementVisible(
                    getDriver().findElement(closePopUp),
                    1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeGoogleAdElement() {
        try {
            waitForElementVisible(getDriver().findElement(googleAD), 5);
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].remove()", getDriver().findElement(googleAD));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void scrollToPoint(Point point) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(arguments[0],arguments[1])", point.x, point.y);
    }

    public Alert waitForAlertToBeDisplayed(Duration duration) {
        return new WebDriverWait(getDriver(), duration)
                .until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert(){
        getDriver().switchTo().alert().accept();
    }

    public WebElement findElement(By locator){
        return getDriver().findElement(locator);
    }

    public List<WebElement> findElements(By locator){
        return getDriver().findElements(locator);
    }
}
