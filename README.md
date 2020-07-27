# cbt-app
Practice app for the interview

Application uses MariaDB. It's setup can be found inside of the application-dev.properties file. Please change the database fields to match yours
spring.datasource.url=jdbc:mariadb://localhost:{portNumber}/{databaseName}
spring.datasource.username={dbUsername}
spring.datasource.password={dbPassword}

for initial run please change 
spring.jpa.hibernate.ddl-auto = validate to spring.jpa.hibernate.ddl-auto = create
So the initial data can be created, after that if you switch back to validate, the state of the database will persist as is.

The credentials used to login are:
username: client@foo.com
password: client123

Base url: http://localhost:8080
To checkout the inserted product follow this path :
/products/1
To login:
/login
To logout:
/login/logout
To go to user profile page:
/user/profile

NOTE-----
The popular product logic works on the backend, but I did not have time to populate the placeholder inside of the single product page, due to the last minute issue with jquery, so you will not be able to see the actual popular products but they will be present on the console. Thanks
