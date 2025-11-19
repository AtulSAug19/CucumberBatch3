
Feature: To verify is admin can add user in the application

Background:
	Given fixture is loaded

@TC002
Scenario: Verify if admin can add ESS user
	Given user navigate to Admin page
	When user click on Add button
	And user select UserRole
	And user select EmployeeName from dropdown
	And user select status
	And user enter username
	And user enter password
	And user enter confirmpassword
	And click on Save button
	Then new user should be displayed in Records Found table
