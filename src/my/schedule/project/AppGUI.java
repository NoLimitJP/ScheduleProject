package my.schedule.project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class AppGUI extends JFrame implements ActionListener {
    JButton addButton, removeButton, editButton, displayButton;
    JPanel fieldsPanel, buttonPanel;
    JTextField name,start,end,credits, removeField, editField;
    JLabel nameLabel, startLabel, endLabel, creditLabel, removeLabel, editLabel;
    JTable table;
    JScrollPane scrollPane;
    private DefaultTableModel tableModel;
    private CourseDAO courseDAO;
    private Course course;

    AppGUI(){
        //Methods for setting up GUI for schedule application
        innit(); // innitializes all instance variables
        panelSetUp(); //adds buttons and text fields to their Jpanels
        guiSetUp(); //formats the GUI such as size, adding panels(with buttons, textfields, the table, etc.)
        actionListenerSetUp();  //adds the action listeners with their respective buttons assigned to the actionPerformed method
}


    public void innit(){
        courseDAO = new CourseDAO(); // new courseDAO object for handling database operations
        String[] columnNames = {"Course Name", "Start Time", "End Time", "Credits"}; //Array containing column names
        tableModel = new DefaultTableModel(columnNames, 0); //Table Model with the array containing column names as parameters as well as initial row count
        table = new JTable(tableModel); // new JTable object with the tableModel variable as the parameter
        scrollPane = new JScrollPane(table); //scroll pane object for viewing in case the table reaches a certain size

        //UI Buttons
        addButton = new JButton("add"); //add button
        removeButton = new JButton("remove"); //remove button
        editButton = new JButton("edit"); //edit button
        displayButton = new JButton("display"); //display button

        //TextFields
        nameLabel = new JLabel("Name"); // name label
        name = new JTextField(); // name text field
        startLabel = new JLabel("Start time"); // start time label
        start = new JTextField(); // start time text field
        endLabel = new JLabel("End time"); // end time label
        end = new JTextField(); // end time text field
        creditLabel = new JLabel("Credits"); // credits label
        credits = new JTextField(); // credits text field
        removeLabel = new JLabel("Remove Course (Enter Name)");
        removeField = new JTextField();
        editLabel = new JLabel("Edit Name (Enter New Name)");
        editField = new JTextField();
    }

    public void panelSetUp(){
        //UI Panels
        buttonPanel = new JPanel(); // innitializes the button panel
        fieldsPanel = new JPanel(); // innitializes the fields panel
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.PAGE_AXIS)); //sets the fields panel layout tot box layout
        buttonPanel.setLayout(new FlowLayout()); //sets the button panel layout to flow layout

        buttonPanel.add(addButton); //adds the add button to the button panel, the next 3 lines do the same but for their respective buttons
        buttonPanel.add(removeButton);
        buttonPanel.add(editButton);
        buttonPanel.add(displayButton);

        fieldsPanel.add(nameLabel);//adds the name label to the fields panel, lines 78, 80,and 82 do the same but for their respective labels
        fieldsPanel.add(name);//adds the name field to the fields panel, lines 79, 81, and 83 do the same but for their respective fields
        fieldsPanel.add(startLabel);
        fieldsPanel.add(start);
        fieldsPanel.add(endLabel);
        fieldsPanel.add(end);
        fieldsPanel.add(creditLabel);
        fieldsPanel.add(credits);
        fieldsPanel.add(removeLabel);
        fieldsPanel.add(removeField);
        fieldsPanel.add(editLabel);
        fieldsPanel.add(editField);
    }
    public void guiSetUp(){
            this.add(fieldsPanel, BorderLayout.PAGE_START); //adds the fields panel to the JFrame and uses box layout specifically at the start of the page
            this.add(buttonPanel, BorderLayout.PAGE_END); //adds the button panel to the JFrame and uses box layout specifically at the end of the page
            this.add(scrollPane, BorderLayout.CENTER); //adds the scroll pane to the JFrame and uses box layout specifically at the center of the page

            this.setName("Schedule Application"); //sets the name of the JFrame
            this.setSize(420, 420); // x-axis and y-axis sizing for the JFrame
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allows program to be stopped on exit
            this.getContentPane().setBackground(new Color(185,245,195));//allows you to set the background color using RGB values
            this.setVisible(true); //sets visiblity parameter value to true, false = invisible
            
    }

public void actionListenerSetUp(){
    addButton.addActionListener(this); //assigns action listener to the add button
    removeButton.addActionListener(this);//assigns action listener to the remove button
    editButton.addActionListener(this);//assigns action listener to the edit button
    displayButton.addActionListener(this);//assigns action listener to the display button
}

public void actionPerformed(ActionEvent e){
    if(e.getSource()== addButton){
        try {
            boolean check = false;
            int num = Integer.parseInt(credits.getText()); // this parses the input from the credits textfield into an integer since the Course object takes the parameter as an int
            course = new Course(name.getText(),num, start.getText(), end.getText()); //constructs a Course object for database insertion
            courseDAO.insertCourse(course.getName(), course.parseStart(course.getStart()), course.parseEnd(course.getEnd()), course.getCredits()); //uses the Course object and its methods in order to use the insertCourse method from the CourseDAO object for database insertion
        } catch (NumberFormatException ex) {//catches incorrect input, more specifically non integers
            JOptionPane.showMessageDialog(null, "Credits must be a valid integer.", "Input Error", JOptionPane.ERROR_MESSAGE); //JOptionPane for error message within UI
        } catch (Exception ex) {
            ex.printStackTrace(); // logs unexpected errors for debugging
        }
    }

    if(e.getSource()== removeButton){
         String courseRemoved = removeField.getText();
         try {
            courseDAO.deleteCourse(courseRemoved);
         } catch (Exception y) {
            JOptionPane.showMessageDialog(this, "This course doesn't exist");
         }
         
    }
    if(e.getSource()== editButton){
        if(credits.getText().isEmpty() && !editField.getText().isEmpty()){
        try {
            String new_name = editField.getText();
            courseDAO.editCourse(name.getText(), new_name);
        } catch (Exception k) {
            JOptionPane.showMessageDialog(this, "Try again");
        }
    }
        if(!credits.getText().isEmpty()&& !editField.getText().isEmpty()){
            try{
                int num =Integer.parseInt(credits.getText());
                courseDAO.editCourse(name.getText(),editField.getText(), num);}
        catch(Exception z){
            JOptionPane.showMessageDialog(this, "Error, a number should go into the credits field");
        }
    }
    if(!credits.getText().isEmpty() && editField.getText().isEmpty() && !name.getText().isEmpty()){
        try {
            int num = Integer.parseInt(credits.getText());
            courseDAO.editCourse(name.getText(), num);

        } catch (Exception q) {
            JOptionPane.showMessageDialog(this, "Enter the name of the course you are trying to change");
        }
    }
    }

    if(e.getSource()== displayButton){
        List<String[]> courses = courseDAO.getCourses(); // fetches data from the DAO, courseDAO
        if (courses != null) {
            tableModel.setRowCount(0); // clears existing rows
            for (String[] course : courses) {
                tableModel.addRow(course); // adds each course to the table
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error retrieving courses.");// if there are no courses an error message will be given to the user
        }}

    }
    
 


public static void main(String[] args) {
    new AppGUI(); //this will run our program
}

}

