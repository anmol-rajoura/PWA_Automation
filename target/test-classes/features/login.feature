Feature: Login Functionality


  Scenario Outline: User logs in with valid OTP
    Given user is on login page
    When user enters phone number "<phoneNumber>"
    And user click on send OTP button
    And user enters valid OTP
    Then user should be logged in successfully
    
  Examples:
  | phoneNumber |
  | 9650801800  |
  | 9876543200  |