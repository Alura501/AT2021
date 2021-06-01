Feature: Ex2

  Background:
    Given I open test site by URL
    When User log in as "Roman" with password "Jdi1234"

  Scenario: Test user logging
    Then User logged in as "ROMAN IOVLEV"

  Scenario: Test home page
    Then Home page is opened

  Scenario: Test Service menu
    When Header service is opened
    Then Header has 9 options
    When Left Section is opened
    Then Left Section has 9 options

  Scenario: Test Different Elements open
    When Different Elements page is clicked
    Then "Different Elements" page is opened

  Scenario: Test Different Elements page contents
    When Different Elements page is clicked
    Then There are 4 checkboxes
    And There are 4 radiobuttons
    And There is a dropdown
    And There are 2 buttons
    And There is the Right Section
    And There is the Left Section

  Scenario: Test checkboxes
    When Different Elements page is clicked
    When Water and Wind checkboxes is clicked
    Then Checkboxes are selected
    And For each checkbox there is an individual log row and value is corresponded to the status of checkbox

    When Water and Wind checkboxes is clicked
    Then Checkboxes are unselected
    And For each checkbox there is an individual log row and value is corresponded to the unselected status of checkbox

  Scenario: Test radiobutton
    When Different Elements page is clicked
    When Selen radiobutton is clicked
    Then There is a log row and value is corresponded to the status of radiobutton

  Scenario: Test box options
    When Different Elements page is clicked
    When Yellow in dropdown is clicked
    Then There is a log row and value is corresponded to the selected value
