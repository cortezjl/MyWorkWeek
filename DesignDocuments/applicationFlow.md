# Application Flow

### User Sign In
1. User chooses sign in on the menu (available on all pages, unless the user 
is signed in already).  Database will be pre-populated with 1 entry for the admin user, 
allowing the administrator to be able to enter any new users.
1. User enters username and password on form and submits. 
1. If user is authenticated, the server will handle allowing access to view/edit 
pages, depending on the user's role(s).  JDBCRealm used for authentication (users, users_roles, and roles table).
1. If authentication fails, show error message/page.

### Add User
1. Option only available to logged in users with proper role
1. User enters new user details
1. Details are sent to Add/Edit User servlet with Add indicator
1. Servlet creates User and User_Roles object
1. Servlet sends objects to dao
1. Dao adds user and user_roles to the database
1. Servlet sends confirmation to Add/Edit page that User has been added.


### View Users
1. Option only available to logged in users with proper role
1. User enter search type and value (all, all active, user name, first or last name)
1. Page sends a request to display users servlet along with criteria 
1. Servlet uses the user dao to select records according to criteria
1. Dao performs select and creates User data objects from results.
1. Dao returns list of users matching criteria to servlet.
1. Servlet sends list back to view user results jsp.
1. View User Results jsp displays the users.
1. Consider paging results so page does not get super long and too much data 

### Edit User
1. Option only available to logged in users with proper role
1. User updates user details
1. Details are sent to Add/Edit user servlet with update Indicator
1. Servlet sends objects to dao
1. Dao updates the user and user_roles to the database
1. Servlet sends confirmation to Add/Edit user page that the user has been updated.

### Create Weekly Schedule
1. Option only available to logged in users with proper role 
1. User enters the schedules starting week date
1. User clicks on create scheudule button
1. Details are sent to Create Schedule servlet
1. Servlet calculates week ending date
1. Servlet calls workingdays API passing the week start and end dates to check for any holidays that week
1. Servlet creates schedule object
1. Servlet populates day of week, date and holiday, if applicable to schedule object
1. Servlet retrieves list of users and user_roles effective that week to populate scheduele
1. Servlet sends object to Add/Edit Schedule jsp
1. User updates Schedule information 
1. User submits schedule information
1. Details are send to the Add/Edit Schedule Servlet 
1  Servlet sends object to dao
1. Dao adds schedule and schedule details to the database
1. Servlet sends confirmation to schedule page that the schedule has been added.

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


### Edit Weekly Schedule
1. Option only available to logged in users with proper role
1. User updates schedule details
1. Details are sent to Add/Edit Schedule servlet with update Indicator
1. Servlet sends objects to dao
1. Dao updates the schedule and schedule_details to the database
1. Servlet sends confirmation to Add/Edit Schedule page that the Schedule has been updated.


### View Time Off Requests
1. Option only available to logged in users with proper role
1. User enter Date Range and search type (all for for them self)
1. Page sends a request to display time off request servlet along with criteria 
1. Servlet uses the time off request dao to select records according to criteria
1. Dao performs select and creates time off requests data objects from results.
1. Dao returns list of time off requests matching criteria to servlet.
1. Servlet sends list back to view time off requests results jsp.
1. View time off request Results jsp displays the users.
1. Consider paging results so page does not get super long and too much data 

### Add Time Off Request
1. Option only available to logged in users with proper role
1. User enters new time off request details
1. Details are sent to Add/Edit time off request servlet with Add indicator
1. Servlet creates time off request object
1. Servlet sends objects to dao
1. Dao adds time off request to the database
1. Servlet sends confirmation to Add/Edit page that time off request has been added.

### Edit Time Off Request
1. Option only available to logged in users with proper role
1. User updates time off request details, including option to update status as cancelled
1. Details are sent to Add/Edit time off request servlet with update Indicator
1. Servlet sends objects to dao
1. Dao updates the time off request to the database
1. Servlet sends confirmation to Add/Edit time off request page that the request has been updated.
