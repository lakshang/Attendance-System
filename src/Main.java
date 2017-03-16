/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Lakshan
 */
public class Main {

    public static void main(String[] args) {

        AttendanceLog attendance_log = new AttendanceLog();
        Student student = new Student();
        Lecturer lecturer = new Lecturer();

        Scanner sc = new Scanner(System.in); //Creates the Scanner
        Scanner src = new Scanner(System.in);
        generalmenuDetials();//Calls the Menu method

        String selectMain = sc.next();//Gets the required input
        String select2;
        switch (selectMain) {
            case "1":
                //if input is "1 - Student Details"
                studentmenuDetails();//Calls the Student Details Menu Method
                select2 = sc.next();//Gets the input

                if (select2.equals("1")) {//If input equals "1"
                    student.viewStudents();//Display the Student Details
                    System.out.print("Sucessful !");

                }
                backMethod();//Calls the backMethod to get back
                if (select2.equals("2")) {
                    //Add Student
                    try {
                        System.out.print("Enter Student ID  ");
                        int id = sc.nextInt();

                        System.out.print("Enter Last Name  ");
                        String last_name = sc.next();

                        System.out.print("Enter First Name  ");
                        String first_name = sc.next();

                        student.addStudent(id, last_name, first_name);
                        System.out.println("Operation successfully Completed !");
                        backMethod();//Calls the backMethod to get back
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Invalid Input");
                        backMethod();//Calls the backMethod to get back
                    }//Exception Handling of Mismatch Data
                }

                if (select2.equals("3")) {

                    System.out.println("Enter Student No");
                    System.out.println("*Please Note you can only edit the First Name and Last Name");
                    int id = sc.nextInt();

                    ArrayList<Student> student_detail = Student.searchStudent(id);//Search and gets the required information on the id inputted

                    if (student_detail.isEmpty()) {

                        System.out.println("Invalid Student ID");//If ID doesn't match
                        backMethod();
                    } else {
                        //Updates the details
                        Student s = student_detail.get(0);
                        System.out.println("Student No     Last Name      First Name     ");
                        System.out.println(s.getStudentNo() + "       " + s.getLastName() + "         " + s.getFirstName());

                        System.out.println("Enter Last Name");
                        String lastnameUp = sc.next();

                        System.out.println("Enter First Name");
                        String firstnameUp = sc.next();
                        //Sets up the new values
                        s.setLastName(lastnameUp);
                        s.setFirstName(firstnameUp);

                        ArrayList<Student> student_list = Student.getStudents();//Gets the students stored
                        ArrayList<Student> updated_list = new ArrayList<Student>();//Creates a new Array 

                        for (Student st : student_list) {

                            if (st.getStudentNo() == s.getStudentNo()) {

                                updated_list.add(s);

                            } else {

                                updated_list.add(st);

                            }//Checks the studentNo and add details

                        }

                        Student.updateStudent(updated_list);//Update the text file
                        System.out.println("Operation successfully Completed !");
                        backMethod();//Calls the backMethod to get back
                    }

                }
                if (select2.equals("4")) {

                    System.out.println("Enter Student No");
                    int id = sc.nextInt();

                    ArrayList<Student> student_detail = Student.searchStudent(id);

                    if (student_detail.isEmpty()) {

                        System.out.println("Invalid Student ID");

                    } else {

                        Student s = student_detail.get(0);
                        System.out.println("Student No     Last Name      First Name     ");
                        System.out.println(s.getStudentNo() + "       " + s.getLastName() + "         " + s.getFirstName());

                        ArrayList<Student> student_list = Student.getStudents();
                        ArrayList<Student> updated_list = new ArrayList<Student>();

                        for (Student st : student_list) {

                            if (st.getStudentNo() != s.getStudentNo()) {

                                updated_list.add(st);

                            }

                        }

                        Student.updateStudent( updated_list);
                        System.out.println("Operation successfully Completed !");
                        backMethod();//Calls the backMethod to get back
                    }

                }
                break;
            case "2":
                lecturerDetails();
                select2 = sc.next();
                if (select2.equals("1")) {
                    lecturer.viewLecturers();
                    System.out.print("Sucessful !");

                }//View lecturers
                if (select2.equals("2")) {
                    try {    //Add Lecturer
                        System.out.print("Enter Unit ID ");
                        String unitID = src.next();
                        System.out.print("Enter Unit Name ");
                        String unitName = src.next();
                        System.out.print("Enter Room No ");
                        String roomNo = src.next();
                        System.out.print("Enter Room Name ");
                        String roomName = src.next();
                        System.out.print("Enter Lecturer Name ");
                        String lecturerName = src.next();
                        System.out.print("Enter Room Capacity ");
                        int roomCapacity = src.nextInt();
                        System.out.print("Enter No of Students Enrolled ");
                        int studentEnroll = src.nextInt();

                        lecturer.addLecturer(unitID, unitName, roomNo, roomName, lecturerName, roomCapacity, studentEnroll);//Updates the new details
                        System.out.print("Operation Sucessfully Completed !");
                        backMethod();//Calls the backMethod to get back
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Invalid Input");
                        backMethod();//Calls the backMethod to get back

                    }
                }
                if (select2.equals("3")) {

                    System.out.println("Enter Unit ID");
                    String unitID = sc.next();

                    ArrayList<Lecturer> lecturer_detail = Lecturer.editsearchLecturer(unitID);//Gets the editsearchLecturer method

                    if (lecturer_detail.isEmpty()) {

                        System.out.println("Invalid Unit ID");
                        //Not match id
                        backMethod();//Calls the backMethod to get back
                    } else {

                        Lecturer lec = lecturer_detail.get(0);
                        System.out.println("Unit ID      Unit Name     Room No   Room Name      Lecturer Name      Room Capacity     No. of Students Enrolled    ");
                        System.out.println(lec.getUnitID() + "       " + lec.getUnitName() + "        " + lec.getRoomNo() + "       " + lec.getRoomName() + "       " + lec.getLecturerName() + "          " + lec.getRoomCapacity() + "                 " + lec.getNoOfStudentsEntrolled());
                        //Gets the Details
                        System.out.print("Enter Unit Name  ");
                        String unitName = sc.next();

                        System.out.print("Enter Room No  ");
                        String roomNo = sc.next();

                        System.out.print("Enter Room Name  ");
                        String roomName = sc.next();

                        System.out.print("Enter Lecturer Name  ");
                        String lecturerName = sc.next();

                        System.out.print("Enter Room Capacity  ");
                        int roomCap = sc.nextInt();

                        System.out.print("Enter Number of Studets Enrolled ");
                        int noEnroll = sc.nextInt();
                        //Sets the data
                        lec.setUnitName(unitName);
                        lec.setRoomNo(roomNo);
                        lec.setRoomName(roomName);
                        lec.setLecturerName(lecturerName);
                        lec.setRoomCapacity(roomCap);
                        lec.setNoOfStudentsEntrolled(noEnroll);

                        ArrayList<Lecturer> lecturer_list = Lecturer.getLecturers();
                        ArrayList<Lecturer> updated_list = new ArrayList<Lecturer>();

                        for (Lecturer st : lecturer_list) {

                            if (st.getUnitID().equals(lec.getUnitID())) {

                                updated_list.add(lec);

                            } else {

                                updated_list.add(st);

                            }

                        }//If unitID equals

                        Lecturer.updateLecturer("lecturer.txt", updated_list);//Update the text file
                        System.out.println("Operation successfully Completed !");
                        backMethod();//Calls the backMethod to get back
                    }

                }
                if (select2.equals("4")) {

                    System.out.println("Enter Unit ID");
                    String unitID = sc.next();

                    ArrayList<Lecturer> lecturer_detail = Lecturer.editsearchLecturer(unitID);

                    if (lecturer_detail.isEmpty()) {

                        System.out.println("Invalid Unit ID");
                        backMethod();//Calls the backMethod to get back
                    } else {

                        Lecturer lec = lecturer_detail.get(0);
                        System.out.println("Unit ID      Unit Name     Room No   Room Name      Lecturer Name      Room Capacity     No. of Students Enrolled    ");
                        System.out.println(lec.getUnitID() + "   ;    " + lec.getUnitName() + "    ;    " + lec.getRoomNo() + "   ;     " + lec.getRoomName() + "   ;    " + lec.getLecturerName() + "      ;    " + lec.getRoomCapacity() + "                 " + lec.getNoOfStudentsEntrolled());

                        ArrayList<Lecturer> lecturer_list = Lecturer.getLecturers();
                        ArrayList<Lecturer> updated_list = new ArrayList<Lecturer>();

                        for (Lecturer st : lecturer_list) {

                            if (!st.getUnitID().equals(lec.getUnitID())) {

                                updated_list.add(st);

                            }

                        }

                        Lecturer.updateLecturer("lecturer.txt", updated_list);
                        System.out.println("Operation successfully Completed !");
                        backMethod();//Calls the backMethod to get back
                    }
                }
                break;
            case "3":
                attendenceDetails();
                select2 = sc.next();
                switch (select2) {
                    case "1":
                        //View Attendance

                        attendance_log.viewAttendance();
                        break;
                    case "2":
                        //Generate Attendance Report

                        System.out.println("Please Enter Unit ID ");
                        String UnitID = sc.next();//Gets the Student ID
                        ArrayList<String> date_list = attendance_log.getAttendanceDate(UnitID);
                        if (date_list.isEmpty()) {
                            //Checks whether the ID is Valid
                            System.out.println("Invalid Unit ID");
                            backMethod();//Calls the backMethod to get back
                        } else {

                            System.out.println("Please Select Date");

                            for (int i = 0; i < date_list.size(); i++) {

                                System.out.println(i + 1 + "." + date_list.get(i));

                            }//Displays the Dates Available

                            String Date_Number = sc.next();//Asks for a Date

                            boolean isDateCorrect = false;

                            for (int i = 0; i < date_list.size(); i++) {

                                if (Date_Number.equals(String.valueOf(i + 1))) {

                                    isDateCorrect = true;
                                    break;
                                }
                            }
                            //Date Matches
                            if (isDateCorrect) {

                                String Date = date_list.get(Integer.parseInt(Date_Number) - 1);

                                ArrayList<AttendanceLog> search_attendance_log = attendance_log.searchAttendanceLog(UnitID, Date);
                                Lecturer l = lecturer.searchLecturer(UnitID);

                                if (search_attendance_log.isEmpty()) {

                                    System.out.println("Invalid Unit ID!");
                                    backMethod();//Calls the backMethod to get back
                                } else if (l == null) {

                                    System.out.println("No Lecturer Record Found!");
                                    backMethod();//Calls the backMethod to get back
                                } else {

                                    attendance_log.generateAttendanceReport(UnitID, Date, search_attendance_log, l);
                                    attendance_log.printAttendanceReport(UnitID, Date, search_attendance_log, l);
                                }

                            } else {

                                System.out.println("Invalid Date Selected!");
                                backMethod();//Calls the backMethod to get back
                            }

                        }
                        break;
                    default:
                        System.out.println("Invalid Number! Try Again");
                        backMethod();//Calls the backMethod to get back
                        break;
                }

                break;
            case "4":
                System.exit(0);
            default:
                System.out.println("Invalid Number! Try Again");
                backMethod();//Calls the backMethod to get back
                break;
        }

    }

    public static void studentmenuDetails() {
        System.out.println("Student Menu");
        System.out.println("1.View Students Detials");
        System.out.println("2.Add Student");
        System.out.println("3.Edit Student");
        System.out.println("4.Delete Student");
        System.out.println(">> Enter a Number to Procced: ");
    }//Student Menu

    public static void lecturerDetails() {
        System.out.println("Lecturers Details Menu");
        System.out.println("1.View Lecturer Detials");
        System.out.println("2.Add Lecturer");
        System.out.println("3.Edit Lecturer");
        System.out.println("4.Delete Lecturer");
        System.out.println(">> Enter a Number to Procced: ");

    }

    public static void attendenceDetails() {
        System.out.println("Attendance Menu");
        System.out.println("1.View Attendance");
        System.out.println("2.Generate Attendance Report");
        System.out.println(">> Enter a Number to Procced: ");
    }

    public static void generalmenuDetials() {

        System.out.println(" Welcome to University of Gugsi ! ");
        System.out.println("_______________________________________");
        System.out.println("STUDENT ATTENDANCE SYSTEM");
        System.out.println("Menu");

        System.out.println("1.Students Detials");
        System.out.println("2.Lecturers Detials");
        System.out.println("3.Attendance ");
        System.out.println("4.Exit");

        System.out.println(">> Enter a Number to Procced: ");
    }

    public static void backMethod() {
        Scanner src = new Scanner(System.in);
        String select3;
        System.out.print("[1] Continue to Main Menu");
        System.out.print("[2] Exit");
        select3 = src.next();
        if (select3.equals("1")) {
            BackLineup.backMain();
        }
    }
}
