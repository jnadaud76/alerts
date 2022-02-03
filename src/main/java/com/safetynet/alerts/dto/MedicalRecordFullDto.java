package com.safetynet.alerts.dto;

import java.util.Set;

import lombok.Data;

@Data
public class MedicalRecordFullDto {
    private String firstName;
    private String lastName;
    private String birthdate;
    private Set<String> medications;
    private Set<String> allergies;
}
