package com.ipte.webapp.stepdefinition;

import com.google.common.net.MediaType;
import com.ipte.webapp.base.BasePage;
import com.ipte.webapp.base.WebDriverInstance;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;

public class Hooks {

    @BeforeAll
    public static void globalSetUp(){
        WebDriverInstance.getDriver();
    }

    @AfterAll
    public static void globalTearDown(){
        WebDriverInstance.cleanUpDriver();
    }


    @After
    public static void afterScenarioHook(Scenario scenario){
        // if (scenario.isFailed()) {
            scenario.attach(BasePage.takeScreenShootAsBytes(), MediaType.PNG.toString(), scenario.getName());
        // }
        WebDriverInstance.cleanUpDriver();
    }

}
