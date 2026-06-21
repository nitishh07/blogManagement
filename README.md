# Blog Management System

A secure RESTful Blog Management System built using **Spring Boot**, **Spring Security**, **JWT Authentication**, **Spring Data JPA**, and **MySQL**. Users can register, log in, create blog posts, comment on posts, and manage only their own content.

---

## Features

### Authentication & Authorization

* User Registration
* User Login
* JWT Token Generation
* Password Encryption using BCrypt
* Stateless Authentication using JWT
* Protected API Endpoints

### Post Management

* Create Post
* View All Posts
* View Post by ID
* Update Own Post
* Delete Own Post

### Comment Management

* Add Comment to a Post
* View All Comments
* View Comment by ID
* Update Own Comment
* Delete Own Comment

### Security Features

* JWT Authentication Filter
* Password Hashing using BCrypt
* Ownership Validation

  * Users can edit only their own posts.
  * Users can delete only their own posts.
  * Users can edit only their own comments.
  * Users can delete only their own comments.

### Exception Handling

* Global Exception Handler
* Custom Error Responses
* Validation Error Handling

---

## Tech Stack

### Backend

* Java 21
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate

### Database

* MySQL

### Authentication

* JWT (JSON Web Token)

### Documentation

* Swagger / OpenAPI

### Build Tool

* Maven

---

## Project Structure

```text
src
│
├── controller
│   ├── AuthController
│   ├── PostController
│   ├── CommentController
│   └── UserController
│
├── service
│   ├── UserService
│   ├── PostService
│   ├── CommentService
│   └── MyUserDetailsService
│
├── model
│   ├── User
│   ├── Post
│   ├── Comment
│   ├── LoginRequest
│   └── UserPrincipal
│
├── repo
│   ├── UserRepo
│   ├── PostRepo
│   └── CommentRepo
│
├── config
│   ├── SecurityConfig
│   ├── JwtService
│   ├── JwtFilter
│   └── AuthResponse
│
├── exception
│   └── GlobalExceptionHandler
│
└── dto
    ├── RegisterRequest
    └── UserResponse
```

---

## Database Configuration

Configure MySQL in `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/blogdb
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## API Endpoints

### Authentication

#### Register

```http
POST /auth/register
```

Request

```json
{
  "name": "Nitish",
  "email": "nitish@gmail.com",
  "password": "1234"
}
```

---

#### Login

```http
POST /auth/login
```

Request

```json
{
  "email": "nitish@gmail.com",
  "password": "1234"
}
```

Response

```json
{
  "token": "jwt_token"
}
```

---

## Posts

### Create Post

```http
POST /api/posts
```

Request

```json
{
  "title": "My First Blog",
  "content": "This is my first blog post."
}
```

---

### Get All Posts

```http
GET /api/posts
```

---

### Get Post By ID

```http
GET /api/posts/{id}
```

---

### Update Post

```http
PUT /api/posts/{id}
```

---

### Delete Post

```http
DELETE /api/posts/{id}
```

---

## Comments

### Create Comment

```http
POST /api/comments/post/{postId}
```

Request

```json
{
  "content": "Great post!"
}
```

---

### Get All Comments

```http
GET /api/comments
```

---

### Get Comment By ID

```http
GET /api/comments/{id}
```

---

### Update Comment

```http
PUT /api/comments/{id}
```

---

### Delete Comment

```http
DELETE /api/comments/{id}
```

---

## Swagger Documentation

After running the application:

```text
http://localhost:8080/swagger-ui/index.html
```

---

## Running the Application

Clone the repository:

```bash
git clone <repository-url>
```

Navigate to project directory:

```bash
cd blogManagement
```

Build the project:

```bash
mvn clean install
```

Run the application:

```bash
mvn spring-boot:run
```

---

## Future Improvements

* Role Based Authorization (ADMIN / USER)
* Like & Dislike System
* Pagination & Sorting
* Search Posts by Title
* Docker Support
* Refresh Tokens
* Email Verification
* Password Reset Functionality
* Unit & Integration Testing

---

## Author

**Nitish Kumar Pandit**

* KIIT University
* B.Tech CSE
* Backend Development | Spring Boot | Java | MySQL

⭐ If you found this project useful, consider giving it a star on GitHub.
