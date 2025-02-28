package com.ipte.webapp.base;

import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestListener extends BasePage implements ITestListener{
    
    @Override
    public void onTestFailure(ITestResult result) {
        // try{
        //     System.out.println("Failure executing "+result.getName()+". Screencapture saved as: "+takeScreenShoot());
        // }catch(Exception e){
        //     e.printStackTrace();
        // }
    }
}
