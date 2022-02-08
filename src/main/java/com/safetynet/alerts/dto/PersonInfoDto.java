package com.safetynet.alerts.dto;

import java.util.Set;


public class PersonInfoDto {
    private String lastName;
    private String address;
    private long age;
    private String email;
    private Set<String> medications;
    private Set<String> allergies;

   /* public PersonInfoDto() {
    }

    public PersonInfoDto(String firstName, String address, long age, Set<String> medications, Set<String> allergies) {
        this.firstName = firstName;
        this.address = address;
        this.age = age;
        this.medications = medications;
        this.allergies = allergies;
    }*/

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String addressParam) {
        this.address = addressParam;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long ageParam) {
        this.age = ageParam;
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

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
