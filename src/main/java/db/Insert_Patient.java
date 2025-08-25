/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 *
 * @author fotis
 */
public class Insert_Patient {

    List<String> AMKA_List = new ArrayList<>();
    String AMKA = new String ();

    public void check_if_Patient_exists(Connection con) throws SQLException {

        String queryPatientAMKA = new String(
                "SELECT `AMKA` FROM `patient` WHERE 1"
        );

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(queryPatientAMKA);

        while (rs.next()) {
            AMKA = rs.getString("AMKA");
            AMKA_List.add(AMKA);
        }

    }

    public void insert_Patient(Connection con) throws SQLException {
        Scanner sc = new Scanner(System.in);
        String First_Name = new String();
        String Last_Name = new String();
        String Insurance = new String();
        String Sex = new String();
        String Address = new String();
        String Phone_num = new String();
        String PatientHistory = new String();
        String PatientHistoryDateCreated = new String();
        String PatientLogDateCreated = new String();
        String PatientLogDiagnosis = new String();

        System.out.println("Enter AMKA:\n");
        String AMKA = sc.nextLine();
        //SEARCH AMKA LIST TO CHECK IF PATIENT ALREADY EXISTS.
        if (AMKA_List.contains(AMKA)) {
            System.out.println("Patient already exists.Loading history now....\n\n\n");

            Statement stmt = con.createStatement();
            String queryPatient = new String("SELECT `AMKA`, `First_Name`, `Last_Name`, `Insurance`,"
                    + "`Sex`, `Address`, `Phone_num` FROM `patient` WHERE AMKA = " + AMKA + " ");

            String queryShowPatientHistory = new String("SELECT `AMKA`, `Disease`,"
                    + "`Date_Diagnosed` FROM `history` WHERE AMKA = " + AMKA + " ");

            String queryShowPatientLog = new String("SELECT `AMKA`, `Date_added`, `Diagnosis`,"
                    + "`Tests`, `Treatment` FROM `log` WHERE AMKA = " + AMKA + "");

            ResultSet rs = stmt.executeQuery(queryPatient);

            while (rs.next()) {
                First_Name = rs.getString("First_Name");
                Last_Name = rs.getString("Last_Name");
                Insurance = rs.getString("Insurance");
                Sex = rs.getString("Sex");
                Phone_num = rs.getString("Phone_num");
            }

            System.out.println("First name: " + First_Name + " Last name: " + Last_Name + " Insurance: " + Insurance + " Sex: " + Sex + " Phone: " + Phone_num + "\n\n\n");

            rs = stmt.executeQuery(queryShowPatientHistory);

            while (rs.next()) {
                PatientHistory = rs.getString("Disease");
                PatientHistoryDateCreated = rs.getString("Date_Diagnosed");
            }

            System.out.println("Your diseases are : " + PatientHistory + " and you inserted them at: " + PatientHistoryDateCreated + "\n\n");

            //SHOW LOG
            rs = stmt.executeQuery(queryShowPatientLog);

            while (rs.next()) {
                PatientLogDateCreated = rs.getString("Date_added");
                PatientLogDiagnosis = rs.getString("Diagnosis");

                System.out.println("You have visited us at: " + PatientLogDateCreated + " when you had: " + PatientLogDiagnosis + "\n\n");
            }

        } else {
            System.out.println("Enter  First Name:\n");
            First_Name = sc.nextLine();
            System.out.println("Enter  Last Name:\n");
            Last_Name = sc.nextLine();
            System.out.println("Enter  Insurance:\n");
            Insurance = sc.nextLine();
            System.out.println("Enter  Sex:\n");
            Sex = sc.nextLine();
            System.out.println("Enter  Address:\n");
            Address = sc.nextLine();
            System.out.println("Enter  Phone number:\n");
            Phone_num = sc.nextLine();

            Statement stmt = con.createStatement();

            String insertPatient = new String(
                    "INSERT INTO `patient`(`AMKA`, `First_Name`, `Last_Name`, `Insurance`, `Sex`, `Address`,"
                    + "`Phone_num`) VALUES ( '" + AMKA + "' ,'" + First_Name + "',' " + Last_Name + " ','" + Insurance + "','" + Sex + "','" + Address + "','" + Phone_num + "')"
            );

            stmt.executeUpdate(insertPatient);

            //CREATE LOG  AND DISEASE TABLE
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
            String DateTime = currentDateTime.format(formatter);

//            String insertAMKAtoLOG = new String("INSERT INTO `log`(`AMKA`, `Date_added`) VALUES ('" + AMKA + "','" + DateTime + "' )");
//
//            stmt.executeUpdate(insertAMKAtoLOG);

            System.out.println("Insert your medical history:");
            String medicalHistory = new String();
            medicalHistory = sc.nextLine();

            String insertAMKAtoHISTORY = new String("INSERT INTO `history`(`AMKA`, `Disease`, `Date_Diagnosed`) VALUES ('" + AMKA + "','" + medicalHistory + "','" + DateTime + "')");

            stmt.executeUpdate(insertAMKAtoHISTORY);
            
 
        }
        
         Examination new_examination = new Examination ();
         new_examination.fill_symptoms(con,AMKA);

    }
    
   
}
