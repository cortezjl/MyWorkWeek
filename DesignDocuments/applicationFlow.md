# Application Flow


### User Sign In
1. User chooses sign in on the menu (available on all pages, unless the user 
is signed in already).  Database will be pre-populated with 1 entry for the admin user, 
allowing the administrator to be able to enter any new users, including for themself 
if they choose to.
1. User enters username and password on form and submits. 
1. If user is authenticated, the server will handle allowing access to view/edit 
pages, depending on the user's role(s).  JDBCRealm used for authentication (users, users_roles, and roles table).
1. If authentication fails, show error message/page.

### Add User
1. Option only available to logged in users with proper role
1. User enters new user details
1. Details are sent to Add User servlet
1. Servlet creates User and User_Roles object
1. Servlet sends objects to dao
1. Dao adds user and user_roles to the database
1. Servlet sends confirmation to Add/Edit page that User has been added.

### View Users
1. Option only available to logged in users with proper role
1. User enter search type and value (all, user name, first or last name)
1. Page sends a request to display users servlet along with criteria 
(all, user name, first name or last name).
1. Servlet uses the user dao to select records according to criteria
1. Dao performs select and creates User data objects from results.
1. Dao returns list of users matching criteria to servlet.
1. Servlet sends list back to view user results jsp.
1. View User Results jsp displays the users.
1. Consider paging results so page does not get super long and too much data 
is sent.

### Edit User
1. Option only available to logged in users with proper role
1. User clicks on edit icon on View User Results jsp for a user that is displayed
1. Details are sent to edit user servlet
1. Servlet retrieves user and user_roles objects
1. Servlet sends object to add/edit user jsp.
1. User modifies the user data
1. User clicks on submit
1. Details are sent to Edit User servlet
1. Servlet sends objects to dao
1. Dao updates the user and user_roles to the database
1. Servlet sends confirmation to Add/Edit user page that the user has been updated.


### Edit User
1. Option only available to logged in users with proper role
1. User clicks on edit icon on View User Results jsp for a user that is displayed
1. Details are sent to delete user servlet
1. Servlet retrieves user and user_roles objects
1. Servlet sends object to add/edit user jsp.
1. User modifies the user data
1. User clicks on submit
1. Details are sent to Edit User servlet
1. Servlet sends objects to dao
1. Dao updates the user and user_roles to the database
1. Servlet sends confirmation to Add/Edit user page that the user has been updated.
### View Trail

1. Page sends a request to view trail servlet along with criteria 
(all, region, name, etc).
1. Servlet uses the trail dao to select trails according to criteria
1. Dao performs select and creates trail objects from results.
1. Dao returns list of trails matching criteria to servlet.
1. Servlet sends list back to trail  jsp.
1. Trail reports jsp displays the trails.
1. Consider paging results so page does not get super long and too much data 
is sent.

### About

1. Static page - html only? 
1. Consider making contact info configurable.


### Create Schedule
1. Option only available to logged in users with proper role 
1. User enters the schedules starting week date
1. Details are sent to Add Schedule servlet
1. Servlet creates schedule  object
1. Servlet sends object to dao
1. Dao adds schedule to the database
1. Servlet sends confirmation to schedule page that the schedule has been added.
Additional functionality:
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

### View Weekly Schedule
1. Option only available to logged in users with proper role 
1. User enters the schedules starting week date
1. Page calculates the ending week date by adding 7 days to starting week date
1. Page sends a request to view Schedule servlet along with criteria 
(starting week date, ending week date).
1. Servlet uses the schedule dao to select records according to criteria
1. Dao performs select and creates schedule report objects from results.
1. Dao returns list of report matching criteria to servlet.
1. Servlet sends list back to view schedule results jsp.
1. View schedule results jsp displays the weekly schedule.








 