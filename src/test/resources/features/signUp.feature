Feature: User Registration and Login

  Scenario: Create a new user account
    Given I navigate to the Magento website
    When I click on the "Create an Account" link
    And I fill in the registration form with valid details
    And I submit the form
    Then I should see a confirmation message