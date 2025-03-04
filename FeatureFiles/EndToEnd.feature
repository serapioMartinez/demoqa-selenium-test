Feature: End to End feature tests

    @EndToEnd
    Scenario: User creates and delete a new account successfully
        Given User visits register form page
        When User scrolls to register "firstname" element
        And User input username register value "mynewUsername"
        And User input password register value "P@ssw0rd"
        And User input firstname register value "firstname"
        And User input lastname register value "lastname"
        And User completes register captcha
        When User scrolls to register "submit" element
        And User clicks submit register button
        Then Successfull registration message is displayed
        And User accept alert message
        When User clicks back to login button
        Then User is located at login page
        When User scrolls to login "username" element
        And User input login credentials
        | username | mynewUsername |
        | password | P@ssw0rd |
        And User clicks login button
        Then User "mynewUsername" is logged into the profile page
        When User clicks delete account button
        Then Delete account confirmation message is displayed
        When User confirm account deletion
        Then Succesfull deletion message is displayed
        When User accept alert message
        Then User is located at login page
        