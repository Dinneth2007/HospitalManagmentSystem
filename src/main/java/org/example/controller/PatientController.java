package org.example.controller;



import org.example.db.DBConnection;
import org.example.model.Patient;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PatientController {

    Connection connection;

    {
        try {
            connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean add(Patient patient) throws SQLException {
        String SQL = "Insert into Patient(name,age,gender,contact_details,emergency_contact,medical_history) Values(?,?,?,?,?,?)";
        PreparedStatement stm = connection.prepareStatement(SQL);

        stm.setObject(1, patient.getName());
        stm.setObject(2, patient.getAge());
        stm.setObject(3, patient.getGender());
        stm.setObject(4, patient.getContact_details());
        stm.setObject(5, patient.getEmergency_contact());
        stm.setObject(6, patient.getMedical_history());
        int res=stm.executeUpdate();
        if(res>0){
            return true;
        }else{
            return false;
        }

    }

    public boolean update(Patient patient) throws SQLException {
        String SQL = "UPDATE PATIENT SET NAME=?,AGE=?,GENDER=?,CONTACT_DETAILS=?,EMERGENCY_CONTACT=?,MEDICAL_HISTORY=? WHERE PATIENT_ID=?";
        PreparedStatement stm = connection.prepareStatement(SQL);
        stm.setObject(1,patient.getName());
        stm.setObject(2, patient.getAge());
        stm.setObject(3, patient.getGender());
        stm.setObject(4, patient.getContact_details());
        stm.setObject(5, patient.getEmergency_contact());
        stm.setObject(6, patient.getMedical_history());
        stm.setObject(7, patient.getId());
        int res=stm.executeUpdate();


        return res>0?true:false;
    }

    public boolean delete(int id) throws SQLException {
        String SQL = "DELETE FROM PATIENT WHERE PATIENT_ID=?";
        PreparedStatement stm = connection.prepareStatement(SQL);
        stm.setObject(1,id);

        int res=stm.executeUpdate();


        return res>0?true:false;
    }
    public Patient serachById(String ID) throws SQLException {
        String SQL = "Select * From Patient WHERE patient_id="+ID;
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery(SQL);
        while (rst.next()) {
            int id = Integer.parseInt(rst.getString("patient_id"));
            String name = rst.getString("name");
            int age = Integer.parseInt(rst.getString("age"));
            String gender = rst.getString("gender");
            String contact_details = rst.getString("contact_details");
            String emergency_ctct= rst.getString("emergency_contact");
            String medical_history = rst.getString("medical_history");
            return new Patient(id,name,age,gender,contact_details,emergency_ctct,medical_history);
        }
        return null;
    }
    public java.util.List<Patient> getPatients() throws SQLException {
        List<Patient> PatientList=new ArrayList<>();
        String SQL = "Select * From Patient";
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery(SQL);
        while (rst.next()) {
            int id = Integer.parseInt(rst.getString("patient_id"));
            String name = rst.getString("name");
           int age = Integer.parseInt(rst.getString("age"));
           String gender = rst.getString("gender");
            String contact_details = rst.getString("contact_details");
            String emergency_ctct= rst.getString("emergency_contact");
            String medical_history = rst.getString("medical_history");
            PatientList.add(new Patient(id,name,age,gender,contact_details,emergency_ctct,medical_history));
        }
        return PatientList;
    }
    
}
