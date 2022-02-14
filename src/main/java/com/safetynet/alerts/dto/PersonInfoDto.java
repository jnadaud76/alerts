package com.safetynet.alerts.dto;

import java.util.Set;


public class PersonInfoDto {
    private String lastName;
    private String address;
    private long age;
    private String email;
    private Set<String> medications;
    private Set<String> allergies;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastNameParam) {
        this.lastName = lastNameParam;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String addressParam) {
        this.address = addressParam;
    }

    public long getAge() {
        return age;
    }

    public void setAge(final long ageParam) {
        this.age = ageParam;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(final String emailParam) {
        this.email = emailParam;
    }
}
