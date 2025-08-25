/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.*;
import db.Display_Personnel;
import java.util.Scanner;

/**
 *
 * @author fotis
 */
public class Main {
//    try {
//   Class.forName("com.mysql.cj.jdbc.Driver");
//}
//catch(ClassNotFoundException ex) {
//   System.out.println("Error: unable to load driver class!");
//   System.exit(1);
//}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = new String("jdbc:mysql://localhost");
        String databaseName = new String("cs360db");
        int port = 3306;
        String username = new String("root");
        String password = new String("");
        Connection con = DriverManager.getConnection(
                url + ":" + port + "/" + databaseName + "?characterEncoding=UTF-8", username, password);
        
        
          
            Statement stmt = con.createStatement();

            String insertTEP_Name = new String(
                    "INSERT INTO `TEP`(`TEP_Name`) VALUES ( 'HY-360' )"
            );

            stmt.executeUpdate(insertTEP_Name);
           
         ResultSet rs ;


         String queryGet_ER_name = new String(
                "SELECT `TEP_Name` FROM `TEP` WHERE 1 "
            );
            
          rs = stmt.executeQuery(queryGet_ER_name);
          
            String ER_name = new String();
         
            while (rs.next()) {
                ER_name = rs.getString("TEP_Name");

            }

       

        System.out.println("Welcome to "+ER_name+" ER.\nIf you are an employee press (1)\nIf you are a patient press (2)\n If you want to exit the program press (3)");
        Scanner sc = new Scanner(System.in);

        int selectAction = sc.nextInt();

        while (selectAction != 3) {
            switch (selectAction) {
                case 1: // display the personnel of the er room.

                    System.out.println("To show available personnel press (1), for covid Reports press (2), for stats press (3)");
                    int selectEmployeeAction = sc.nextInt();

                    switch (selectEmployeeAction) {
                        case 1:
                            Display_Personnel.display_Doctors(con);
                            Display_Personnel.display_Nurses(con);
                            Display_Personnel.display_Employees(con);

                            System.out.println("\nIf you are an employee press (1)\nIf you are a patient press (2)\n If you want to exit the program press (3)");
                            selectAction = sc.nextInt();
                            break;
                        case 2:
                            Covid_Report covid_report = new Covid_Report();
                            covid_report.show_Covid_Patients(con);

                            System.out.println("\nIf you are an employee press (1)\nIf you are a patient press (2)\n If you want to exit the program press (3)");
                            selectAction = sc.nextInt();

                            break;
                        case 3:
                            Stats stats = new Stats();
                            stats.show_Stats(con);

                            System.out.println("\nIf you are an employee press (1)\nIf you are a patient press (2)\n If you want to exit the program press (3)");
                            selectAction = sc.nextInt();
                            break;
                    }

                    break;
                case 2: //create a new patient
                    Insert_Patient newPatient = new Insert_Patient();
                    newPatient.check_if_Patient_exists(con);
                    newPatient.insert_Patient(con);

                    System.out.println("\nIf you are an employee press (1)\nIf you are a patient press (2)\n If you want to exit the program press (3)");
                    selectAction = sc.nextInt();
                    break;

            }

        }



        con.close();

    }

}
