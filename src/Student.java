/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author Lakshan
 */
public class Student {

    private int StudentNo;
    private String LastName;
    private String FirstName;

    public int getStudentNo() {
        return StudentNo;
    }

    public void setStudentNo(int StudentNo) {
        this.StudentNo = StudentNo;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public static void main(String[] args) {

        ArrayList<Student> student_list = Student.getStudents();

        for (Student student : student_list) {

            System.out.println(student.getStudentNo());
            System.out.println(student.getLastName());
            System.out.println(student.getFirstName());

        }

    }
    public static ArrayList<Student> getStudents() {

        ArrayList<Student> student_list = new ArrayList<>();

        for (String line : Data.readData("studentsdetails.txt")) { //Read the text file with the readData method

            StringTokenizer token = new StringTokenizer(line, " ");//Sperates the lines from the spaces

            Student s = new Student();

            while (token.hasMoreTokens()) {

                s.setStudentNo(Integer.parseInt(token.nextToken().trim()));
                s.setLastName(token.nextToken().trim());
                s.setFirstName(token.nextToken().trim());

                student_list.add(s);//adds to the created array list

            }
        }

        return student_list;//returns the array list

    }
    public void viewStudents() {

        ArrayList<Student> student_details = Student.getStudents();//Creates an array list and calls the getStudent method to read the students stored

        System.out.println("Student No     Last Name      First Name     ");

        for (Student info : student_details) {

            System.out.println(info.getStudentNo()+ "       " + info.getLastName()+ "         " + info.getFirstName()      );
            //Display the details in text file
        }

    }

    public void addStudent(int StudentNo, String LastName, String FirstName) {

        Student s = new Student();
        s.setStudentNo(StudentNo);
        s.setLastName(LastName);
        s.setFirstName(FirstName);
        //Sets the values

        ArrayList<Student> student_list = getStudents();
        student_list.add(s);//Adds the value to the array.

        Student.updateStudent(student_list);//Updates the text file.

    }

    public static void updateStudent(ArrayList<Student> student_list) {
        
        try {

            ArrayList<String> lines = new ArrayList<>();//Creates a new Array List

            BufferedReader br = new BufferedReader(new FileReader("B://Lectures//BEngHonsSoftware Engineering//First Year//Semester 01/Software Development Principles 01 - Mr.Guhanathan Pooravi/CourseWork/SDP02 - CW02 (Attendance System)/CW2_UoG/src/studentdetails.txt" )); //Reads the File
            String line;//Creates a Variable to Store the read Lines

            while ((line = br.readLine()) != null) {

                lines.add(line);
            //Read until null
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter("B://Lectures//BEngHonsSoftware Engineering//First Year//Semester 01//Software Development Principles 01 - Mr.Guhanathan Pooravi/CourseWork/SDP02 - CW02 (Attendance System)/CW2_UoG/src/studentdetails.txt"));//Writes Back the File

            String updated_content = lines.get(0) + System.lineSeparator();//Uses line sperator to go from line to line

            for (int i = 0; i < student_list.size(); i++) {

                Student s = student_list.get(i);
                updated_content += s.getStudentNo() + "   " + s.getLastName() + "   " + s.getFirstName();
                //Writes the data line by line 
                if (i != student_list.size()-1) {//If it not the last element element
                    updated_content += System.lineSeparator();
                }
                
            }

            bw.write(updated_content);
            bw.flush();
            bw.close();

        } catch (Exception e) {

            System.out.println("File Not Found");//Exception Handling
        }

    }
    public static ArrayList<Student> searchStudent(int StudentNo) {

        ArrayList<Student> student_detail = Student.getStudents();//Gets student details

        ArrayList<Student> search_studentDetials = new ArrayList<>();//Creates a new Array List

        for (Student a : student_detail) {

            if (a.getStudentNo()==StudentNo) {//If StudentNo matches

                search_studentDetials.add(a);//Add the details

            }

        }

        return search_studentDetials;
    }

}
    
    
    
    


