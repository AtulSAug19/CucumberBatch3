
Feature: Verify user is able to register.

Background: 
Given user is on registration page

@Registration @TC001 @Smoke
Scenario: Verify when user enters all details then user should be registered successfully
When user enter fullname "AtulShrivastava"
And user enter address "1233ASDASFf" 
And user enter other email id "abc@gmail.com"
And user enter City/Town "Pune"
And user enter Company "ABC"
And user select "Male"
When user enter username "atul123"
And user enter password "213123"
And user enter confirm password "213123"
And click on submit button
And select agree checkbox
Then "Success" is displayed

@Login @TC002 @Sanity
Scenario Outline: Verify user should be able to login with multiple credential
When user enter "<username>" in username
And user enter "<password>" in password
And click on login button
Then "Success" is displayed

Examples:
 | username | password |
 |qwerrtg | 1234455433|
 |defsfsgsgs| ewfefswfrg12e412414|