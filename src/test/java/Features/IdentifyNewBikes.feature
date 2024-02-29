   Feature: Identify New Bikes in ZigWheels

  @Smoke
  Scenario: Honda Selection 
    Given Selecting the browser we want
    And Navigate to ZigWheels site 
    When Selection of all the brands shown in ZigWheels
    And All the brands sent to Excel Sheet
    Then Quitting the driver
    
   
    
    
  @Smoke
  Scenario: All Bikes Validation 
    Given Selecting the browser we want
    And Navigate to ZigWheels site 
    When Selecting the all brands viewed in ZigWheels site
    And Search the bikes less than 4 lakhs
    And All the collected datas printed in Excel Sheet
    Then Quitting the driver
    
    
  @Smoke
  Scenario: Upcoming Bikes  
    Given Selecting the browser we want
    And Navigate to ZigWheels site 
    When Hover the icon called Upcoming_bikes
    And Selects the manufacturer called Honda
    And All the collected datas printed in Excel Sheet1
    Then Quitting the driver
   
            
  @Smoke
  Scenario: Cars in Chennai 
    Given Selecting the browser we want
    And Navigate to ZigWheels site 
    When Used Cars in Chennai
    Then Quitting the driver
  
       
  @Smoke
  Scenario: Invalid Login Checking Details
    Given Selecting the browser we want
    And Navigate to ZigWheels site 
    When Click the icon called Invalid Login
    Then Quitting the driver    
    
      