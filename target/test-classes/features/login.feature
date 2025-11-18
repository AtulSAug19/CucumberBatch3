
Feature: Login to application

 Scenario: Login using data from Excel
Given user opens login page
When user logs in with username "Atul" and password "123"
Then login should be validated for "TC001"
