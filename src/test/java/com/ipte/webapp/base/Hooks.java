package com.ipte.webapp.base;

import java.io.IOException;
import java.time.LocalDateTime;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Hooks extends BasePage {

    public static ExtentReports report = new ExtentReports();
    public static ThreadLocal<ExtentTest> extentTests = new ThreadLocal<>();
    public static ExtentSparkReporter spark = new ExtentSparkReporter("target/TestReportSpark.html");

    public Hooks() {
        super();
    }

    @BeforeTest
    public void setUp(ITestContext testContext) throws IOException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        getDriver();
        report.attachReporter(spark);
    }

    @BeforeMethod
    public void setUpMethod(ITestResult testResult) {
        System.out.println("Starting new Test: " + testResult.getMethod().getMethodName() + " at "
                + LocalDateTime.now().toString());
        if(extentTests.get()==null){
            extentTests.set(report.createTest(testResult.getClass().getSimpleName()+"."+testResult.getMethod().getMethodName()));
        }
    }

    @AfterMethod
    public void tearDownMethod(ITestResult testResult) {
        WebDriverInstance.cleanUpDriver();
        extentTests.remove();
    }

    @AfterTest
    public void tearDown(){
        report.flush();
    }

}
