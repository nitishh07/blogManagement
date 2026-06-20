# Blog Management System

A simple Blog Management System built using Spring Boot and MySQL. The application allows users to create, read, update, and delete blog posts and comments while maintaining relationships between users, posts, and comments.

## Features

* User Management

  * Create User
  * View All Users
  * View User by ID
  * Update User
  * Delete User

* Blog Post Management

  * Create Post
  * View All Posts
  * View Post by ID
  * Update Post
  * Delete Post

* Comment Management

  * Create Comment
  * View All Comments
  * View Comment by ID
  * Delete Comment

## Tech Stack

* Java 21
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL
* Maven
* Lombok

## Database Design

### User

| Field    | Type    |
| -------- | ------- |
| id       | Integer |
| name     | String  |
| email    | String  |
| password | String  |

### Post

| Field     | Type          |
| --------- | ------------- |
| id        | Integer       |
| title     | String        |
| content   | String        |
| createdAt | LocalDateTime |
| updatedAt | LocalDateTime |

### Comment

| Field     | Type          |
| --------- | ------------- |
| id        | Integer       |
| content   | String        |
| createdAt | LocalDateTime |

## Relationships

* One User can create many Posts.
* One User can create many Comments.
* One Post can have many Comments.

```text
User (1) -------- (N) Post

User (1) -------- (N) Comment

Post (1) -------- (N) Comment
```

## API Endpoints

### User APIs

```http
GET    /api/users
GET    /api/users/{id}
POST   /api/users
PUT    /api/users/{id}
DELETE /api/users/{id}
```

### Post APIs

```http
GET    /api/posts
GET    /api/posts/{id}
POST   /api/posts
PUT    /api/posts/{id}
DELETE /api/posts/{id}
```

### Comment APIs

```http
GET    /api/comments
GET    /api/comments/{id}
POST   /api/comments
DELETE /api/comments/{id}
```

## Project Structure

```text
src
 ├── controller
 ├── service
 ├── repo
 ├── model
 └── BlogManagementApplication
```

## Future Improvements

* Spring Security
* BCrypt Password Hashing
* JWT Authentication
* Role-Based Authorization
* Swagger Documentation
* Global Exception Handling
* Validation using @Valid
* Unit Testing with JUnit and Mockito
* Email Notifications

## Getting Started

1. Clone the repository.
2. Create a MySQL database.

```sql
CREATE DATABASE blogdb;
```

3. Configure `application.properties`.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/blogdb
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
```

4. Run the application.

```bash
mvn spring-boot:run
```

5. Access APIs using Postman.

## Author

Nitish Kumar Pandit

Spring Boot backend project created for learning REST APIs, JPA relationships, and database management.
