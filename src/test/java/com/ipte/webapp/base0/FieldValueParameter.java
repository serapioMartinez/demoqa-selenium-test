package com.ipte.webapp.base0;

import org.openqa.selenium.By;

public class FieldValueParameter {
    private By locator;
    private String value;

    public FieldValueParameter(By locator, String value) {
        this.locator = locator;
        this.value = value;
    }
    public By getLocator() {
        return locator;
    }
    public void setLocator(By locator) {
        this.locator = locator;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    
}
