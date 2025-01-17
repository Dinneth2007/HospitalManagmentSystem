package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class Appointment {
    private int doctor_id;
    private int patient_id;
    private int appointment_id;
    private LocalDate appointment_date;
    private String time;

    public Appointment(int patient_id, int doctor_id, LocalDate appointment_date, String time) {
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.appointment_date = appointment_date;
        this.time = time;
    }
}
