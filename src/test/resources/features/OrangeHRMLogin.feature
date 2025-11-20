
Feature: Verify login functionality for OrangeHRM application

Background:
Given fixture is loaded

@TC001
Scenario: Verify if admin can login
When user navigate to login page
Then user enter credentials and login