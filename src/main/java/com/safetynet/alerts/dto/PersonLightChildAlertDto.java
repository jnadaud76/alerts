package com.safetynet.alerts.dto;


public class PersonLightChildAlertDto {
    private String firstName;
    private String lastName;
    private long age;

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

    public long getAge() {
        return age;
    }

    public void setAge(final long ageParam) {
        this.age = ageParam;
    }
}
