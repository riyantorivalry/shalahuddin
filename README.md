Shalahuddin Maven Archetype Project Template
=============================================

Summary
-------
The project is a source to create Shalahuddin Database based on web application.

Generated project characteristics
-------------------------
* No-xml Spring MVC 4 web application for Servlet 3.0 environment
* Spring Security 4
* CSS3 Template : [Twitter Bootstrap v3.3.6](http://getbootstrap.com/)
* View Resolver : [Thymeleaf](http://www.thymeleaf.org/)
* Javascript: [Jquery 1.12.4](http://jquery.com/)
* Grid/Paging: [Jquery Datatables 1.10.13](http://datatables.net/)
* Spring Security
* Spring Data JPA [Spring Data JPA](http://projects.spring.io/spring-data-jpa/)
* MongoDB (Spring Data Mongo)
* JUnit/Mockito

Prerequisites
-------------

- JDK 8
- Tomcat v8.0
- Maven 3

Tools
------------
- Eclipse IDE
- Browser (Recomendation : Chrome)

Features
--------
* Support various database connection (oracle, mysql, postgresql, sqlserver, derby, hsqldb, h2). Default: postgresql
* Audit trail already implemented
* Support Active Directory authentication
* User Management already implemented
* Access Role

Create maven archetype and install it locally
---------------------------------------------

Open Eclipse IDE :
- Click menu File > Import 
- Select 'General' folder and choose 'Existing Projects into Workspace
- Browse the project folder you just have download
- Then, clik 'Finish'

Navigate to the project, then:
- Righ click > Run As > Maven clean
- Ensure that the project is cleaned up succesfully. Find out in 'Console' 
- Then, Righ click > Run As > Maven install
- Ensure that the project is installed succesfully, generating war file automatically by system. Find out in 'Console' 

```
Run the project
----------------

Navigate to the project directory and then:
- Righ click > Run As > Run on server
- Choose Tomcat v8.0 then 'Finish'

Test in the browser
-------------------

	http://localhost:8080/shalahuddin

*8080 is the default port of Tomcat server. You may have modified it. Check it out in Tomcat properties
	
Signin using this:

    userId : admin
    password: admin


Note: No additional services are required in order to start the application. Mongo DB configuration is in place but it is not used in the code.

Enabling MongoDB repositories
-----------------------------

* Open MongoConfig class and uncomment the following line:

```
// @EnableMongoRepositories(basePackageClasses = Application.class)
```

Now you can add repositories to your project:

```
@Repository
public interface MyRepository extends MongoRepository<MyDocument, String> {

}
```

Reference
---------
1. Rafal Borowiec blog [codeleak.pl](http://blog.codeleak.pl/2016/01/spring-mvc-4-quickstart-maven-archetype.html)
2. Spring Data JPA Tutorial: Auditing [petrikainulainen.net](https://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-auditing-part-one/)
3. Spring Data JPA Tutorial: Introduction to Query Methods [petrikainulainen.net](https://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-introduction-to-query-methods/)
4. Source template project :dbudi
