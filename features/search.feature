Feature: Search course

  Scenario: Successful search
    Given User is on coursera home page in "<browser>"
    When the user enters invalid email "<email>"
    Then navigate to other field and come back
    But it should throw error
    
  Examples:
  |browser|email|
  |chrome |12345|
  |edge   |12345|
