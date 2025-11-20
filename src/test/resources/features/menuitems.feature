
Feature: Verify all menu items

Background:
	Given fixture is loaded
	When user navigate to login page
	When enter username in username field
	When enter password in password field
	When Click on Login button
	Then Dashboard page should be displayed

@TC002
Scenario: When user click on menu items the all submenu items should be displayed

Given user navigate to Admin page
Then all below menu items gets displayed
| User Management |
| Job |
| Organization |
| Qualifications |
| Nationalities |
|Corporate Branding  |
| Configuration |
When user click on User Management
Then Users should be displayed
And user click on Job
Then all below sub menu items gets displayed
| Job Titles |
| Pay Grades |
| Employment Status |
| Job Categories |
| Work Shifts |