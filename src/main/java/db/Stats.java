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
public class Stats {

    public void show_Stats(Connection con) throws SQLException {

        List<String> DiagnosisList = new ArrayList();
        List<String> MedicationsList = new ArrayList();
        List<String> TestsList = new ArrayList();

        String Diagnosis = new String();
        String Medication = new String();
        String Test = new String();

        Statement stmt = con.createStatement();

        String queryLog = new String("SELECT `AMKA`, `Date_added`, `Diagnosis`, `Tests`, `Treatment` FROM `log` WHERE 1");

        ResultSet rs = stmt.executeQuery(queryLog);

        while (rs.next()) {
            Diagnosis = rs.getString("Diagnosis");
            Medication = rs.getString("Treatment");
            Test = rs.getString("Tests");

            DiagnosisList.add(Diagnosis);
            MedicationsList.add(Medication);
            TestsList.add(Test);
        }
        System.out.println("Total diagnosis: " + DiagnosisList.size());
        for (int i = 0; i < DiagnosisList.size(); i++) {
            System.out.println(DiagnosisList.get(i));

        }

        System.out.println("\nTotal medications:" + MedicationsList.size());
        for (int i = 0; i < MedicationsList.size(); i++) {
            System.out.println(MedicationsList.get(i));

        }

        System.out.println("\nTotal tests:" + TestsList.size());
        for (int i = 0; i < TestsList.size(); i++) {
            System.out.println(TestsList.get(i));

        }


    }
}
