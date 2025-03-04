package com.ipte.webapp;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = {"./FeatureFiles/"},
    glue = {"com.ipte.webapp.stepdefinition"},
    plugin = {
       "pretty", "html:target/reports/myreport.html"
    },
    tags = "@Automation or @Login or @TextBox or @Empty"
)
public class CucumberRunner {
    
}
