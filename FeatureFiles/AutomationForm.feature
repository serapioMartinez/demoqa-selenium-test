Feature: Complete Automation Form Practive.

    @Automation
    Scenario: Form is sent when required fields are set
        Given User visits automation practice form page
        When User scrolls to "firstname" element
        And User input firstname value "luis"
        And User input lastname value "martinez"
        And User input phone value "6549824587"
        And User select radio button "MALE"
        And User clicks submit button
        Then Dialog is diaplayed to user

    @Automation
    Scenario: Form is not sent when required fields are empty
        Given User visits automation practice form page
        When User scrolls to "submit" element
        And User clicks submit button
        Then Mandatory fields are required