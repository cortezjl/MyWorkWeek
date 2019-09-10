#User Stories

###Add new users
As an administrator I want to control the ability create an account for new users so only users that have been granted access can use the application.  As an administrator I want to control what type of user each user is to control what functionality the user is able to use.  Employee start and end dates are also needed for each user to control whether the user should be included on the schedule or not.  Employees may leave and return at a later date or they may be hired but not starting right away.

###Sign In
As an established user I want to log in so that I can access the weekly work schedule and time off requests.

###Find Weekly Schedule
As any user I want to be able find an existing work schedule by week date.

###Create Schedule
As a management user I want to be able to create a weekly work schedule that includes the following functionality:
•	Only include employees that are active – have a start date less or equal to the week ending date and have an end date greater than or equal to the week starting date.  A date value of 12/31/9999 will be used for active employees
•	At least 1 shift manager must be scheduled to work at all times that the restaurant is open
•	Limit the work hours that employees under age 16 can work on weekdays during the school year (can work 4-7PM)
•	Limit the total number of hours that employees under age 16 can work during the school
•	Include start and end times for each day of the week.  
•	Able to view any time off requests for the week the schedule is being created for
•	Allow non-time specific values of “open”, “to done” and “close”.   
o	A time value of “open” is used for shift manager employees that come in at a time they determine to ensure their additional responsibilities are completed prior to the restaurant opening.  
o	A time value of “to done” is used when an employee can leave prior to the restaurant closing, after the evening rush is over.  
o	A time value of “close” indicates that the employee is scheduled to stay until after the restaurant has closed and all cleaning and restocking has been completed.

###Edit Schedule
As a management user I want to be able to modify an existing weekly work schedule.

###View Schedule
As any user, I want to be able to view and print a weekly work schedule.

###About
As a user I want to find out more information about this site so that I can make contact the administrator.
###Add Time Off Request
As any user, I want to be able to add a new request for time off for one or more days and for partial days.

###View Time Off Requests
As any user, I want to be able to search for any time off requests I have made.

