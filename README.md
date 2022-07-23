# GiftCertificate

---

GiftCertificate Application is a part of a training project described in [here.](https://github.com/mjc-school/MJC-School/blob/old/stage%20%233/java/module%20%232.%20REST%20API%20Basics/rest_api_basics_task.md)


## 1. Technology stack

***

GiftCertificate uses following technologies:
1. JDK v.1.8.0_311
2. Maven v.3.8.4
3. Spring Framework v.5.3.21
4. Hikari Connection Pool v.4.0.3
5. Smart Tomcat v.8.5.81
6. PostgreSQL JDBC v.42.3.6 and H2 v.2.1.214
7. JUnit v.5.9.0-M1, AssertJ v.3.23.1, Mockito v.4.6.1


## 2. Prerequisites

***

Using GiftCertificate requires:
1. Intellij IDEA v.2022.1
2. Smart Tomcat Plugin
3. PostgreSQL

## 3. Build and run application 
***

### 3.1. Build project
+ from terminal:
  `mvn clean install`
+ from Intellij IDEA:
  `Ctrl + F9`

### 3.2. Run tests
+ from terminal:
  `mvn verify`
+ from IntelliJ IDEA:
  `Ctrl + Shift + F10`

### 3.3. Run application - Run/Debug Configuration 
![Tomcat.png](Tomcat.png)

## 4. TODO List

***

- [x] Develop web service for Gift Certificates system with the following entities (many-to-many) 
  - [x] CreateDate, LastUpdateDate - format ISO 8601 (https://en.wikipedia.org/wiki/ISO_8601). Example: 2018-08-29T06:12:15.156. More discussion here: https://stackoverflow.com/questions/3914404/how-to-get-current-moment-in-iso-8601-format-with-date-hour-and-minute
  - [x] Duration - in days (expiration period)
- [ ] The system should expose REST APIs to perform the following operations:
  - [ ] CRUD operations for GiftCertificate. If new tags are passed during creation/modification – they should be created in the DB. For update operation - update only fields, that pass in request, others should not be updated. Batch insert is out of scope.
  - [x] CRD operations for Tag.
  - [ ] Get certificates with tags (all params are optional and can be used in conjunction):
    - [x] by tag name (ONE tag)
    - [x] search by part of name/description (can be implemented, using DB function call)
    - [ ] sort by date or by name ASC/DESC (extra task: implement ability to apply both sort type at the same time).