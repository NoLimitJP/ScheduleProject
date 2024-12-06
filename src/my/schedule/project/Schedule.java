package my.schedule.project;

import java.time.LocalTime;
import java.util.*;
public class Schedule{
  public LinkedList<Course> classSchedule;
  public ListIterator<Course> classScheduleIterator;
  public Schedule(){
    this.classSchedule = new LinkedList<Course>();
    this.classScheduleIterator = classSchedule.listIterator();
  }
  
  public void addCourse(Course course){
    classSchedule.add(course);
  }
  
  public void removeCourse(Course course){
    classSchedule.remove(course);
  }
  
  public String toString(){
          StringBuilder scheduleString = new StringBuilder();
          ListIterator<Course> iterator = classSchedule.listIterator(); // Create a new iterator
          while (iterator.hasNext()) {
              scheduleString.append(iterator.next()).append("\n");
          }
          return scheduleString.toString();
      }
  
  }
