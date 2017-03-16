/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author Lakshan Gunarathna
 */
public class AttendanceLog {

    private String RoomNo;
    private int StudentID;
    private String UnitID;
    private String LecturerName;
    private String Date;
    private int WeekNo;

    public String getRoomNo() {
        return RoomNo;
    }

    public void setRoomNo(String RoomNo) {
        this.RoomNo = RoomNo;
    }

    public int getStudentID() {
        return StudentID;
    }

    public void setStudentID(int StudentID) {
        this.StudentID = StudentID;
    }

    public String getUnitID() {
        return UnitID;
    }

    public void setUnitID(String UnitID) {
        this.UnitID = UnitID;
    }

    public String getLecturerName() {
        return LecturerName;
    }

    public void setLecturerName(String LecturerName) {
        this.LecturerName = LecturerName;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public int getWeekNo() {
        return WeekNo;
    }

    public void setWeekNo(int WeekNo) {
        this.WeekNo = WeekNo;
    }

    public static void main(String[] args) {

        for (AttendanceLog l : getAttendanceLog()) {
            System.out.print(l.getRoomNo());
            System.out.print(l.getStudentID());
            System.out.print(l.getUnitID());
            System.out.print(l.getLecturerName());
            System.out.print(l.getDate());
            System.out.print(l.getWeekNo());

            System.out.println("");
        }

    }

    public static ArrayList<AttendanceLog> getAttendanceLog() {

        ArrayList<AttendanceLog> attendancelog_list = new ArrayList<>();//Creates a Array List

        for (String line : Data.readData("attendancelog.txt")) {//Reads the Text File (FileName)

            StringTokenizer token = new StringTokenizer(line, " ");//Seperates from the space

            AttendanceLog s = new AttendanceLog();

            while (token.hasMoreTokens()) {

                s.setRoomNo(token.nextToken().trim());
                s.setStudentID(Integer.parseInt(token.nextToken().trim()));
                s.setUnitID(token.nextToken().trim());
                s.setLecturerName(token.nextToken().trim());
                s.setDate(token.nextToken().trim());
                s.setWeekNo(Integer.parseInt(token.nextToken().trim()));

                attendancelog_list.add(s);//Adds the details to the Array List

            }
        }

        return attendancelog_list;//return 

    }

    public ArrayList<AttendanceLog> searchAttendanceLog(String UnitID, String Date) {

        ArrayList<AttendanceLog> attendance_list = AttendanceLog.getAttendanceLog();//gets the attendencelog

        ArrayList<AttendanceLog> search_attendance_log = new ArrayList<>();//Creates a Array List

        for (AttendanceLog a : attendance_list) {

            if (a.getUnitID().equals(UnitID) && a.getDate().equals(Date)) {

                search_attendance_log.add(a);

            }//Checks whether UnitID equals to UnitID and Date equals to Date

        }

        return search_attendance_log;//Returns the search Log

    }

    public void generateAttendanceReport(String UnitID, String Date, ArrayList<AttendanceLog> search_attendance_log, Lecturer lecturer) {

        int Attendance = 0;//Variable
        double PercentageOccupancy = 0;//Variable

        AttendanceLog attendance_log = search_attendance_log.get(0);//Gets the details from the method

        System.out.println("");

        System.out.println("\t\t\t\t\tAttendance Report");
        System.out.println("Unit        :   " + UnitID);
        System.out.println("Unit Name   :   " + lecturer.getUnitName());
        System.out.println("Room        :   " + lecturer.getRoomNo());
        System.out.println("Name        :   " + lecturer.getRoomName());
        System.out.println("Lecturer    :   " + lecturer.getLecturerName());
        System.out.println("Week#       :   " + attendance_log.getWeekNo());
        System.out.println("Date        :   " + Date);
        //Display the details
        System.out.println("");

        System.out.println("Student No.      Last Name      First Name      Present");

        ArrayList<Student> student_list = Student.getStudents();//display student details

        for (Student student : student_list) {

            String Present = "N";

            for (AttendanceLog search_attendance : search_attendance_log) {

                if (search_attendance.getStudentID() == student.getStudentNo()) {
                    Present = "Y";
                    Attendance++;
                }

            }

            System.out.println(student.getStudentNo() + "          " + student.getLastName() + "     \t    " + student.getFirstName() + "  \t " + Present);

        }

        PercentageOccupancy = ((double) Attendance / (double) lecturer.getRoomCapacity()) * 100;

        System.out.println("");

        System.out.println("Lecturer confirmed attendance: Yes/No");

        System.out.println("Students Enrolled       :   " + lecturer.getNoOfStudentsEntrolled());
        System.out.println("Attendance              :   " + Attendance);
        System.out.println("Room capacity           :   " + lecturer.getRoomCapacity());
        System.out.println("Percentage occupancy    :   " + new DecimalFormat("0.00").format(PercentageOccupancy) + "%");
    }

    public void viewAttendance() {

        ArrayList<AttendanceLog> attendance_log = AttendanceLog.getAttendanceLog();

        System.out.println("Room_No      Student_ID      Unit_ID      Lecturer_Name      Date      Week_No");

        for (AttendanceLog attendance : attendance_log) {

            System.out.println(attendance.getRoomNo() + "       " + attendance.getStudentID() + "         " + attendance.getUnitID() + "      " + attendance.getLecturerName() + "       " + attendance.getDate() + "      " + attendance.getWeekNo());

        }

    }

    public ArrayList<String> getAttendanceDate(String UnitID) {
       
        ArrayList<AttendanceLog> attendance_log = AttendanceLog.getAttendanceLog();//Get the attendance log

        ArrayList<String> date_list = new ArrayList<>();//Creates a new Array List

        for (AttendanceLog a : attendance_log) {

            if (a.getUnitID().equals(UnitID)) {//UnitID equals entered UnitID

                boolean DateAlreadyAdded = false;

                for (String date : date_list) {

                    if (a.getDate().equals(date)) {
                        //don't add the same date
                        DateAlreadyAdded = true;
                        break;
                    }

                }

                if (!DateAlreadyAdded) {

                    date_list.add(a.getDate());//Add the date

                }

            }

        }

        return date_list;//returns date_list

    }

    public  void printAttendanceReport(String UnitID, String Date, ArrayList<AttendanceLog> search_attendance_log, Lecturer lecturer) {
        try {//Write the file using buffered writer
            BufferedWriter bw = new BufferedWriter(new FileWriter("E:\\CW2_UoG\\src\\Data\\reportgenerated.txt"));
            int Attendance = 0;
            double PercentageOccupancy = 0;

            AttendanceLog attendance_log = search_attendance_log.get(0);

           
            bw.write("\t\t\t\t\t\n Attendance Report");
            bw.newLine();
            bw.write("Unit        :   " + UnitID);
            bw.newLine();
            bw.write("Unit Name   :   " + lecturer.getUnitName());
            bw.newLine();
            bw.write("Room        :   " + lecturer.getRoomNo());
            bw.newLine();
            bw.write("Name        :   " + lecturer.getRoomName());
            bw.newLine();
            bw.write("Lecturer    :   " + lecturer.getLecturerName());
            bw.newLine();
            bw.write("Week#       :   " + attendance_log.getWeekNo());
            bw.newLine();
            bw.write("Date        :   " + Date);
            bw.newLine();

          

            bw.write("Student No.      Last Name      First Name      Present");
            bw.newLine();

            ArrayList<Student> student_list = Student.getStudents();

            for (Student student : student_list) {

                String Present = "N";

                for (AttendanceLog search_attendance : search_attendance_log) {

                    if (search_attendance.getStudentID() == student.getStudentNo()) {
                        Present = "Y";
                        Attendance++;
                    }

                }
                
                bw.write(student.getStudentNo() + "          " + student.getLastName() + "     \t    " + student.getFirstName() + "  \t " + Present);
                bw.newLine();
            }

            PercentageOccupancy = ((double) Attendance / (double) lecturer.getRoomCapacity()) * 100;

            

            bw.write("Lecturer confirmed attendance: Yes/No");
            bw.newLine();

            bw.write("Students Enrolled       :   " + lecturer.getNoOfStudentsEntrolled());
            bw.newLine();
            bw.write("Attendance              :   " + Attendance);
            bw.newLine();
            bw.write("Room capacity           :   " + lecturer.getRoomCapacity());
            bw.newLine();
            bw.write("Percentage occupancy    :   " + new DecimalFormat("0.00").format(PercentageOccupancy) + "%");
            bw.newLine();
            bw.flush();
            bw.close();
        } catch (Exception e) {
            System.out.println("File not Found !");
        }
        
    }
}
