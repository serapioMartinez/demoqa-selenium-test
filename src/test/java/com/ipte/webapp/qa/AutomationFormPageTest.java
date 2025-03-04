// package com.ipte.webapp.qa;

// import static org.junit.Assert.assertFalse;
// import static org.junit.Assert.assertTrue;

// import java.io.IOException;

// import org.testng.ITestContext;
// import org.testng.annotations.Listeners;
// import org.testng.annotations.Test;

// import com.aventstack.extentreports.ExtentTest;
// import com.aventstack.extentreports.Status;
// import com.ipte.webapp.base.Hooks;
// import com.ipte.webapp.base.TestListener;
// import com.ipte.webapp.pageobject.AutomationPracticeFormPage;

// @Listeners(TestListener.class)
// public class AutomationFormPageTest extends Hooks {

//     AutomationPracticeFormPage form = new AutomationPracticeFormPage();

//     @Test
//     void validateFormIsSendWhenRequiredFieldsAreSet(ITestContext testContext) throws Exception {
//         ExtentTest test = Hooks.extentTests.get();
//         test.assignCategory(testContext.getName());
        
//         String firstName = "Name";
//         String lastName = "LastName";
//         String phoneNumber = "1234567890";

//         form.visitPagePath();
//         test.log(Status.PASS, "User visits Automation Form Page path");

//         form.scrollToPoint(form.getFirstNameInput().getLocation());
//         test.log(Status.PASS, "User scrolls to First Name Input field");

//         form.getFirstNameInput().sendKeys(firstName);
//         test.log(Status.PASS, "User introduces first name value: "+firstName);

//         form.getLastNameInput().sendKeys(lastName);
//         test.log(Status.PASS, "User introduces last name value: "+lastName);

//         form.getPhoneNumberInput().sendKeys(phoneNumber);
//         test.log(Status.PASS, "User introduces phone number value: "+phoneNumber);

//         form.getGenderRadio(1).click();
//         test.log(Status.PASS, "User select radio button: "+1);

//         form.getSubmitButton().click();
//         test.log(Status.PASS, "User click submit button");

//         // Then
//         assertTrue(waitForElementVisible(form.getDialogElement(), 200));
//         test.log(Status.PASS, "User introduces firstname value: "+firstName);
//         test.pass("Test Completed succesfully");

//     }

//     @Test
//     void validateFormIsNotSentWhenRequiredFieldsAreEmpty(ITestContext testContext) throws IOException{
//         ExtentTest test = Hooks.extentTests.get();
//         test.assignCategory(testContext.getName());

//         form.visitPagePath();
//         test.log(Status.PASS, "User visits Automation Form Page path");

//         form.scrollToPoint(form.getSubmitButton().getLocation());
//         test.log(Status.PASS, "User scrolls to Submit button");

//         form.getSubmitButton().click();
//         test.log(Status.PASS, "User clicks Submit button");

        
//         assertTrue(form.isFirstNameFieldRequired());
//         test.log(Status.PASS, "First name input is required");
//         assertTrue(form.isLastNameFieldRequired());
//         test.log(Status.PASS, "Last name input is required");
//         assertTrue(form.isPhoneNumberFieldRequired());
//         test.log(Status.PASS, "Phone number input is required");
//         assertTrue(form.isradioGroupRequired());
//         test.log(Status.PASS, "Radio button input is required");
//     }
    

// }
