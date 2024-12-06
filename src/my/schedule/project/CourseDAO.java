package my.schedule.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
//TO DO: November 27, 2024, finish code for buttons and text fields to have overall functionality and make sure to specify use-cases
public class CourseDAO {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/schedule_app";
    private static final String USER = "postgres";
    private static final String PASS = "postgres";

    public List<String[]> getCourses() {
        String query = "SELECT * FROM courses"; // SQL query
        List<String[]> courses = new ArrayList<>(); //Array List of type String to store data

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
            while (rs.next()) { //reads each result set 
                // collect each row as a String array
                Time startTime = rs.getTime("start_time");
                Time endTime = rs.getTime("end_time");

                String formattedStart = startTime.toLocalTime().format(formatter);
                String formattedEnd = endTime.toLocalTime().format(formatter);

                String[] course = {
                    rs.getString("course_name"),
                    formattedStart, //formatted start time
                    formattedEnd,//formatted end time
                    String.valueOf(rs.getInt("credits")) //Convert int into String
                };
                courses.add(course); // Add to the list
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses; // Return the collected data
    }

    public void insertCourse(String name, LocalTime start_time, LocalTime end_time, int credits){//database insertion method for "courses" table
String query = "INSERT INTO courses (course_name, start_time, end_time, credits) VALUES (?, ?, ?, ?)";//insertion query
        
            List<String[]> check = this.getCourses();
            int counter = 0;
            for(String[] course:check){
                if(course[0].equals(name)){counter++;}
            }
            if(counter<=0){
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);//try catch for database connection
             PreparedStatement pstmt = conn.prepareStatement(query)) {//prepared statement for clean insertion into the database
            
            Time sqlStartTime = Time.valueOf(start_time);//converting 'local time' object to an sql 'time' object
            Time sqlEndTime = Time.valueOf(end_time);//line 36 but for the ending time
             
            // Set the parameters for the query
            pstmt.setString(1, name);/*using the setObject method matching each parameter to the right object */
            pstmt.setTime(2, sqlStartTime);
            pstmt.setTime(3, sqlEndTime);
            pstmt.setInt(4, credits);

            int rowsAffected = pstmt.executeUpdate(); //confirming whether or not the insertion was successful
            if (rowsAffected > 0) {
                System.out.println("Course added successfully!");
            } else {
                System.out.println("Failed to add course.");
            }
        } catch (SQLException e) {
            e.printStackTrace();}
    } else{System.out.println("This course has already been added");}
}

        public void deleteCourse(String name){
            String query = "DELETE FROM courses WHERE course_name = ?";

            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, name); 

            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) deleted.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        public void editCourse(String courseName, String newName){
            String updateQuery = "UPDATE courses SET course_name = ? WHERE course_name = ?";
            try {
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement pstmt = conn.prepareStatement(updateQuery);
                pstmt.setString(1, newName);
                pstmt.setString(2, courseName);

                int rowsUpdated = pstmt.executeUpdate();

        // Provide feedback on the operation
        if (rowsUpdated > 0) {
            System.out.println("Course updated successfully.");
        } else {
            System.out.println("No course found with the given name.");
        }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        public void editCourse(String courseName, String newName, int newCredits){
            String updateQuery = "UPDATE courses SET course_name = ?, credits = ? WHERE course_name = ?";
            try {
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement pstmt = conn.prepareStatement(updateQuery);
                pstmt.setString(1, newName);
                pstmt.setInt(2, newCredits);
                pstmt.setString(3, courseName);
                

                int rowsUpdated = pstmt.executeUpdate();

        // Provide feedback on the operation
        if (rowsUpdated > 0) {
            System.out.println("Course updated successfully.");
        } else {
            System.out.println("No course found with the given name.");
        }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        public void editCourse(String courseName, int newCredits){
            String updateQuery = "UPDATE courses SET credits = ? WHERE course_name = ?";
            try {
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement pstmt = conn.prepareStatement(updateQuery);
                pstmt.setInt(1, newCredits);
                pstmt.setString(2, courseName);
                

                int rowsUpdated = pstmt.executeUpdate();

        // Provide feedback on the operation
        if (rowsUpdated > 0) {
            System.out.println("Course updated successfully.");
        } else {
            System.out.println("No course found with the given name.");
        }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        
    public static void main(String[] args) {
        CourseDAO dao = new CourseDAO(); //object to test methods
        dao.getCourses();// testing the getCourses method
        Course test = new Course("Social Studies",4,"12:00 AM","11:30 PM");// creating a test Course object
        String testName = test.getName();
        int testCredits = test.getCredits();
        LocalTime testStart = test.parseStart(test.getStart());//using the parseStart method written in Course.java file to parse the String to a LocalTime object
        LocalTime testEnd = test.parseEnd(test.getEnd()); //similar to line 61, but using the parseEnd method
        dao.insertCourse(testName, testStart, testEnd, testCredits);
        dao.deleteCourse("Math");
        dao.getCourses();
        


    }
}
