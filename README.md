# JDBC Demo: Spotify Database

## Tables of Contents
- [Overview](#overview)
- [Setup](#setup)
- [Usage](#usage)
- [Tables Created](#tables-created)

## Overview

This project is a Java application that connects to a MySQL database using JDBC (Java Database Connectivity). It demonstrates various database operations, such as creating tables, inserting data, updating records, and executing queries and also advanced ones like transaction management.

## Setup

### Prerequisites
- **Java Development Kit (JDK)**: Ensure you have JDK installed on your machine.
- **MySQL Server**: Ensure MySQL Server is installed and running.
- **JDBC Driver**: Download the MySQL JDBC driver (Connector/J) and add it to your project's classpath.

### Database Setup
- Create a MySQL database named `spotify`:
    ```sql
    CREATE DATABASE spotify;
    ```
- Create the required tables and insert initial data. SQL scripts are provided in the `sql` directory:
    - `create.sql`: Creates the necessary tables.
    - `alter.sql`: Alters tables as needed.
    - `insert.sql`: Inserts initial data.
    - `update.sql`: Updates existing data.

### Configuration
Update the database connection details in the `JdbcDemo` class:
```java
// Database credentials
static final String USER = "<your_username>"; // your username
static final String PASSWORD = "<your_passord>"; // your password
```

## Usage
- Compile and run the java code:
  ``` sh
  javac JdbcDemo.java
  java JdbcDemo
  ```
## Tables Created
-  Users
-  Artists
-  Songs
-  Playlists
-  Playlsits-Songs (to handle the M:N relation between playlist and songs)
