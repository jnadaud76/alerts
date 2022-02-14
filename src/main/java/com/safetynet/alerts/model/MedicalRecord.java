package com.safetynet.alerts.model;

import java.util.Objects;
import java.util.Set;


public class MedicalRecord {

    private String firstName;
    private String lastName;
    private String birthdate;
    private Set<String> medications;
    private Set<String> allergies;

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

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(final String birthdateParam) {
        this.birthdate = birthdateParam;
    }

    public Set<String> getMedications() {
        return medications;
    }

    public void setMedications(final Set<String> medicationsParam) {
        this.medications = medicationsParam;
    }

    public Set<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(final Set<String> allergiesParam) {
        this.allergies = allergiesParam;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalRecord that = (MedicalRecord) o;
        return firstName.equals(that.firstName)
                && lastName.equals(that.lastName)
                && birthdate.equals(that.birthdate)
                && medications.equals(that.medications)
                && allergies.equals(that.allergies);
    }

    @Override
    public int hashCode() {
        return Objects
                .hash(firstName, lastName, birthdate, medications, allergies);
    }
}
