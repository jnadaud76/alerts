package com.safetynet.alerts.dto;

import java.util.Set;


public class PersonInfoDto {
    /**
     * Person's last name.
     */
    private String lastName;
    /**
     * Person's home address.
     */
    private String address;
    /**
     * Person's age.
     */
    private long age;
    /**
     * Person's email address.
     */
    private String email;
    /**
     * Person's medications (type of medication and posology).
     */
    private Set<String> medications;
    /**
     * Person's allergies.
     */
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
