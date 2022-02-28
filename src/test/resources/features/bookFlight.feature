Feature: Book a ticket

  Background: User is on the kiwi.com homepage
  Scenario: User should be able to book a ticket in kiwi.com
    Given User is on the kiwi.com homepage
    When user selects flight from "BUD" to "BER" airport
    And selects departure "2022-03-08" and return "2022-04-06" date
    And select passengers: 2 adults, 2 children, 1 infant
    And add 0 cabin bag and 0 checked in bag
    Then user clicks on Search button
    And user selects non-stop flight option
    And user select first flight on the page
    And user chooses to continue as a guest
    And scroll down to continue button and click to submit
    Then take a screenshot
