package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Doctor {
    private int Id;
    private String Name;
    private String Speciality;
    private String Availabilty;
    private String Qualifications;

    public Doctor(String name, String speciality, String availabilty, String qualifications, String contact_details) {
        Name = name;
        Speciality = speciality;
        Availabilty = availabilty;
        Qualifications = qualifications;
        Contact_details = contact_details;
    }

    private String Contact_details;




}
