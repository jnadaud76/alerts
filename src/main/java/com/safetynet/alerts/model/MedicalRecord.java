package com.safetynet.alerts.model;

import java.util.Set;

import lombok.Data;

@Data
public class MedicalRecord {

    private String firstName;
    private String lastName;
    private String birthdate;
    private Set<String> medications;
    private Set<String> allergies;

}
