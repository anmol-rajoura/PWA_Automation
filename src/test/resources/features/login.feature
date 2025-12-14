Feature: Login Functionality


  Scenario: User logs in with valid OTP
    Given user is on login page
    When user enters phone number
    And user click on send OTP button
    And user enters valid OTP
    Then user should be logged in successfully