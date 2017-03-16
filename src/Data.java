/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author Lakshan
 */
public class Data {

    public static ArrayList<String> readData(String FileName) {

        ArrayList<String> lines = new ArrayList<>();
        //Creates a Array  List
        try {

            BufferedReader br = new BufferedReader(new FileReader("E:\\CW2_UoG\\src\\Data\\" + FileName));//Gets to the source folser
            String line;
            //Variable the store the value read
            while ((line = br.readLine()) != null) {

                lines.add(line);

            }
            //Reads the lines until null and adds to the array list

            lines.remove(0);
            //Remove the [0] index(ex:Student No/Last Name .....)
        } catch (Exception e) {

            System.out.println("File Not Found");//Exception Hanlding for File not found
        }

        return lines;//Returns the Array List

    }
}
