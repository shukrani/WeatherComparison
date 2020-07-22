Feature: Verify all the scenarios related with login
Given I am on home page
When Click on login button
Then login form gets open

  @pinCity
  Scenario Outline: pin city weather page
     When I select weather section
     Then weather section gets open
     When I  pin <city_name> city
     Then selected <city_name> display on the map
     And I can check the detailed weather information for the <city_name>
  
    Examples: 
      | city_name | 
      | "Pune"    |
      
  @weatherAPI
  Scenario Outline: Get the Weather details for the city
 		When a user retrieves the weather information by <city_name>
    Then the status code is 200
    And response return weather object
   Examples: 
      | city_name | 
      | "Pune"    |