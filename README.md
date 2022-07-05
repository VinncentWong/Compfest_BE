# Kantin Kejujuran
Kantin Kejujuran is a task to create a website with several cases from Compfest UI. There are several features that you should make in this website.

## How to run
* Clone this repository or do in your terminal with ```git clone https://github.com/VinncentWong/Compfest_BE.git```
#### Note : Make sure you have installed git
* Make sure you are using Maven 3.x and Java JDK 1.8
* You can build the project and run the test with ```mvn clean package```
* Once you built, you can run the project with :
```
    mvn spring-boot:run
```
* Check the log, it should be like this 
```
    2022-07-04 22:46:38.083  INFO 9424 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
    2022-07-04 22:46:38.103  INFO 9424 --- [  restartedMain] com.demo.CompfestApplication             : Started CompfestApplication in 5.953 seconds (JVM running for 6.619)
```
#### Note : I use MYSQL(XAMMP) for database setup, please download XAMMP first and install it. Then, make a database with name compfest or if you change database name, then please go to ```src/main/resources/application.properties``` and change ```spring.datasource.url``` value with
```spring.datasource.url=jdbc:mysql://localhost:3306/your-database-name```

## Endpoint that are available on this service
## User Entity
```
    1. "/registration" -> User Registration
    2. "/login" -> User Login
    3. "/user/{id}" -> For getting user with current ID -> Require JWT Token
    4. "/addbalance/{id}" -> For adding user balance with current ID -> Require JWT Token
```
## Item Entity 
#### ( All endpoints that related to item entity required JWT Token so User should be successfully login!)
```
    1. "/item/additem" -> Add new item
    2. "/item/deleteitem/id" -> Delete item with current ID
    3. "/item/getitem" -> Get Item by Item Name ( Please input item name in HTTP header)
    with header = search : value
    4. "/item/getitems" -> Get all items that exist in database
```

```Default Port = 8080```
