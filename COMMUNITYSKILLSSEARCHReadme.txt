COMMUNITY SKILLS SEARCH

0. INTRODUCTION
====================
This web application is a group project for COMP602/COMP705 Software Development Practice. It allows the users to sign up with their suburb and competent skills, and they 
can search for some works in the local community which require those skills. 
Every user could be an advertiser who offers the job, as well as a worker who 
looks for the job. After a job is done, they are able to rate and give feedback to 
each other. The feedback will be displayed on the job page, while the rating will be 
accumulated to the user account.

1. AUTHORS
====================
16929319 Andrew Drawneek
16900339 Andy Chen
13836312 Daniel Parker
15877724 Matt Chung

2. RUN THE PROGRAM
====================
System requirements:
Mac OS, Windows, Linux.
Any modern browser such as Firefox, Safari or Chrome.


(A) Run the program on localhost
Required tools:
1. Java JDK 1.8
2. Netbeans 8.1 or above with Java EE web support
3. Glassfish 4.1.1 (bundled with Netbeans)
4. MySQL - we used XAMPP 5.6 for easy installation & configuration of MySQL on Windows and MacOS
5. MySQL Workbench 6.3 CE


- Start MySQL server
1. Start XAMPP 5.6.
2. Click Manage Servers tab.
3. Select MySQL Database in the list and click Start.

- Create MySQL database 
(MySQL server must have been started as in the previous step before executing these steps):
Import the database and create the initial data by opening and running the SQL script files in MySQL Workbench in the following order from the “SDP_Project/db” folder:
1. db_model
2. db_insert_suburbs
3. db_insert_classifications_skills
4. db_insert_users 

- Deploy to localhost
Open up Netbeans IDE and load the "CommunitySkillsSearch" project file. Right-click 
the project folder, select Properties. Go to Run, select the Server as Glassfish 
Server that you installed. Press Run Project.

(B) Run the program online
Database and Web app are hosted on Amazon, the website is published which can be visited from anywhere.


Required tools:
   1. Web browser (Google Chrome is highly recommended)


- Access to the link: http://custom-env.x6ba9yj3ve.us-west-2.elasticbeanstalk.com/home

3. HOW TO USE
=============
From the home page, anyone may search for jobs or workers using the search field at the top of the screen. To post a job advertisement or list oneself as an available worker one must create their own user account with the system and log in via the “Log in” button at top-right of screen. 


On successful log-in to the system, new buttons appear in the menu & button bar at the top of the page. The top-right of the screen contains controls for the user to edit their profile and account settings, access their internal message box, and log-out. The top-left of the screen contains controls for creating and managing job advertisements, and a search box to search for workers or jobs on the system. Click “Go!” to access the search page where further search options by skill and location are provided.


The Edit Profile option provides a switch called “Visible” which if switched on (the default) will display the user in search results by others for “Workers”. If the user does not wish to advertise themselves as available for hire, they should change this setting to off.


A new advertisement for a job may be created by the “+” icon in the top-left of the menu & button bar. If the user creates a job advert then the user is acting as an employer and the advert will appear in search results for “Jobs”. 


Workers searching for job adverts on the system may click on the “View Job” button for more information. They can apply for a job by clicking the “Apply” button on a job’s advert page. The job application will then appear in the advertiser’s “Received Applications” list from where the advertiser may “Assign” the job to the applicant. If the advertiser assigns the job to the applicant, the applicant will see “Accept” and “Reject” buttons appear next to the job on its advertisement page or in their “My Applications” list. 


If the applicant clicks “Accept”, the status is changed to “Accepted” and the advertiser now has the option to “Mark as done” when the job is completed. Clicking “Mark as done” will show a rating & feedback page, where the employer can optionally rate the worker and leave feedback. Once this has been done, the worker is given the same opportunity to rate their employer.



4. KNOWN ISSUES
===============
Maximum concurrent user number has not been tested.
Only one skill can be searched for at a time using the Search function.