   Feature: Identify New Bikes in ZigWheels


@Smoke
  Scenario: All Bikes Validation 
    Given Selecting the browser we want
    And Navigate to ZigWheels site 
    When Selecting the all brands viewed in ZigWheels site
    And Search the bikes less than 4 lakhs
    And All the collected datas printed in Excel Sheet
    Then Quitting the driver