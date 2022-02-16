package com.safetynet.alerts.dto;

import java.util.Set;


public class MedicalRecordFullDto {
    /**
     * Person's first name.
     */
    private String firstName;
    /**
     * Person's last name.
     */
    private String lastName;
    /**
     * Person's birthdate.
     */
    private String birthdate;
    /**
     * Person's medications (type of medication and posology).
     */
    private Set<String> medications;
    /**
     * Person's allergies.
     */
    private Set<String> allergies;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstNameParam) {
        this.firstName = firstNameParam;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastNameParam) {
        this.lastName = lastNameParam;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(final String birthdateParam) {
        this.birthdate = birthdateParam;
    }

    public Set<String> getMedications() {
        return medications;
    }

    public void setMedications(final Set<String> medicationsParam) {
        this.medications = medicationsParam;
    }

    public Set<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(final Set<String> allergiesParam) {
        this.allergies = allergiesParam;
    }
}
