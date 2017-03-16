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
public class Lecturer {

    private String UnitID;
    private String UnitName;
    private String RoomNo;
    private String RoomName;
    private String LecturerName;
    private int RoomCapacity;
    private int NoOfStudentsEntrolled;

    public String getUnitID() {
        return UnitID;
    }

    public void setUnitID(String UnitID) {
        this.UnitID = UnitID;
    }

    public String getUnitName() {
        return UnitName;
    }

    public void setUnitName(String UnitName) {
        this.UnitName = UnitName;
    }

    public String getRoomNo() {
        return RoomNo;
    }

    public void setRoomNo(String RoomNo) {
        this.RoomNo = RoomNo;
    }

    public String getRoomName() {
        return RoomName;
    }

    public void setRoomName(String RoomName) {
        this.RoomName = RoomName;
    }

    public String getLecturerName() {
        return LecturerName;
    }

    public void setLecturerName(String LecturerName) {
        this.LecturerName = LecturerName;
    }

    public int getRoomCapacity() {
        return RoomCapacity;
    }

    public void setRoomCapacity(int RoomCapacity) {
        this.RoomCapacity = RoomCapacity;
    }

    public int getNoOfStudentsEntrolled() {
        return NoOfStudentsEntrolled;
    }

    public void setNoOfStudentsEntrolled(int NoOfStudentsEntrolled) {
        this.NoOfStudentsEntrolled = NoOfStudentsEntrolled;
    }

    public static void main(String[] args) {
        //Gets the details
     for (Lecturer l : getLecturers()) {
     System.out.print(l.getUnitID());
     System.out.print(l.getUnitName());
     System.out.print(l.getRoomNo());
     System.out.print(l.getRoomName());
     System.out.print(l.getLecturerName());
     System.out.print(l.getRoomCapacity());
     System.out.print(l.getNoOfStudentsEntrolled());
            
     System.out.println("");
     }

     }
    public static ArrayList<Lecturer> getLecturers() {

        ArrayList<Lecturer> lecturer_list = new ArrayList<>();//Creates a new Array List

        for (String line : Data.readData("lecturer.txt")) {//Read the data file

            StringTokenizer token = new StringTokenizer(line, ";");//Tokenizers from ";"

            Lecturer s = new Lecturer();

            while (token.hasMoreTokens()) {

                s.setUnitID(token.nextToken().trim());
                s.setUnitName(token.nextToken().trim());
                s.setRoomNo(token.nextToken().trim());
                s.setRoomName(token.nextToken().trim());
                s.setLecturerName(token.nextToken().trim());
                s.setRoomCapacity(Integer.parseInt(token.nextToken().trim()));
                s.setNoOfStudentsEntrolled(Integer.parseInt(token.nextToken().trim()));

                lecturer_list.add(s);

            }//Adds to the array list
        }

        return lecturer_list;

    }
    public void viewLecturers() {

        ArrayList<Lecturer> lecturer_details = Lecturer.getLecturers();

        System.out.println("Unit ID      Unit Name     Room No    Room Name      Lecturer Name      Room Capacity     No. of Students Enrolled    ");

        for (Lecturer info : lecturer_details) {

            System.out.println(info.getUnitID()+ "    ;   " + info.getUnitName()+ "    ;    " + info.getRoomNo()+"    ;   " + info.getRoomName()+"     ;   "+ info.getLecturerName()+"        ;   "+ info.getRoomCapacity()+"        ;         "+ info.getNoOfStudentsEntrolled());

        }
        //Gets the details from the text file and display
    }
    public void addLecturer(String UnitID, String UnitName, String RoomNo, String RoomName, String LecturerName, int RoomCapacity, int NoOfStudentsEnrolled){ 
    
        Lecturer s = new Lecturer();
        //Sets the inputted values
        s.setUnitID(UnitID);
        s.setUnitName(UnitName);
        s.setRoomNo(RoomNo);
        s.setRoomName(RoomName);
        s.setLecturerName(LecturerName);
        s.setRoomCapacity(RoomCapacity);
        s.setNoOfStudentsEntrolled(NoOfStudentsEntrolled);
    
        ArrayList<Lecturer> lecturer_list = getLecturers();
        lecturer_list.add(s);//adds the exsisting array list

        Lecturer.updateLecturer("lecturer.txt", lecturer_list);//updates the text file
    
    
    
    }
    public static void updateLecturer(String FileName, ArrayList<Lecturer> lecturer_list) {

        try {

            ArrayList<String> lines = new ArrayList<>();//Creates a new Array List

            BufferedReader br = new BufferedReader(new FileReader("E:\\CW2_UoG\\src\\Data\\" + FileName));//Reads the File
            String line;//Creates a Variable to Store the read Lines

            while ((line = br.readLine()) != null) {

                lines.add(line);
                //Read until null
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter("E:\\CW2_UoG\\src\\Data\\" + FileName));//Writes Back the File

            String updated_content = lines.get(0) + System.lineSeparator();//Uses line sperator to go from line to line

            for (int i = 0; i < lecturer_list.size(); i++) {

               Lecturer lec = lecturer_list.get(i);
               updated_content += lec.getUnitID()+ "  ; " + lec.getUnitName() + "  ; " + lec.getRoomNo()+"  ; "+ lec.getRoomName()+" ; " + lec.getLecturerName()+"  ;" + lec.getRoomCapacity()+"  ;" + lec.getNoOfStudentsEntrolled();
               
               //Writes the data line by line 
                if (i != lecturer_list.size()-1) {//If it not the last element element
                    updated_content += System.lineSeparator();
                }

            }

            bw.write(updated_content);
            bw.flush();
            bw.close();

        } catch (Exception e) {

            System.out.println("File Not Found");
        }

    }

    public Lecturer searchLecturer(String UnitID) {

        Lecturer lecturer = null;

        ArrayList<Lecturer> lecturer_list = Lecturer.getLecturers();//gets the details

        for (Lecturer l : lecturer_list) {

            if (l.getUnitID().equals(UnitID)) {//if unit id equals
                lecturer = l;
                break;
                //object = to unit id
            }
        }

        return lecturer;//returns the object with the enterd unit id

    }
     public static ArrayList<Lecturer> editsearchLecturer(String UnitID) {

        ArrayList<Lecturer> lecturer_details = Lecturer.getLecturers();//Gets  details

        ArrayList<Lecturer> search_lecturerDetails = new ArrayList<>();//Creates a new Array List

        for (Lecturer a : lecturer_details) {

            if (a.getUnitID().equals(UnitID)) {//If StudentNo matches

                search_lecturerDetails.add(a);//Add the details
            }
        }
        return search_lecturerDetails;
    }

  

}
