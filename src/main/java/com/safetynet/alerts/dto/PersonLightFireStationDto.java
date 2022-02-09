package com.safetynet.alerts.dto;


public class PersonLightFireStationDto {
    private String firstName;
    private String lastName;
    private String address;
    private String phone;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String addressParam) {
        this.address = addressParam;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phoneParam) {
        this.phone = phoneParam;
    }
}
