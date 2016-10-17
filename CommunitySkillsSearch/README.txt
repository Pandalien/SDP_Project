COMMUNITY SKILLS SEARCH

0. INTRODUCTION
====================
This web application is a group project for COMP602 Software Development Practice.
It allows the users to sign up with their suburb and competent skills, and they 
can search for some works in the local community which require those skills. 
Every user could be the advertiser who offers the job, as well as the worker who 
looks for the job. After a job is done, they are able to rate and give feedback to 
each other. The feedback will be displayed on the job page, while the rate will be 
accumulated to the user account.

1. AUTHORS
====================
Andrew Drawneek
Andy Chen
Daniel Parker
15877724 Matt Chung

2. RUN THE PROGRAM
====================
- Run the program on localhost
  Required tools:
  1. Netbeans 8.1 or above
  2. Glassfish 4.1.1
  3. MySQL Workbench 6.3 CE
Get MySQL connected to localhos. Import the database by opening the SQL files in 
the order: db_model > db_insert_suburbs > db_insert_classifications_skills >
db_insert_users.
Open up Netbeans IDE and load the "CommunitySkillsSearch" project file. Right-click 
the project folder, select Properties. Go to Run, select the Server as Glassfish 
Server that you installed. Press Run Project.

- Run the program online

3. MAJOR BOTTLENECK
====================