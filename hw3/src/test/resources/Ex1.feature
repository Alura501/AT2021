Feature: Ex1

  Background:
    Given I open test site by URL
    When User log in as "Roman" with password "Jdi1234"

  Scenario: Test user logging
    Then User logged in as "ROMAN IOVLEV"

  Scenario: Test home page
    Then Home page is opened


  Scenario: Test header section
    Then Header section contains expected items

  Scenario: Test contents of index page
    Then Index page has 4 images
    And Images are displayed
    And There are 4 texts under images
    And They have proper text

  Scenario: Test texts of the main headers
    Then Text equals to expected result

  Scenario: Test central iframe
    Then Central iframe is displayed
    When Focus is switched to the iframe
    Then There is epam-logo
    And Focus is switched to original window back

  Scenario: Test sub header
    Then Sub header text is "JDI GITHUB"
    And Sub header text is a link "https://github.com/epam/JDI"

  Scenario: Test Left Section
    Then There is a Left Section

  Scenario: Test Footer
    Then There is a Footer
