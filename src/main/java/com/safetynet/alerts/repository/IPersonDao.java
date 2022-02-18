package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.Person;

import java.util.Set;

public interface IPersonDao {
    Set<Person> getPersons();

    void setPersons(final Set<Person> personsParam);

    /**
     * CRUD method which find all person.
     *
     * @return all person.
     */
    Set<Person> findAll();

    /**
     * CRUD method which find a person from unique id.
     *
     * @param firstName first part of unique id.
     * @param lastName  second part of unique id.
     * @return a person.
     */
    Person findById(String firstName, String lastName);


    /**
     * CRUD method which find a set of person from an address.
     *
     * @param address a home address.
     * @return a set of people found.
     */
    Set<Person> findByAddress(String address);

    /**
     * CRUD method which update a person.
     *
     * @param personParam a person.
     */
    void update(Person personParam);

    /**
     * CRUD method which save a person.
     *
     * @param personParam a person.
     */
    void save(Person personParam);

    /**
     * CRUD method which delete a person from unique id.
     *
     * @param firstName first part of unique id.
     * @param lastName  second part of unique id.
     */
    void deleteById(String firstName, String lastName);


}
