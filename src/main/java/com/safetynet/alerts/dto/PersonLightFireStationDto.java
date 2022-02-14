package com.safetynet.alerts.dto;


public class PersonLightFireStationDto {
    private String firstName;
    private String lastName;
    private String address;
    private String phone;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(final String addressParam) {
        this.address = addressParam;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phoneParam) {
        this.phone = phoneParam;
    }
}
