# Internet shop

# Table of Contents
* [Short description](#description)
* [Project structure](#structure)
* [For developer](#setup)
* [Authors](#authors)

# <a name="description"></a>Short description
In this project I attempted to implement basics functions of an online store. There are two type of roles in that store: USER and ADMIN. Each of them has the following are access rights:

* for **Admin**
  * get list of users/items;
  * add/remove user or item;

* for **User**
  * get list of all items;
  * add/remove item to bucket;
  * complete order;
  * get your orders history;


For security purposes regardless of role there is obligatory procedure of login/registration to have access to main resources of the web-site. It is enhanced as well by using hashing of passwords with "salt". This way even breached, users data won't be exposed.

---

## <a name="structure">Project structure

- Java 11
- Maven 4.0.0
- javax.servlet 3.1.0
- jstl 1.2
- mysql 8.0.20
- log4j 1.2.17
- SonarCloud Scan v1.2
- maven-checkstyle-plugin

## <a name="setup">Setup Guide

Open the project in your IDE.

Add it as maven project.

Configure [Tomcat](https://habr.com/ru/post/274587/ "Example"):

add sdk 11.0.3;

Add sdk 11.0.3 in project struсture.

Create a schema "internet_shop" in any SQL database.

Use file jv-internetshop.src.main.resources.init_db.sql to create all the tables required by this app.

At jv-internetshop.util.ConnectionUtil class use username, password and name of DB to create a Connection.

Change a path in jv-internetshop.src.main.resources.log4j.properties. It has to reach your logFile.

Create your first user using registration form.

There is no option to create user with ADMIN access rights, so you have to do that manually in the database.

A main page http://localhost:8080/

___

### <a name="authors"></a>Authors
[Tsyhankov Serhii](https://github.com/fruts)