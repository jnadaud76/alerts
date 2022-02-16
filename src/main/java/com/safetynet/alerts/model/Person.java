package com.safetynet.alerts.model;


import java.util.Objects;

public class Person {
    /**
     * Person's first name.
     */
    private String firstName;
    /**
     * Person's last name.
     */
    private String lastName;
    /**
     * Person's home address.
     */
    private String address;
    /**
     * Person's home city.
     */
    private String city;
    /**
     * Person's home zip code.
     */
    private int zip;
    /**
     * Person's home phone number.
     */
    private String phone;
    /**
     * Person's email address.
     */
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return zip == person.zip && firstName
                .equals(person.firstName) && lastName.equals(person.lastName)
                && address.equals(person.address) && city.equals(person.city)
                && phone.equals(person.phone) && email.equals(person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, address, city, zip, phone, email);
    }
}
