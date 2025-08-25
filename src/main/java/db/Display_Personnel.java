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
public class Display_Personnel {

    public static void display_Doctors(Connection con) throws SQLException {

        Statement stmt = con.createStatement();

        String queryDoctors = new String(
                "SELECT `ID_num`, `Phone_num`, `First_Name`, `Last_Name`,"
                + "`Address`, `Specialization` FROM `doctor` WHERE 1"
        );

        ResultSet rs = stmt.executeQuery(queryDoctors);
        System.out.println("DOCTORS:");
        while (rs.next()) {
            String fname = rs.getString("First_Name");
            String lname = rs.getString("Last_Name");
            String specialization = rs.getString("Specialization");

            System.out.println(fname + "||||" + lname + "||||" + specialization);

        }
    }

    public static void display_Nurses(Connection con) throws SQLException {

        Statement stmt = con.createStatement();

        String queryNurses = new String(
                "SELECT `ID_num`, `Phone_num`, `First_Name`, `Last_Name`,"
                + "`Address` FROM `nurse` WHERE 1"
        );

        ResultSet rs = stmt.executeQuery(queryNurses);
        System.out.println("NURSES:");
        while (rs.next()) {
            String fname = rs.getString("First_Name");
            String lname = rs.getString("Last_Name");

            System.out.println(fname + "||||" + lname);

        }

    }

    public static void display_Employees(Connection con) throws SQLException {

        Statement stmt = con.createStatement();

        String queryNurses = new String(
                "SELECT `ID_num`, `Phone_num`, `First_Name`, `Last_Name`,"
                + "`Address` FROM `employee` WHERE 1"
        );

        ResultSet rs = stmt.executeQuery(queryNurses);
        System.out.println("EMPLOYEES:");
        while (rs.next()) {
            String fname = rs.getString("First_Name");
            String lname = rs.getString("Last_Name");

            System.out.println(fname + "||||" + lname);

        }

    }
}
