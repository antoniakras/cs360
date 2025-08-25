/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

///**
// *
// * @author fotis
// */

public class Update_log {


    public void update(  String tests , String AMKA , String Treatment,String Diagnosis ,Connection con) throws SQLException {
        
        
         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
         LocalDateTime currentDateTime = LocalDateTime.now();
         DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
         String DateTime = currentDateTime.format(formatter);
         
            
            Statement stmt = con.createStatement();

            String insertPatient = new String(
                    "INSERT INTO `log`(`AMKA`, `Date_added`, `Diagnosis`, `Tests`, `Treatment`) VALUES ( '" + AMKA + "' ,'" + DateTime + "',' " + Diagnosis + " ','" + tests + "','"+Treatment+"')"
            );

          stmt.executeUpdate(insertPatient);
        
    }
    }
