package com.safetynet.alerts.dto;

import java.util.Set;


public class MedicalRecordFullDto {
    private String firstName;
    private String lastName;
    private String birthdate;
    private Set<String> medications;
    private Set<String> allergies;

   /* public MedicalRecordFullDto() {
    }

    public MedicalRecordFullDto(String firstName, String lastName, String birthdate, Set<String> medications, Set<String> allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.medications = medications;
        this.allergies = allergies;
    }*/

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstNameParam) {
        this.firstName = firstNameParam;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastNameParam) {
        this.lastName = lastNameParam;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdateParam) {
        this.birthdate = birthdateParam;
    }

    public Set<String> getMedications() {
        return medications;
    }

    public void setMedications(Set<String> medicationsParam) {
        this.medications = medicationsParam;
    }

    public Set<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(Set<String> allergiesParam) {
        this.allergies = allergiesParam;
    }
}
