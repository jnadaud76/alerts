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

    public void setLastName(String lastNameParam) {
        this.lastName = lastNameParam;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phoneParam) {
        this.phone = phoneParam;
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

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonLightFireDto that = (PersonLightFireDto) o;
        return age == that.age && lastName.equals(that.lastName) && phone.equals(that.phone) && medications.equals(that.medications) && allergies.equals(that.allergies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, phone, age, medications, allergies);
    }*/
}
