package com.ipte.webapp.stepdefinition;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebElement;

import com.ipte.webapp.base.BasePage;
import com.ipte.webapp.base0.FieldValueParameter;

import io.cucumber.java.DataTableType;
import io.cucumber.java.ParameterType;

public class CustomParameterTypes {
    private BasePage page = new BasePage();

    @ParameterType("[a-z]+;.+")
    public By locator(String locator) {
        String locatorSplit[] = locator.split(";");
        switch (locatorSplit[0]) {
            case "id":
                return By.id(locatorSplit[1]);
            case "css":
                return By.cssSelector(locatorSplit[1]);
            case "class":
                return By.className(locatorSplit[1]);
            case "xpath":
                return By.xpath(locatorSplit[1]);
            case "name":
                return By.name(locatorSplit[1]);
            case "linktext":
                return By.linkText(locatorSplit[1]);
            case "tag":
                return By.tagName(locatorSplit[1]);
            default:
                throw new RuntimeException("Not supported locator Type");
        }
    }

    @DataTableType()
    public By selectorEntry(String locator){
        return locator(locator);
    }

    @DataTableType
    public FieldValueParameter fieldValueParameterEntry(List<String> fieldValue){
        if(fieldValue.size() != 2 || !fieldValue.get(0).matches("[a-z]+;.+")) throw new InvalidArgumentException("Data table type must be in order -> | locator | value |");
        By by = locator(fieldValue.get(0));
        return new FieldValueParameter(by, fieldValue.get(1));
    }
}
