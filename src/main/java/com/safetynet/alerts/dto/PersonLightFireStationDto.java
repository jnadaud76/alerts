package com.safetynet.alerts.dto;


public class PersonLightFireStationDto {
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
     * Person's home phone number.
     */
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
