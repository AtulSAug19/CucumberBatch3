
Feature: Verify user is able to login.
#1. DataTable as List<String>
Scenario: Verify when user enters all details using datatable as List
Given user is on registration page
When user enter credentials
| Atul |
| 12345Atul |
And click on submit button
Then login should be successfull

#2. DataTable as List<List<String>> (2D List)
@TC002
Scenario: Login with multiple rows
Given user is on registration page
When user enters below data and logged in
  | username | password |
  | admin    | pass123  |
  | user1    | test111  |

#3. DataTable as Map<String,String> (Key-Value)
Scenario: Login with multiple rows using Map
Given enter login details
  | username | password |
  | admin    | pass123  |
  | user1    | test111  |
And click on submit button
Then login should be successfull

#4. DataTable as List<Map<String,String>> (Multiple rows)
Scenario: Register multiple users
Given user enters registration details
  | name  | email            | city  |
  | Ram   | ram@gmail.com    | Delhi |
  | Shyam | shyam@gmail.com  | Pune  |
  
  #5. DataTable to POJO
  Scenario: Register user with POJO
Given user enters below details
  | name  | email           | city  |
  | Ram   | ram@gmail.com   | Delhi |
  
