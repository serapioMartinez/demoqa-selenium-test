Feature: Login tests for invalid credentials
    Background: User visits login page
        Given User visits login page
        Then User is located at login page

    @Login @BadCredentials
    Scenario: User enter invalid credentials then is not logged into the profile page
        When User scrolls to login "username" element
        And User input login credentials
        | username | asdfasdf |
        | password | P@ssword |
        And User clicks login button
        Then Invalid credentials error is displayed

    @Login
    Scenario: User enter SQLi values then is not logged into the profile page
        When User scrolls to login "username" element
        And User input login credentials
        | username | ' or 1=1 -- |
        | password | P@ssword |
        And User clicks login button
        Then Invalid credentials error is displayed
