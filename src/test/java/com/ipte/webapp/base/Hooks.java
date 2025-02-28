package com.ipte.webapp.base;

import java.io.IOException;
import java.time.LocalDateTime;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class Hooks extends BasePage {

    public Hooks() {
        super();
    }

    @BeforeTest
    public void setUp(ITestContext testContext) throws IOException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        getDriver();
    }

    @BeforeMethod
    public void setUpMethod(ITestResult testResult){
        System.out.println("Starting new Test: "+testResult.getMethod().getMethodName()+" at "+LocalDateTime.now().toString());
    }

    @AfterMethod
    public void tearDownMethod(ITestResult testResult) {
        if(!testResult.isSuccess()){
            try{
                System.out.println("Failure executing "+testResult.getName()+". Screencapture saved as: "+takeScreenShoot(testResult.getMethod().getMethodName()));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        WebDriverInstance.cleanUpDriver();
    }

}
