## [Online Lesson Platform](https://online-lessons-isgbl.ondigitalocean.app/#/)

<h3>Project Overview:</h3>
This project aims to create a user-friendly and efficient platform for both learning and selling courses. Users can purchase available courses or upload their own for sale. 
Key features include user registration and authentication, user profiles, course and lesson creation, course search, course purchase, and interactive learning materials.

<h3>Key Features:</h3>
1. User Authentication and Profiles:
Users can register on the platform using a nickname, email, and password.
2. Authentication occurs via entering credentials.
User profiles are categorized into Admin, Registered User, and Guest.
3. Course and Lesson Creation
Authenticated users can create new courses and lessons, adding descriptions and course materials.
Multimedia materials, downloadable files, and other educational resources can be included.
If a course is purchased, the user who created it receives a percentage of the course sale.
4. Course Search and Purchase
All users can view available courses.
Authenticated users can purchase courses.

---

<h3>Testing:</h3>
I decided to test the API using the RestAssured library because it can be automated and included in continuous integration (CI) pipelines, allowing for quick and frequent validation of API functionality with every code change. This helps identify and address issues early in the development process, improving overall software quality.
I chose to do UI testing with Selenium along with the Value Object Module approach. Selenium is perfect for automating web browsers, and TestNG provides robust testing capabilities. Additionally, I integrated Lombok because it reduces boilerplate code, and I used a Logger and Screenshots for reports to ensure comprehensive logging of test execution.


<ul>
  <li><a href="https://docs.google.com/document/d/1EMd2ddNjRm1-IBK_kJ-7-L0MKCk5d89pSazEED2Zwpo/edit" target="_blank">Test plan</a></li>
  <li><a href="https://drive.mindmup.com/map/1tVoHbt4kEinCuAX-t31Nyrd_RCJXcowA" target="_blank">Mind map</a></li>
  <li><a href="https://docs.google.com/spreadsheets/d/16isSIZwTCewDa6AofON1k2LK6I9sBLj8KcSNO0Db36g/edit#gid=0" target="_blank">Decision table</a></li>
  <li><a href="https://docs.google.com/spreadsheets/d/1AeaZ7UqhTECI3YJkomPDHCYZVAxc9r1kW0b3JP9bUf0/edit#gid=0" target="_blank">State Transition Diagram</a></li>
  <li><a href="https://docs.google.com/spreadsheets/d/1AeaZ7UqhTECI3YJkomPDHCYZVAxc9r1kW0b3JP9bUf0/edit#gid=0" target="_blank">State Transition Analysis</a></li>
</ul>

I created 145 API tests and 84 UI tests.

<ul>
  <li><a href="https://github.com/babaianv/Online_lessons_platformQA/tree/main/src/test/java/com/learn/RAtests" target="_blank">API tests suites</a></li> 
  <li><a href="https://github.com/babaianv/Online_lessons_platformQA/tree/main/src/test/java/com/learn/UItests" target="_blank">UI tests suites</a></li> 
</ul>

---
<h3>TestLink</h3>

In TestLink, requirements were entered, a test plan was created, and test cases were developed to manage the testing process.
<ul>
  <li><a href="https://docs.google.com/spreadsheets/d/1Iv8FBbw5Wq2YfuaBKhIHxDvuJNV0Ul1Z/edit?usp=drive_web&ouid=104174358876654605216&rtpof=true" target="_blank">General Test Plan Metrics</a></li>
  <li><a href="https://docs.google.com/spreadsheets/d/1RhG0RcqCOEtva9K5xUKhKp3Vb_kdc7Wk/edit#gid=2089969486" target="_blank">Test result matrix</a></li>
  <li><a href="https://drive.google.com/drive/folders/1cU6qs00lWLdul23_okeyLs7D1V1DwrhE" target="_blank">Example of Positive Test Case</a></li> 
  <li><a href="https://drive.google.com/drive/folders/1cU6qs00lWLdul23_okeyLs7D1V1DwrhE" target="_blank">Example of Negative Test Case</a></li> 
</ul>

---

<h3>🐞 Bugs Found:</h3>


- UI Bugs:

| Bugs Priority | Quantity |
|---------------|----------|
| 1 Highest priority | 1 |
| 4 High priority | 4 |
| 2 Medium priority | 2 |
| 4 Low priority | 4 |
| **Total:** | **11** |

- API Bugs:

| Bugs Priority | Quantity |
|---------------|----------|
| 6 Highest priority | 6 |
| 7 High priority | 7 |
| 1 Medium priority | 1 |
| **Total:** | **14** |




<h4>Example of the most critical bugs:</h4>

<li><a href="https://docs.google.com/document/d/1dkZYJfzcpUM4JS2xhz1ewOdtzwRM02fo/edit?usp=drive_web&ouid=104174358876654605216&rtpof=true" target="_blank">BugReport1</a></li>
<li><a href="https://docs.google.com/document/d/1eib11vEczoafVOwQo7lnEwIJE4SsDLHA/edit?usp=drive_web&ouid=104174358876654605216&rtpof=true" target="_blank">BugReport2</a></li>
<li><a href="https://docs.google.com/document/d/1xgkN6N89_5wEzFaKO8XIOpmsOr0tNFM_/edit?usp=drive_web&ouid=104174358876654605216&rtpof=true" target="_blank">BugReport3</a></li>
<li><a href="https://docs.google.com/document/d/1dkZYJfzcpUM4JS2xhz1ewOdtzwRM02fo/edit?usp=drive_web&ouid=104174358876654605216&rtpof=true" target="_blank">BugReport4</a></li>

---

I also used [Jenkins](https://drive.google.com/file/d/1cFxItZW8l65Os2WKmstv1-zLYYRWFnkY/view?usp=sharing) for continuous integration (CI), which helps automate and manage the software development lifecycle.

---

<h3 align="left">Languages and Tools:</h3>
<p align="left"> <a href="https://git-scm.com/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/git-scm/git-scm-icon.svg" alt="git" width="40" height="40"/> </a> <a href="https://www.java.com" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="java" width="40" height="40"/> </a> <a href="https://www.jenkins.io" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/jenkins/jenkins-icon.svg" alt="jenkins" width="40" height="40"/> </a> <a href="https://www.mongodb.com/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/mongodb/mongodb-original-wordmark.svg" alt="mongodb" width="40" height="40"/> </a> <a href="https://www.mysql.com/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/mysql/mysql-original-wordmark.svg" alt="mysql" width="40" height="40"/> </a> <a href="https://www.postgresql.org" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/postgresql/postgresql-original-wordmark.svg" alt="postgresql" width="40" height="40"/> </a> <a href="https://postman.com" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/getpostman/getpostman-icon.svg" alt="postman" width="40" height="40"/> </a> <a href="https://www.selenium.dev" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/detain/svg-logos/780f25886640cef088af994181646db2f6b1a3f8/svg/selenium-logo.svg" alt="selenium" width="40" height="40"/> </a> <a href="https://spring.io/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" alt="spring" width="40" height="40"/> </a> </p>

