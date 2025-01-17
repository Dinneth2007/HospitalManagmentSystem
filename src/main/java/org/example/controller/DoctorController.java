package org.example.controller;



import org.example.db.DBConnection;
import org.example.model.Doctor;
import org.example.model.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DoctorController {

    Connection connection;

    DoctorController(){
        try {
            connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static int loadNewDoctorId() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT doctor_id FROM Doctor ORDER BY doctor_id DESC LIMIT 1");
        return rst.next() ? rst.getInt("doctor_id")+1 : 0;
    }
    public boolean add(Doctor doc) throws SQLException {
        String SQL = "Insert into Doctor(name,specialty,availability,qualifications,contact_details) Values(?,?,?,?,?)";
        PreparedStatement stm = connection.prepareStatement(SQL);

        stm.setObject(1, "Dr "+doc.getName());
        stm.setObject(2, doc.getSpeciality());
        stm.setObject(3, doc.getAvailabilty());
        stm.setObject(4, doc.getQualifications());
        stm.setObject(5, doc.getContact_details());

        int res=stm.executeUpdate();
        if(res>0){
            return true;
        }else{
            return false;
        }

    }

    public boolean update(Doctor doc) throws SQLException {
        String SQL = "UPDATE DOCTOR SET NAME=?,specialty=?,availability=?,QUALIFICATIONS=?,CONTACT_DETAILS=? WHERE DOCTOR_ID=?";
        PreparedStatement stm = connection.prepareStatement(SQL);

        stm.setObject(1, doc.getName());
        stm.setObject(2, doc.getSpeciality());
        stm.setObject(3, doc.getAvailabilty());
        stm.setObject(4, doc.getQualifications());
        stm.setObject(5, doc.getContact_details());
        stm.setObject(6, doc.getId());
        int res=stm.executeUpdate();


        return res>0?true:false;
    }

    public boolean delete(int id) throws SQLException {
        String SQL = "DELETE FROM DOCTOR WHERE DOCTOR_ID=?";
        PreparedStatement stm = connection.prepareStatement(SQL);
        stm.setObject(1,id);

        int res=stm.executeUpdate();


        return res>0?true:false;
    }
    public Doctor serachById(String ID) throws SQLException {
        String SQL = "Select * From doctor WHERE doctor_id="+ID;
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery(SQL);
        while (rst.next()) {
            int id = Integer.parseInt(rst.getString("doctor_id"));
            String name = rst.getString("name");
            String speciality = rst.getString("specialty");
            String availabilty = rst.getString("availability");
            String contact_details = rst.getString("contact_details");
            String qualifications= rst.getString("qualifications");

            return new Doctor(id,name,speciality,availabilty,qualifications,contact_details);
        }
        return null;
    }
    public List<Doctor> getDoctors() throws SQLException {
        List<Doctor> DoctorList=new ArrayList<>();
        String SQL = "Select * From Doctor";
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery(SQL);
        while (rst.next()) {
            int id = Integer.parseInt(rst.getString("doctor_id"));
            String name = rst.getString("name");
           String speciality = (rst.getString("speciality"));
           String availablity = rst.getString("availability");
            String quali = rst.getString("qualificatons");
            String emergency_ctct= rst.getString("contact_details");

            DoctorList.add(new Doctor(id,name,speciality,availablity,quali,emergency_ctct));
        }
        return DoctorList;
    }
    
}
