# Schedule Project

## Project Description  
This is a scheduling application designed for students or faculty to organize their semester schedules. Users can input details such as the course name, start time, end time, and course credits. The project was developed using Object-Oriented Programming principles and integrates with a PostgreSQL database for persistent storage.

## Components

### 1. **Course Object**  
- Represents an individual course.  
- Includes a constructor and getter methods (e.g., `getX()`).

### 2. **Data Access Object (DAO)**  
- Handles database interactions with the PostgreSQL database.  
- Implements CRUD operations: **Create**, **Read**, **Update**, and **Delete**, allowing seamless database manipulation directly from the Java program.

### 3. **GUI Object**  
- Built using the Swing framework.  
- Extends the `JFrame` class and implements the `ActionListener` interface.  
- Provides a user interface for interacting with the database.  
- Components used include:
  - `JOptionPane`  
  - `JPanel`  
  - `JButton`  
  - `JTextField`  
  - `JLabel`  
  - `JTable`

## Features
- The GUI is functional and supports all database operations.
- The design and layout are subject to refinement.

## Design Details  
All three objects reside within the same package, enabling seamless interaction and method sharing among them.


