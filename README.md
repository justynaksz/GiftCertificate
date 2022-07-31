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
![Tomcat.png](Tomcat.PNG)

## 4. TODO List

***

### 4.1. Business requirements

- [x] Develop web service for Gift Certificates system with the following entities (many-to-many) 
  - [x] CreateDate, LastUpdateDate - format ISO 8601 (https://en.wikipedia.org/wiki/ISO_8601). Example: 2018-08-29T06:12:15.156. More discussion here: https://stackoverflow.com/questions/3914404/how-to-get-current-moment-in-iso-8601-format-with-date-hour-and-minute
  - [x] Duration - in days (expiration period)
- [x] The system should expose REST APIs to perform the following operations:
  - [x] CRUD operations for GiftCertificate. If new tags are passed during creation/modification – they should be created in the DB. For update operation - update only fields, that pass in request, others should not be updated. Batch insert is out of scope.
  - [x] CRD operations for Tag.
  - [x] Get certificates with tags (all params are optional and can be used in conjunction):
    - [x] by tag name (ONE tag)
    - [x] search by part of name/description (can be implemented, using DB function call)
    - [x] sort by date or by name ASC/DESC (extra task: implement ability to apply both sort type at the same time).

### 4.2. Application requirements

- [x] JDK version: 8 – use Streams, java.time.*, etc. where it is possible. (the JDK version can be increased in agreement with the mentor/group coordinator/run coordinator)
- [x] Application packages root: com.epam.esm
- [x] Any widely-used connection pool could be used.
- [x] JDBC / Spring JDBC Template should be used for data access.
- [x] Use transactions where it’s necessary.
- [x] Java Code Convention is mandatory (exception: margin size – 120 chars).
- [x] Build tool: Maven/Gradle, latest version. Multi-module project.
- [x] Web server: Apache Tomcat/Jetty.
- [x] Application container: Spring IoC. Spring Framework, the latest version.
- [x] Database: PostgreSQL/MySQL, latest version.
- [x] Testing: JUnit 5.+, Mockito.
- [x] Service layer should be covered with unit tests not less than 80%.
- [x] Repository layer should be tested using integration tests with an in-memory embedded database (all operations with certificates).

### 4.3. General requirements
- [x] Code should be clean and should not contain any “developer-purpose” constructions.
- [x] App should be designed and written with respect to OOD and SOLID principles.
- [x] Code should contain valuable comments where appropriate.
- [x] Public APIs should be documented (Javadoc).
- [x] Clear layered structure should be used with responsibilities of each application layer defined.
- [x] JSON should be used as a format of client-server communication messages.
- [x] Convenient error/exception handling mechanism should be implemented: all errors should be meaningful and localized on backend side.
- [x] Abstraction should be used everywhere to avoid code duplication.
- [x] Several configurations should be implemented (at least two - dev and prod).
