@tag
Feature: Purchase the order from Ecommerce website
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce page

  @Regression
  Scenario Outline: Positive test of submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to the cart
    And I checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on the confirmationPage

    Examples: 
      | name  						|  	password  	| 	productName  		|	
      | Geeta@gmail.com 	| 	Pass@1007  	| 	ADIDAS ORIGINAL |
      | Kishor@gmail.com	|		Pass@1007		|		ZARA COAT 3			|

