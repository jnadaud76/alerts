package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.PersonFullDto;
import com.safetynet.alerts.model.Person;

import java.util.Set;

public interface IPersonService {
    /**
     * Get all person.
     *
     * @return set of all person.
     */
    Set<PersonFullDto> getPersons();

    /**
     * Get a person by unique id.
     *
     * @param firstName first part of unique id.
     * @param lastName  second part of unique id.
     * @return a person.
     */
    PersonFullDto getPerson(String firstName, String lastName);

    /**
     * Get a set of person by address.
     *
     * @param address person's address.
     * @return set of person.
     */
    Set<PersonFullDto> getPersonByAddress(String address);

    /**
     * Create a person.
     *
     * @param personParam a person.
     */
    void createPerson(Person personParam);

    /**
     * Update a person.
     *
     * @param personParam a person to modify.
     */
    void updatePerson(Person personParam);

    /**
     * Delete a person by unique id.
     *
     * @param firstName first part of unique id to delete.
     * @param lastName  second part of unique id to delete.
     */
    void deletePerson(String firstName, String lastName);
}
