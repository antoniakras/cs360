/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.*;
import java.util.*;

/**
 *
 * @author fotis
 */
public class Examination {


    public void fill_symptoms(Connection con , String AMKA) throws SQLException {
        
        Scanner sc = new Scanner(System.in);

        Statement stmt = con.createStatement();
        ResultSet rs ;
  
        String symptom_input = new String();
        
        System.out.println("Insert symptom:");
        symptom_input = sc.nextLine();
        
        List<String> Doctor = new ArrayList<>();
        String Treatment = new String ();
        
        if((symptom_input.equals("Cardiac Arrest") ) || ((symptom_input.equals("tachycardia") )) ){
            
            Doctor = find_doctor(con,"Cardiologist");
            
            if(symptom_input.equals("Cardiac Arrest")){
                Treatment="Medication";
            }else{
                Treatment="Catheter ablation";
            }
          
                   
        }else if((symptom_input.equals("Epileptic Seizure") ) || ((symptom_input.equals("stroke") )) ){
            
            Doctor = find_doctor(con,"Neurologist");
            
            Treatment="Medication";
            
        }else if(symptom_input.equals("food allergy")){
            
            Doctor = find_doctor(con,"Allergist");
            
            Treatment="Medication";
            
        }else if((symptom_input.equals("infections") ) || ((symptom_input.equals("pain reliever") )) || ((symptom_input.equals("Covid symptoms") )) ){
            
             Doctor = find_doctor(con,"Pathologist");
            
            if (!(symptom_input.equals("Covid symptoms"))) {
                
                Treatment="Medication";
                
            }else{
                
                Treatment="Patience";
            }
            
        }else if((symptom_input.equals("Dressing Wounds") ) || ((symptom_input.equals("surgery/chronic pain") )) || ((symptom_input.equals("topical surgery") )) ){
            
            Doctor = find_doctor(con,"Surgeon");
            
            if(symptom_input.equals("Dressing Wounds")){
                Treatment="Stitches";
            }else{
                Treatment="Surgery";
            }
        
        }
        
        System.out.println("Your doctor is: " + Doctor);
        
        
            String queryDiagnosis = new String(
               "SELECT `Disease`, `Symptom_type` FROM `diagnosis` WHERE Symptom_type= '" + symptom_input + "' "
            );
            
         rs = stmt.executeQuery(queryDiagnosis);
         
         String Diagnosis = new String();
         
            while (rs.next()) {
                Diagnosis = rs.getString("Disease");

            }
            
            System.out.println("Your diagnosis is: "+Diagnosis);
        
        
        String queryMeds = new String(
                "SELECT `Milligrams`, `Medicine_Name`, `Medicine_Type`, `Disease`, `Prescriptions` FROM `medication` WHERE Prescriptions= '" + symptom_input + "' "
            );
            
         rs = stmt.executeQuery(queryMeds);
         
 
          String medication = new String();
          int ml = 0;
                
          
            while (rs.next()) {
                medication = rs.getString("Medicine_Name");
                ml = rs.getInt("Milligrams");

            }
         if((!medication.isEmpty()) && (ml!=0)){                
        System.out.println("Medicine Prescription: " + medication);
        System.out.println("Dosage :" + ml);
        
         }
        
           
         String queryTests = new String(
                "SELECT `test_type`, `symptom_type` FROM `test` WHERE symptom_type= '" + symptom_input + "' "
            );
            
         rs = stmt.executeQuery(queryTests);
        
        List<String> tests_List = new ArrayList<>();
        
            while (rs.next()) {
            String test = rs.getString("test_type");
            tests_List.add(test);
        }
        
        if(!tests_List.isEmpty()){
        System.out.println("Test to take: " + tests_List);
        Take_Tests obj = new Take_Tests();
        obj.Have_test(tests_List,AMKA,Treatment,Diagnosis,con);
        }else{
            
            
          Update_log log_update = new Update_log();
          log_update.update("No tests done", AMKA, Treatment, Diagnosis ,con);
          Rexamination new_rexamination = new Rexamination ();
          new_rexamination.rexamination(con,AMKA);
        }
        
    }
    
    
    public List<String> find_doctor (Connection con , String specialization ) throws SQLException {
        
        List<String> result  = new ArrayList<>() ;
        Statement stmt = con.createStatement();
        ResultSet rs ;
        
         String queryDoctor = new String(
                "SELECT `ID_num`, `Phone_num`, `First_Name`, `Last_Name`, `Address`, `Specialization` FROM `doctor` WHERE Specialization= '"+specialization+"' "
            );
            
             rs = stmt.executeQuery(queryDoctor);
        
            while (rs.next()) {
            String doctor_fName = rs.getString("First_Name");
            String doctor_lName = rs.getString("Last_Name");
            String doctor_specialization = rs.getString("Specialization");
            result.add(doctor_fName);
            result.add(doctor_lName);
            result.add(doctor_specialization);
        }
        
        return result;
    }
      

}
