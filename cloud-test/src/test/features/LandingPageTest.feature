#Feature: Feature file to test web page functions

@assignment
Feature: Landing page verifications

  @web
  Scenario: Web page element verification Web view
    Given user direct to the web page as "WEB" user
    When able to see the log in the page
    Then able to verify menu item <menuItem>
      | solutions    |
      | integrations |
      | resources    |
      | pricing      |
      | company      |

  @web
  Scenario: Web page search verification
    Given user direct to the web page as "WEB" user
    When user clicks on search button
    Then user able to search text as "Azure"
    And user clicks on search
    Then take screenshot of page
    Then user validate the results count

  @mobile
  Scenario: Web page element verification mobile view
    Given user direct to the web page as "MOBILE" user
    When able to click on menu button
    Then able to verify menu item <menuItem>
      | solutions    |
      | integrations |
      | resources    |
      | pricing      |
      | company      |

  @mobile
  Scenario: Web page search verification
    Given user direct to the web page as "MOBILE" user
    When user clicks on search button
    Then user able to search text as "Azure"
    And user clicks on search
    Then take screenshot of page
    Then user validate the results count