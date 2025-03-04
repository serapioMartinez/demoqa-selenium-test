Feature: Smoke tests to verify base page steps correctly performing

    @TextBox
    Scenario Outline: User visits text-box path and fill form with bad formatted email then email field is required
        Given User visits "/text-box" path
        When User scrolls to id;userEmail element
        And User send "<mail>" to css;#userEmail element
        And User clicks to css;button#submit element
        Then Field id;userEmail has invalid value
        Examples:
        | mail |
        | mail |
        | mail@example |
        | mail@example@com |
    
    @TextBox
    Scenario: User visits text-box path and fill form then output is displayed
        Given User visits "/text-box" path
        When User send "luis" to id;userName element
        And User send "mail@mail.com" to id;userEmail element
        And User send "this is my current address" to id;currentAddress element
        And User send "this is my permanent address" to id;permanentAddress element
        And User scrolls to css;button#submit element
        And User clicks to css;button#submit element
        Then Element xpath;//*[@id="output"] is displayed
        And Element id;name is displayed
        And Element id;email is displayed
        And Element id;currentAddress is displayed
        And Element id;permanentAddress is displayed
        And Fields have value
        | css;#output #name | luis |
        | css;#output #email | mail@mail.com |
        | css;#output #currentAddress | this is my current address |
        | css;#output #permanentAddress | this is my permanent address |

