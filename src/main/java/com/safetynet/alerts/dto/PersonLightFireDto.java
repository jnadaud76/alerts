package com.safetynet.alerts.dto;

import java.util.Set;

public class PersonLightFireDto {

    private String lastName;
    private String phone;
    private long age;
    private Set<String> medications;
    private Set<String> allergies;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastNameParam) {
        this.lastName = lastNameParam;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phoneParam) {
        this.phone = phoneParam;
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

}
