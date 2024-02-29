Feature: Identify New Bikes in ZigWheels

  @Smoke
  Scenario: Honda Selection 
    Given Selecting the browser we want
    And Navigate to ZigWheels site 
    When Selection of all the brands shown in ZigWheels
    And All the brands sent to Excel Sheet
    Then Quitting the driver