package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Patient {
    private int id;
    private String name;
    private int age;
    private String gender;
    private String contact_details;
    private String emergency_contact;
    private String medical_history;

    public Patient(String name, int age, String gender, String contact_details, String emergency_contact, String medical_history) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.contact_details = contact_details;
        this.emergency_contact = emergency_contact;
        this.medical_history = medical_history;
    }
}
