package my.schedule.project;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
public class Course implements Comparable<Course>{
  // Format Time should be like this: "12:00 PM"
  // Day should be like this: "Monday"
  //implement something to convert 12 to an int and 00 to an int formatted as hour and minutes and PM or AM as Meridiem

  private String name;
  public static final String[] days = {"Monday","Tuesday","Wednesday", "Thursday","Friday"};
  private ArrayList<String> scheduledDays;
  private int credits;
  private String timeStart;
  private String timeEnd;

  //Constructor
  public Course(String name,int credits, String timeStart, String timeEnd){
    this.name = name;
    this.credits = credits;
    this.timeStart = timeStart;
    this.timeEnd = timeEnd;
    this.scheduledDays = new ArrayList<String>();
  }
  
  public void addDays(String day){
    scheduledDays.add(day); //adds days to arraylist scheduledDays
  }

  public void removeDays(String day){
    scheduledDays.remove(day); //removes days from arraylist scheduledDays
  }

  public boolean equals(Object other){
    if(other instanceof Course){ // checks if object instance of course
  Course otherCourse = (Course)other; //other object constructor(type casting)
  return name.equals(otherCourse.name) &&
  credits == otherCourse.credits && //return true if all fields equal, false if not
timeStart.equals(otherCourse.timeStart)&&timeEnd.equals(otherCourse.timeEnd);}
    return false;}//if object not instance of Course return false

  public String toString(){ //Converts Object to String
    StringBuilder daysString = new StringBuilder(); //String builder object for more efficent String construction.
    for (String day : scheduledDays) {
        daysString.append(day).append(", ");
    }
    return "Course: " + this.name + "\nDays: " + daysString.toString() + "\nCredits: " + this.credits + "\nTime: " + this.timeStart + " - " + this.timeEnd;
  }

  public String getName(){
    return this.name;
  }

  public int getCredits(){
    return this.credits;
  }

  public String getStart(){
    return this.timeStart;
  }

  public String getEnd(){
    return this.timeEnd;
  }

  public LocalTime parseStart(String timeStart){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
    return LocalTime.parse(timeStart, formatter);
  }

  public LocalTime parseEnd(String timeEnd){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
    return LocalTime.parse(timeEnd, formatter);
  }

  public int compareTo(Course other) {
    parseStart(timeStart);
    parseStart(other.timeStart);
    return parseStart(timeStart).compareTo(parseStart(other.timeStart));
  }
}
