package com.ipte.webapp.stepdefinition;

import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.ipte.webapp.base.BasePage;
import com.ipte.webapp.base0.FieldValueParameter;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BasepageSteps extends BasePage{

    @Given("User visits {string} path")
    public void userVisitsPath(String path) {
        if (!path.startsWith("/"))
            throw new IllegalArgumentException("Path to visit should start with \"/\" character");
        this.visitPage(path);
    }

    @When("User clicks to {locator} element")
    public void userClicksElementByLocator(By locator){
        this.getDriver().findElement(locator).click();
    }

    @When("User send {string} to {locator} element")
    public void userSendValueToElementByLocator(String value, By locator){
        this.getDriver().findElement(locator).sendKeys(value);
    }

    @When("User scrolls to {locator} element")
    public void userScrollToElementByLocator(By locator){
        this.scrollToPoint(this.findElement(locator).getLocation());
    }

    @Then("Element {locator} is displayed")
    public void elementIsDisplayedByLocator(By locator){
        assertTrue(this.waitForElementVisible(findElement(locator), 500));
    }

    @Then("Field {locator} has invalid value")
    public void fieldInvalidValueByLocator(By locator){
        String script = "try{return (arguments[0].matches || arguments[0].msMatchesSelector).call(arguments[0],':invalid')}catch(error){return false}"; // See https://developer.mozilla.org/en-US/docs/Web/API/Element/matches
        WebElement element = findElement(locator);
        // String value = element.getCssValue("border-color");
        // System.out.println("border-color: " +value);

        boolean field_error_class = element.getDomAttribute("class").contains("field-error");
        boolean invalid_pseudoclass = (Boolean) ((JavascriptExecutor) getDriver()).executeScript(script, element);
        assertTrue(field_error_class || invalid_pseudoclass);
    }

    @Then("Fields have invalid value")
    public void fieldsInvalidValueByLocator(List<By> locators){
        locators.forEach(locator -> System.out.println(locator));
        locators.forEach(locator -> fieldInvalidValueByLocator(locator));
    }

    @Then("Field {locator} have the value {string}")
    public void fieldHaveValue(By locator, String value){
        assertTrue(findElement(locator).getText().equalsIgnoreCase(value));
    }
    
    @Then("Fields have value")
    public void fieldsHaveValue(List<FieldValueParameter> fieldValueList){
        fieldValueList.forEach(fieldValue -> {
            System.out.println("Testing: | "+fieldValue.getLocator()+" | "+fieldValue.getValue()+" |");
            
            String elementText = findElement(fieldValue.getLocator()).getText();
            assertTrue(
                "Element "+fieldValue.getLocator()+" has \n\tText: "+ elementText +" \n\tExpected was: "+fieldValue.getValue(),
                elementText.contains(fieldValue.getValue()));
        });
    }
}
