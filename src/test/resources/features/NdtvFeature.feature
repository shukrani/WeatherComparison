Feature: Verify all the scenarios related with login
Given I am on home page
When Click on login button
Then login form gets open

  @weatherSanity
  Scenario Outline: pin city weather page
     When I select weather section
     Then weather section gets open
     When I  pin <city_name> city
     Then selected <city_name> display on the map
     And I can verify and store detailed weather information for the <city_name>
  
    Examples: 
      | city_name | 
      | "Pune"    |
      
  @weatherSanity
  Scenario Outline: Get the Weather details for the city
 		When a user retrieves the weather information by <city_name>
    Then the status code is 200
    And response return weather object
   Examples: 
      | city_name | 
      | "Pune"    |
      
  @weatherSanity
   Scenario Outline: Weather data compare
     When user comparing weather data received from UI and API on the basis of <temp_variance> and <humidity_variance> for the <city_name>
     Then user is able to see the correct results
    Examples: 
      | temp_variance | humidity_variance | city_name|
      | 2             | 10                | pune     |
   
  
    
      
   