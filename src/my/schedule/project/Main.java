package my.schedule.project;
import java.util.Scanner;
public class Main {
  public static void main(String[] args) { //This Method is now used primarily for debugging
    Main main = new Main();
    Schedule schedule = new Schedule();
    Scanner userI = new Scanner(System.in);
    System.out.println("Type C to add a course\nEnter any other Key to Exit");
    String start = userI.next();
    if(start.equals("C")){
    schedule.addCourse(main.addCourse(schedule));}
    else{
      System.out.println("No Course Was Added");
    }
  }
  
    private Course addCourse(Schedule schedule){
      Scanner in = new Scanner(System.in);
      System.out.println("Enter the name of the course you want to add: ");
      String name = in.nextLine();
      
      System.out.println("Enter the amount of credits for this course(ex: 4). ");
      int credits = in.nextInt();
      in.nextLine();

      System.out.println("Enter start time for this course(ex: 12:00 PM): ");
      String timeStart = in.nextLine();

      System.out.println("Enter end time for this course(ex: 12:00 PM): ");
      String timeEnd = in.nextLine();
      
    Course newCourse = new Course(name,credits,timeStart,timeEnd);
      
    schedule.addCourse(newCourse);
    System.out.println(schedule.toString());
    System.out.println("Course added successfully!");
    return newCourse;}
    

  
  
}
