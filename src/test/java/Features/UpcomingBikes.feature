   Feature: Identify New Bikes in ZigWheels

   @Smoke
  Scenario: Upcoming Bikes  
    Given Selecting the browser we want
    And Navigate to ZigWheels site 
    When Hover the icon called Upcoming_bikes
    And Selects the manufacturer called Honda
    And All the collected datas printed in Excel Sheet1
    Then Quitting the driver