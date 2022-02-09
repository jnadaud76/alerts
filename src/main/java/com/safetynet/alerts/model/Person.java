package com.safetynet.alerts.model;


import java.util.Objects;

public class Person {


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return zip == person.zip && firstName.equals(person.firstName) && lastName.equals(person.lastName) && address.equals(person.address) && city.equals(person.city) && phone.equals(person.phone) && email.equals(person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, address, city, zip, phone, email);
    }
}
