package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Appointment {
    private int doctor_id;
    private int patient_id;
    private int appointment_id;
    private String appointment_date;
    private String time;

    public Appointment(int patient_id, int doctor_id, String appointment_date, String time) {
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.appointment_date = appointment_date;
        this.time = time;
    }
}
