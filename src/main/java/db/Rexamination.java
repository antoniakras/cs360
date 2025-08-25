/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.*;

/**
 *
 * @author fotis
 */
public class Rexamination {


    public void rexamination(Connection con , String AMKA ) throws SQLException {
        
        ResultSet rs ;
    
        Statement stmt = con.createStatement();


         String queryLog = new String(
                "SELECT `AMKA`, `Date_added`, `Diagnosis`, `Tests`, `Treatment` FROM `log` WHERE AMKA= '"+AMKA+"' "
            );
            
          rs = stmt.executeQuery(queryLog);

         String Diagnosis  = new String();
         
            while (rs.next()) {
                Diagnosis = rs.getString("Diagnosis");

            }
            
            System.out.println("Your diagnosis is:fdsafsa"+Diagnosis+"sdfasR");
            
         if   (Diagnosis.equals("Covid")){
         System.out.println("OK ");
    }
            
        
        if ((Diagnosis.equals(" Heart attack ")) || (Diagnosis.equals(" Covid ")) || (Diagnosis.equals(" Aneurysm  ")) || (Diagnosis.equals(" Gastroenteritis ") )) {
              

              if (!(Diagnosis.equals(" Covid "))) {
                  System.out.println("You have to be hospitalized.");
              }else{
                  System.out.println("You have to be quarantined.STAY AWAY FROM ANYBODY!");

              }
              
          }else{
              
              System.out.println("You are free to go!");
          }
      
        
    }
    
      

}
