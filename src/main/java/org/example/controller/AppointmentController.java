package org.example.controller;

import org.example.db.DBConnection;
import org.example.model.Appointment;
import org.example.model.Doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AppointmentController {
        Connection connection;

    public AppointmentController() throws SQLException, ClassNotFoundException {
        connection=DBConnection.getInstance().getConnection();
    }
    public int getLastAppointmentId() throws SQLException {
        String SQL = "Insert into Appointment(patient_id,doctor_id,appointment_date,time) Values(?,?,?,?)";
        PreparedStatement stm = connection.prepareStatement(SQL);
        return 0;
    }
    public boolean addAppointment(Appointment appointment) throws SQLException {
        String SQL = "Insert into Appointment(patient_id,doctor_id,appointment_date,time) Values(?,?,?,?)";
        PreparedStatement stm = connection.prepareStatement(SQL);

        stm.setObject(1, appointment.getPatient_id());
        stm.setObject(2, appointment.getDoctor_id());
        stm.setObject(3, appointment.getAppointment_date());
        stm.setObject(4,appointment.getTime());


        int res=stm.executeUpdate();
        if(res>0){
            return true;
        }else{
            return false;
        }

    }
}
