# java-jdbc-start
Quick starting demo about connecting Java application with a database using JDBC 

## Folder Structure (VS Code Contents):
The workspace contains two folders by default, where:
- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies


# Before Starting:
Before starting with JDBC, the JDK, and Java Environmental variable must be installed and setup. For more information you can check my repo ["Java Essentials"](https://github.com/anmarjarjees/java-essentials), you can check I will be using VS Code for this repo. 


# JDBC
Java Database Connectivity (JDBC) is a Java standard that provides the interface for connecting from Java to relational databases. It's the API (Application Programming Interface) for establishing a connection between our Java project (application) and the database. 

JDBC uses drivers to connect to the database, so there is a specific driver for each type of databases
It's used to run the CRUD operation against different types of DBMS.

JDBC is not the only way to connect to databases. Java also supports ORM (object Relational Mapping) which is implemented by JPA Jakarta Persistence API (JPA; formerly Java Persistence API). The Java Persistence API provides Java developers with an object/relational mapping facility for managing relational data in Java applications. Java Persistence consists of four areas:


# Java and Database (SQL) Datatype options:
You can use the JDBC with MySQL (Workbench), PostgreSQL, MySQL (XAMPP), SQLite.
Below is the datatype list for the most commonly used in SQL and Java:
| SQL Language | Java Language |
| -------- | ------- |
| VARCHAR | String |
| INTEGER | int |
| DOUBLE | double |
| REAL | float |
| FLOAT | float |
| BIT | boolean |
| DATE | Date |


# Database Tools Preparation:
Whether you have the MySQL community and Workbench or MySQL XAMPP installed, you need to download the **MySQL Java Connector" as we do with Python when we download the MySQL Python Connector.

1. Go to [*"MySQL connector"**](https://dev.mysql.com/downloads/connector/j/). You can search for "Java mysql connector" to get into this official page from oracle, or any other trusted third-party website like "Maven" then just download the ".JAR" file which stands for ["Java ARchive"](https://docs.oracle.com/javase/8/docs/technotes/guides/jar/jarGuide.html).

2- For just downloading the simple and independent file, you can select "Platform Independent":

![MySQL Java Connector](/repo-img/mysql-connector.png)

3- Unzip/extract the compressed downloaded folder. The only file that we need to attach to our project is **"mysql-connector-j-8.0.33.jar"**. Notice that the version number might change depending on the available version when you download it. 

### NOTE:
By default this file will be inside the "Download" folder", but you can (should) copy it into the folder "lib" of your project for better and fast accessing.


# Database Creation:
For example, run XAMPP to launch the phpMyAdmin or MySQL Workbench to create your database, and one table at least.

- Create a database named "jdbc_test":
```sql
CREATE DATABASE jdbc_test
```

- Create a table named "authors":
```sql
CREATE TABLE authors (
	author_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, -- Integer value with auto-increment 
    first_name VARCHAR(40) NOT NULL,
    last_name VARCHAR(40) NOT NULL,
    email VARCHAR(70) UNIQUE,    
    phone VARCHAR(20), -- should use text value (no calculation and can have 0 at the left)
    city VARCHAR(30),  
    province VARCHAR(30) DEFAULT "ON",
    join_date DATETIME
    -- Adding the constraint for the primary key at the bottom (another way):
    -- PRIMARY KEY(author_id) 
);
```

- Insert Data into the table "authors":
```sql
INSERT INTO authors 
(first_name, last_name, email, phone, city, join_date) 
values 
('Sam', 'Simpson', 'samsimpson@storyclub.ca', '4161231234','Toronto',now()),
('Martin', 'Smith', 'alexchow@storyclub.ca', '4161231235','Scarborough',now()),
('Sara', 'Grays', 'saragrayson@ggmmaaiill.ca', '4161231236','Mississauga',now()),
('Kate', 'Wilson', 'katewilson@storyclub.ca', '4161231237','Brampton',now()),
('Susan', 'Clark', 'susanclarck@storyclub.ca', '4161231238','Toronto',now());
```


# Starting Your Project:
- Open Visual Studio Code, and follow the normal steps for creating a new JAVA project (refer to my full PDF file for more details and explanations).
- From the VS Code Explorer:
    - click **"Java Projects"** Tab => Select your project "java-jdbc-start"
        - click the "+" plus sign of **"Referenced Libraries"**
- Navigate to the folder (directory) where you downloaded the MySQL connecter/J. **(NOTE To Recap: By default this file will be inside the "Download" folder", but you can copy it into the folder "lib" of your project for better and fast accessing)**
    - select the JAR file "mysql-connector-j-x.x.x.jar"
- The connection file will be added to your current project library
![vscode-explorer](/repo-img/vscode-explorer.png)


# Code Files:
1- The first file is "Driver.java" is just for testing the driver of "MySQL" if it's working or not
    - Refer to the code and the comment in the file "Driver.java"
2- The second file "App.java" is to connect to the database and run a simple query
    - Refer to the code and the comment in the file "App.java"

NOTE: The code in the first file can simply included in the second file to check the driver first, then connect and query the database.


# References, Resources, and Credits:
- [Java How to Program By Paul J. Deitel - Deitel & Associates, Inc](https://deitel.com/about/)
- [JDBC Basic Oracle](https://docs.oracle.com/javase/tutorial/jdbc/basics/index.html)
- [Java Platform, Standard Edition (Java SE) 8](https://docs.oracle.com/javase/8/)
- [MySQL Community Downloads - Connector/J](https://dev.mysql.com/downloads/connector/j/)
