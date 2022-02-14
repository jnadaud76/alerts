package com.safetynet.alerts.dto;

public class PersonFullDto {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private int zip;
    private String phone;
    private String email;

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

    public String getCity() {
        return city;
    }

    public void setCity(final String cityParam) {
        this.city = cityParam;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(final int zipParam) {
        this.zip = zipParam;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phoneParam) {
        this.phone = phoneParam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String emailParam) {
        this.email = emailParam;
    }
}
