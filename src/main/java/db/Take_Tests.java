///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package db;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

///**
// *
// * @author fotis
// */

public class Take_Tests {


    public void Have_test(  List<String> tests_List , String AMKA , String Treatment,String Diagnosis ,Connection con) throws SQLException {
      
        Scanner sc = new Scanner(System.in);
        ResultSet rs ;
        
        System.out.println(Treatment);
        System.out.println(Diagnosis);
        
        
        List<String> nurse = find_nurse(con);
        System.out.println("Your nurse is: " + nurse );
            
      
        //REGISTER REPORT TO LOG:
                
        String Tests="";
        for(String s : tests_List)
        {
            Tests=Tests + s + ",";
        }
        
        Update_log log_update = new Update_log();
        log_update.update(Tests, AMKA, Treatment, Diagnosis ,con);
    
        Statement stmt = con.createStatement();
        
        //EXAM REPORT:
        List<String> Report  = new ArrayList<>();

        
         String queryLog = new String(
                "SELECT `AMKA`, `Date_added`, `Diagnosis`, `Tests`, `Treatment` FROM `log` WHERE AMKA= '"+AMKA+"' "
            );
            
          rs = stmt.executeQuery(queryLog);
        
            while (rs.next()) {
            String log_amka = rs.getString("AMKA");
            String log_date = rs.getString("Date_added");
            String log_diagnosis = rs.getString("Diagnosis");
            String log_tests = rs.getString("Tests");
            String log_treatment = rs.getString("Treatment");
            Report.add(AMKA);
            Report.add(log_date);
            Report.add(log_diagnosis);
            Report.add(log_tests);
            Report.add(log_treatment);
            
            
        }
            
          System.out.println(Report);
          
          Rexamination new_rexamination = new Rexamination ();
          new_rexamination.rexamination(con,AMKA);
                
        
    }
    
     public List<String> find_nurse (Connection con ) throws SQLException {
         
         
        List<String> result  = new ArrayList<>() ;
        Statement stmt = con.createStatement();
        ResultSet rs ;
        

         String queryNurse = new String(
                "SELECT `First_Name`, `Last_Name` FROM `nurse` ORDER BY RAND() LIMIT 1 "
            );
            
        rs = stmt.executeQuery(queryNurse);
        
            while (rs.next()) {
            String nurse_fName = rs.getString("First_Name");
            String nurse_lName = rs.getString("Last_Name");
            result.add(nurse_fName);
            result.add(nurse_lName);
        }
        
        return result;      
     
     }
    
 
}

