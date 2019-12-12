#Julie Cortez Individual Project

###Problem Statement 

I am part owner of a restaurant where we have a point of service (POS) system.  The POS system includes a Scheduler to 
be able to create a weekly work schedule for employees that are in the POS system.  It is not easy to use and doesn’t 
allow you to schedule employees for a time period that is not an actual time (for example, 2 PM to Close).  

   
Currently, employees will submit a paper monthly calendar of days/times they need off for the upcoming month..  They can 
also request off for dates beyond the upcoming month.  For example, dates they need off for a vacation several months 
from now.  Those dates are typically written on a different calendar.  

Producing the weekly work schedule is being done manually and the manager writing up the schedule has to look through 
all the paper time off requests and the wall calendar when creating a schedule.  A blank template is printed off with 
the days of the week and the names of the current 
employees on it and split into 4 areas:
1.	Shift Manager
2.	Front of House (FOH), which is for people working the registers, drive-thru and to keep the dining area clean
3.	Back of House (BOH), which is the various kitchen staff
4.  Bussers

I want to create a scheduling system that will allow users to submit time off requests and that managers can create a 
weekly work schedule that includes any current employees.  The time off request information needs to be available when 
creating the schedule for the work week to try an accommodate any days/times that the employees need off.

* Project Technologies/Techniques
* Security/Authentication
* Tomcat's JDBC Realm Authentication
* Admin or Manager role: create/read/update any data (no deletes).  An Administrator or Manager user will need to be 
able to enter the users that can access the system and some info about the employees such as date of birth, dates of 
employment and their role(s) 

* User role: create time off request,  edit data they have entered previously
*Manager role: create, edit or view weekly schedule, view time off requests for any employees
* All: view their time off requests

* Database 
    * MySQL
    * Store user, roles and user_roles data
    * Store all time off requests and  weekly schedules
* ORM Framework
* Hibernate 5
* Dependency Management
    * Maven
* Web Service consumed using Java
    * openweathermap.org 
* CSS
* Bootstrap 4
* Data Validation
    * Bootstrap Validator for front end 
* Logging
    * Configurable logging using Log4J2. In production, only errors will normally be logged, but logging at a debug 
    level can be turned on to facilitate trouble-shooting.
* Hosting
    * AWS
* Independent Research Topic/s
    * JQuery
    * Hibernate Query
* Unit Testing
    * JUnit5 tests to achieve minimum of 80%+ code coverage
* IDE: IntelliJ IDEA