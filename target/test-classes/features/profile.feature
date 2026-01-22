@requiresLogin
Feature: Profile Creation and Management

@profileCreation
Scenario Outline: Create profile with valid details Name "<name>", Email "<email>", Dob "<dob>"
  When user enters name "<name>"
  And user enters email "<email>"
  And user selects date of birth "<dob>"
  And user selects gender radio button
  And user clicks on Save and Continue button
  Then profile should be created successfully
  
Examples:
  | name   | email                  | dob        |
  | Anmol  | anmolsingh@test.com    | 12/01/1999 |
  | Pratik | pratiksingh@test.com   | 12/02/1998 |

