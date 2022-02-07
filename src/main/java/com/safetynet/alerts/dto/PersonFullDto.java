package com.safetynet.alerts.dto;

public class PersonFullDto {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private int zip;
    private String phone;
    private String email;

   /* public PersonFullDto() {
    }

    public PersonFullDto(String firstName, String lastName, String address, String city, int zip, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String addressParam) {
        this.address = addressParam;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String cityParam) {
        this.city = cityParam;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zipParam) {
        this.zip = zipParam;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phoneParam) {
        this.phone = phoneParam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String emailParam) {
        this.email = emailParam;
    }
}
