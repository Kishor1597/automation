Feature: User Registration and Login

  Scenario: Create a new user account
    Given I navigate to the Magento website
    When I click on the "Create an Account" link
    And I fill in the registration form with valid details
    And I submit the form
    Then I should see a confirmation message

  Scenario: Sign in with the newly created account
    Given I navigate to the Magento login page
    When I enter valid credentials
    Then I should be logged in successfully

  Scenario: Sign up with an already registered email
    Given I navigate to the Magento website
    When I click on the "Create an Account" link
    And I fill in the registration form with already registered email
    And I submit the form
    Then I should see an account already exists error

  Scenario: Sign in with wrong credentials
    Given I navigate to the Magento website
    When I click on the "Sign In" link
    And I enter invalid login credentials
    Then I should see an invalid login error