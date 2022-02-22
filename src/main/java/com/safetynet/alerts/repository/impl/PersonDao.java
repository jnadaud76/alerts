package com.safetynet.alerts.repository.impl;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.IPersonDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

import java.util.Set;


@Repository
public class PersonDao implements IPersonDao {
    /**
     * @see Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonDao.class);
    /**
     * Set of person extract from data source.
     */
    private Set<Person> persons = new HashSet<>();

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(final Set<Person> personsParam) {
        this.persons = personsParam;
    }

    /**
     * CRUD method which find all person.
     *
     * @return all person.
     */
    public Set<Person> findAll() {
        return this.persons;
    }

    /**
     * CRUD method which find a person from unique id.
     *
     * @param firstName first part of unique id.
     * @param lastName  second part of unique id.
     * @return the person sought if present in the set or null otherwise.
     */
    public Person findById(final String firstName, final String lastName) {
        Person person = null;
        for (Person p : persons) {
            if (p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)) {
                person = p;
                break;
            }
        }
        return person;
    }

    /**
     * CRUD method which find a set of person from an address.
     *
     * @param address a home address.
     * @return a set of people found. If no people are found, the set is empty.
     */
    public Set<Person> findByAddress(final String address) {
        Set<Person> personSet = new HashSet<>();
        for (Person p : persons) {
            if (p.getAddress().equals(address)) {
                personSet.add(p);
            }
        }
        return personSet;
    }

    /**
     * CRUD method which update a person.
     * Delete the person first before create it again with parameters provided.
     * Unique id (firstName+lastName) can't be modified.
     *
     * @param personParam a person to modify.
     */
    public void update(final Person personParam) {
        deleteById(personParam.getFirstName(), personParam.getLastName());
        Person person = new Person();
        person.setFirstName(personParam.getFirstName());
        person.setLastName(personParam.getLastName());
        person.setEmail(personParam.getEmail());
        person.setPhone(personParam.getPhone());
        person.setZip(personParam.getZip());
        person.setCity(personParam.getCity());
        person.setAddress(personParam.getAddress());
        persons.add(person);

    }

    /**
     * CRUD method which save a person.
     * Try to find person first. If already present in set,
     * throw new IllegalArgumentException().
     *
     * @param personParam a person to add to the set.
     */
    public void save(final Person personParam) {
        Person person = findById(personParam.getFirstName(), personParam.getLastName());
        if (person == null) {
            persons.add(personParam);
        } else {
            LOGGER.error("Person already exist in Set", new IllegalArgumentException());
            throw new IllegalArgumentException();
        }

    }

    /**
     * CRUD method which delete a person from unique id.
     * Try to find person first. If not present in set,
     * throw new IllegalArgumentException().
     *
     * @param firstName first part of unique id to delete.
     * @param lastName  second part of unique id to delete.
     */
    public void deleteById(final String firstName, final String lastName) {
        Person person = findById(firstName, lastName);
        if (person != null) {
            persons.remove(person);
        } else {
            LOGGER.error("Person doesn't exist in Set", new IllegalArgumentException());
            throw new IllegalArgumentException();
        }

    }

}


