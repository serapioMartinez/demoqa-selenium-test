package com.ipte.webapp.base;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

import com.ipte.webapp.App;

public class BasePage {
    protected String url;
    protected Properties props;

    private By closePopUp = By.cssSelector("#dismiss-button");
    private By googleAD = By.id("adplus-anchor");

    public BasePage(){
        this.props = new Properties();

        try {
            props.load(App.class.getResourceAsStream("/data.properties"));
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.exit(1);
        }

        this.url = props.getProperty("url", "");
    }

    public static WebDriver getDriver() throws IOException{
        return WebDriverInstance.getDriver();
    }

    public String getURL(){
        return this.props.getProperty("url");
    }

    public void visitRoot() throws IOException{
        getDriver().get(url);
    }

    protected void visitPage(String path) throws IOException{
        getDriver().get(url+path);
    }


    public static String takeScreenShoot() throws Exception{
        return takeScreenShoot("SC_Error_");
    }

    public static String takeScreenShoot(String methodName) throws Exception{
        String screenshotName = methodName+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd A"))+".png";
        String screenshotPath = "target"+File.separator+"surefire-reports"+File.separator+"screenshots"+File.separator;

        File screenshoot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        File temp = new File(screenshotPath);
        if(!temp.exists()) temp.mkdirs();

        temp = new File(screenshotPath+screenshotName);
        Files.copy(screenshoot.toPath(), temp.toPath());
        screenshoot.renameTo(temp);

        return screenshotName;
    }

    public static boolean waitForElementVisible(WebElement element, int millis) throws Exception{
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofMillis(millis));
        wait.until(ExpectedConditions.visibilityOf(element));
        return true;
    }

    public void closePopUpElement() throws IOException{
        try {
            waitForElementVisible(
                getDriver().findElement(closePopUp),
                 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeGoogleAdElement() throws IOException{
        try {
            waitForElementVisible(getDriver().findElement(googleAD), 5);
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].remove()", getDriver().findElement(googleAD));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void scrollToPoint(Point point) throws IOException{
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(arguments[0],arguments[1])", point.x, point.y);
    }

    public boolean waitForAlertToBeDisplayed(Duration duration) throws IOException{
        new WebDriverWait(getDriver(), duration)
                            .until(ExpectedConditions.alertIsPresent());
        return true;
    }

}
