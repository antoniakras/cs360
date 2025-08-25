/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fotis
 */
public class Covid_Report {

    public void show_Covid_Patients(Connection con) throws SQLException {

        String queryCovidPatients = new String("SELECT `AMKA` FROM `log` WHERE Diagnosis = ' Covid ' ");

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(queryCovidPatients);
        List<String> AMKA_List = new ArrayList();
        String AMKA = new String();

        String First_Name = new String();
        String Last_Name = new String();
        String Insurance = new String();
        String Sex = new String();
        String Phone_num = new String();
        String PatientHistory = new String();
        String PatientHistoryDateCreated = new String();


        while (rs.next()) {
            AMKA = rs.getString("AMKA");
            AMKA_List.add(AMKA);
        }

        for (int i = 0; i < AMKA_List.size(); i++) {

            String queryPatient = new String("SELECT `AMKA`, `First_Name`, `Last_Name`, `Insurance`,"
                    + "`Sex`, `Address`, `Phone_num` FROM `patient` WHERE AMKA = " + AMKA_List.get(i) + " ");

            String queryShowPatientHistory = new String("SELECT `AMKA`, `Disease`,"
                    + "`Date_Diagnosed` FROM `history` WHERE AMKA = " + AMKA_List.get(i) + " ");


            rs = stmt.executeQuery(queryPatient);

            while (rs.next()) {
                First_Name = rs.getString("First_Name");
                Last_Name = rs.getString("Last_Name");
                Insurance = rs.getString("Insurance");
                Sex = rs.getString("Sex");
                Phone_num = rs.getString("Phone_num");
            }

            System.out.println("\nFirst name: " + First_Name + " Last name: " + Last_Name + " Insurance: " + Insurance + " Sex: " + Sex + " Phone: " + Phone_num + "\n\n");

            rs = stmt.executeQuery(queryShowPatientHistory);

            while (rs.next()) {
                PatientHistory = rs.getString("Disease");
                PatientHistoryDateCreated = rs.getString("Date_Diagnosed");
            }

            System.out.println("Your diseases are : " + PatientHistory + " and you inserted them at: " + PatientHistoryDateCreated + "\n\n");

        }

        System.out.println("Total patients with covid :" + AMKA_List.size());
    }

}
