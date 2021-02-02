#https://cucumber.io/docs/gherkin/reference/

Feature: Spree Cart functionality - 1
  This explain how verious cart functionality will work
  #1 . Only valid user with valid password can login
  #2 . No password is a valid entry

  Background:
    Given not a validated user
    When user browse to the "http://spree.shiftedtech.com"
    Then home page display
    When user navigate to login page
    Then login page display
    When user login with user "shiftqa01@gmail.com" and password "shiftedtech"
    Then home page display
    And navigate to the shopping cart page
    And clear the cart
    And  navigate to home page

  @Debug
  Scenario: 1.Add two product to shopping cart
    When user user select a product name "Ruby on Rails Tote"
    And  add the product to shopping cart
    And  navigate to home page
    And  user user select a product name "Ruby on Rails Bag"
    And  add the product to shopping cart
    And  navigate to home page
    And  navigate to the shopping cart page
    Then 2 product should be visible in the cart

  Scenario: 2.Add two product to shopping cart
    When user select following products
    |Ruby on Rails Tote|
    |Ruby on Rails Bag |
    And  navigate to home page
    And  navigate to the shopping cart page
    Then 2 product should be visible in the cart


  Scenario: 3.Add two product to shopping cart
    When user select following products
      |Ruby on Rails Tote|
      |Ruby on Rails Bag |
      |Ruby on Rails Baseball Jersey|
    And  navigate to home page
    And  navigate to the shopping cart page
    Then 3 product should be visible in the cart

  Scenario: 4.Add products to shopping cart
    When user select following products
      |Ruby on Rails Tote|
      |Ruby on Rails Bag |
      |Ruby on Rails Baseball Jersey|
    And  navigate to home page
    And  navigate to the shopping cart page
    Then following product should be visible in the cart
      |Ruby on Rails Tote           |$15.99|1|$15.99|
      |Ruby on Rails Bag            |$22.99|1|$22.99|
      |Ruby on Rails Baseball Jersey|$19.99|1|$19.99|

  Scenario: 4.Add products to shopping cart
    When user select following products
      |Ruby on Rails Tote|
      |Ruby on Rails Bag |
      |Ruby on Rails Baseball Jersey|
    And  navigate to home page
    And  navigate to the shopping cart page
    Then following product should be visible in the cart page
      |Name                         |Price |Quantity|Total  |
      |Ruby on Rails Tote           |$15.99|1       |$15.99 |
      |Ruby on Rails Bag            |$22.99|1       |$22.99 |
      |Ruby on Rails Baseball Jersey|$19.99|1       |$19.99 |
