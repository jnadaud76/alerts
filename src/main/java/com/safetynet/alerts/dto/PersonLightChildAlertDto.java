package com.safetynet.alerts.dto;


public class PersonLightChildAlertDto {
    private String firstName;
    private String lastName;
    private long age;

   /* public PersonLightChildAlertDto() {
    }

    public PersonLightChildAlertDto(String firstName, String lastName, long age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
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

    public long getAge() {
        return age;
    }

    public void setAge(long ageParam) {
        this.age = ageParam;
    }
}
