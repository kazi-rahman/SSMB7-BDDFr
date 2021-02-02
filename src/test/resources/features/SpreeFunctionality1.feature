#https://cucumber.io/docs/gherkin/reference/

Feature: Spree login functionality - 1
  This explain how verious login functionality will work
  #1 . Only valid user with valid password can login
  #2 . No password is a valid entry

  @Smoke
  @Functional
  Scenario: 1.Valid user with valid password
    Given not a validated user
    When user browse to the "http://spree.shiftedtech.com"
    Then home page display
    When user click "HomePage.Login" link
    Then login page display
    When user enter "shiftqa01@gmail.com" at "LoginPage.Email" textbox
    And user enter "shiftedtech" at "LoginPage.Password" textbox
    And user click "LoginPage.Login" button
    Then home page display
    And login success message display in "HomePage.SuccessMsg" as "Logged in successfully"
    Then user click "HomePage.Logout" link

  @Debug
  Scenario: 2.Valid user with valid password
    Given not a validated user
    When user browse to the "http://spree.shiftedtech.com"
    Then home page display
    When user click "HomePage.Login" link
    Then login page display
    When user enter "shiftqa02@gmail.com" at "LoginPage.Email" textbox
    And user enter "shiftedtech" at "LoginPage.Password" textbox
    And user click "LoginPage.Login" button
    Then home page display
    And login success message display in "HomePage.SuccessMsg" as "Logged in successfully xxx"
    Then user click "HomePage.Logout" link


  @Debug
  Scenario: 3.InValid user with valid password
    Given not a validated user
    When user browse to the "http://spree.shiftedtech.com"
    Then home page display
    When user click "HomePage.Login" link
    Then login page display
    When user enter "shiftqa01@gmail.com" at "LoginPage.Email" textbox
    And user enter "shiftedtechXXX" at "LoginPage.Password" textbox
    And user click "LoginPage.Login" button
    Then login page display
    And login error message display in "LoginPage.ErrorMsg" as "Invalid email or password."

  Scenario Outline: 4.Valid user with valid password
    Given not a validated user
    When user browse to the "http://spree.shiftedtech.com"
    Then home page display
    When user click "HomePage.Login" link
    Then login page display
    When user enter "<Email>" at "LoginPage.Email" textbox
    And user enter "<Password>" at "LoginPage.Password" textbox
    And user click "LoginPage.Login" button
    Then home page display
    And login success message display in "HomePage.SuccessMsg" as "<Message>"
    Then user click "HomePage.Logout" link
    Examples:
    |Email  |Password |Message  |
    |shiftqa01@gmail.com|shiftedtech|Logged in successfully|
    |shiftqa02@gmail.com|shiftedtech|Logged in successfully|