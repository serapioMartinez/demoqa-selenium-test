Feature: Register form tests
    Background:
        Given User visits register form page
        Then User is located at register user path
        
    @Empty @Registration
    Scenario: User submit registration form with mandatory fields empty
        When User scrolls to register "submit" element
        Then Mandatory registration fields are required

    @NotEmpty @Registration @BadFormat
    Scenario Outline: User tried registering bad format password
        When User scrolls to register "firstname" element
        And User input firstname register value "luis"
        And User input lastname register value "martinez"
        And User input username register value "username_00001"
        And User input password register value "<password>"
        And User completes register captcha
        And User clicks submit register button
        Then Password invalid format message is displayed
        Examples:
        | password |
        | password |
        | Password |
        | P@ssword |
        | Passw0rd |
        | 9@sswd |
    
    @NotEmpty @Registration
    Scenario: User succesfull registration
        When User scrolls to register "firstname" element
        And User input firstname register value "luis"
        And User input lastname register value "martinez"
        And User input username register value "username_00"
        And User input password register value "P@assw0rd"
        And User completes register captcha
        And User clicks submit register button
        Then Successfull registration message is displayed

