package com.ipte.webapp.base;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class TestListener extends BasePage implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            String screenShoot = takeScreenShootAsBase64();
            System.out.println("Failure executing " + result.getName());

            ExtentTest test = Hooks.extentTests.get();
            test.log(Status.FAIL, result.getThrowable().getMessage());
            test.fail(MediaEntityBuilder.createScreenCaptureFromBase64String(screenShoot).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Skipping test: "+result.getMethod().getMethodName());
        ExtentTest test = Hooks.report.createTest(result.getClass().getSimpleName()+"."+result.getMethod().getMethodName());
        test.skip(result.getThrowable());
    }
}
