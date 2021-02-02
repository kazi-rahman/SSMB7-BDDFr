#https://cucumber.io/docs/gherkin/reference/

Feature: Spree login functionality - 3
  This explain how verious login functionality will work
  #1 . Only valid user with valid password can login
  #2 . No password is a valid entry

  Background:
    Given not a validated user
    When user browse to the "http://spree.shiftedtech.com"
    Then home page display
    When user navigate to login page
    Then login page display


  Scenario: 1.Valid user with valid password
    When user login with user "shiftqa01@gmail.com" and password "shiftedtech"
    Then home page display
    And login success message display as "Logged in successfully"


  Scenario: 2.Valid user with valid password
    When user login with user "shiftqa01@gmail.com" and password "invalid"
    Then login page display
    And login error message display as "Invalid email or password."
