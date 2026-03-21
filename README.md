# E-Commerce Backend System

A production-style RESTful E-Commerce Backend built using Java, Spring Boot, PostgreSQL, JWT Authentication, and Role-Based Access Control (RBAC).
This project simulates a real-world backend system for an online shopping platform.

## Overview

This project focuses on:

Secure authentication and authorization

Scalable layered architecture

Structured relational database design

REST API development

Deployment-ready backend services

## Features
Authentication and Security

User registration and login

JWT-based authentication

Role-Based Access Control (RBAC)

Password encryption using Spring Security

User Management

Register new users

Login existing users

View user profile

Manage user roles

Product Management

Add products

Update product details

Delete products

View and search products

Cart Management

Add items to cart

Update cart quantity

Remove items from cart

View cart

Order Management

Place orders

View order history

Manage order status

## Tech Stack
Backend

Java

Spring Boot

Spring Web

Spring Data JPA

Spring Security

Security

JWT

BCrypt

Role-Based Access Control

Database

PostgreSQL

Tools

Maven

Postman

Deployment

AWS EC2

AWS RDS

## Project Structure
src
├── controller
├── service
├── repository
├── entity
├── dto
├── config
├── security
└── exception

## Architecture Layers

Controller Layer – Handles API requests

Service Layer – Contains business logic

Repository Layer – Interacts with database

Security Layer – Handles authentication and authorization

## Database Design
Main Entities

User

Role

Product

Cart

Order

OrderItem

## API Endpoints
Authentication

POST /auth/register

POST /auth/login

Users

GET /users/profile

GET /users

Products

GET /products

GET /products/{id}

POST /products

PUT /products/{id}

DELETE /products/{id}

Cart

GET /cart

POST /cart/add

PUT /cart/update

DELETE /cart/remove/{id}

Orders

POST /orders

GET /orders

GET /orders/{id}

## Setup Instructions
Prerequisites

Java 17+

Maven

PostgreSQL

Git

Clone Repository
git clone https://github.com/sakshi-nandgude/E-Commerce-Platform

Configure Database
CREATE DATABASE ecommerce_db;

Update application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/ecommerce_db
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update

Configure JWT

jwt.secret=your_secret_key

jwt.expiration=86400000


Build Project

mvn clean install

Run Application

mvn spring-boot:run

Application runs on:

http://localhost:8080

## Testing with Postman
Register
POST /auth/register
{
  "name": "Sakshi",
  "email": "sakshi@example.com",
  "password": "password123",
  "role": "CUSTOMER"
}
Login
POST /auth/login
{
  "email": "sakshi@example.com",
  "password": "password123"
}
Authorization Header
Authorization: Bearer <JWT_TOKEN>

Backend can be deployed on AWS EC2

Application accessible via public endpoint

## Security

JWT authentication

Password hashing with BCrypt

Role-based authorization

Protected endpoints

## Future Improvements

Payment gateway integration

Email notifications

Product reviews

Docker support

CI/CD pipeline

Swagger documentation

## Author

Sakshi Nandgude
MSc Business Analytics, University of Limerick
