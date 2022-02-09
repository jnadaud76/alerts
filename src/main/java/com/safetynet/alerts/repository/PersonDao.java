package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.Person;

import org.springframework.stereotype.Repository;

import java.util.HashSet;

import java.util.Set;


@Repository
public class PersonDao {

    private Set<Person> persons = new HashSet<>();

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> personsParam) {
        this.persons = personsParam;
    }

    public Set<Person> findAll() {
        return this.persons;
    }

    public Person findById(final String firstname, final String lastName) {
        Person person = null;
        for (Person p : persons) {
            if (p.getFirstName().equals(firstname) && p.getLastName().equals(lastName)) {
                person = p;
                break;
            }
        }
        return person;
    }

    public Set<Person> findByAddress(final String address) {
        Set<Person> personSet = new HashSet<>();
        for (Person p : persons) {
            if (p.getAddress().equals(address)) {
                personSet.add(p);
            }
        }
        return personSet;
    }

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

    public void save(final Person personParam) {
        Person person = findById(personParam.getFirstName(), personParam.getLastName());
        if (person == null) {
            persons.add(personParam);
        } else {
            throw new IllegalArgumentException();
        }

    }

    public void deleteById(final String firstname, final String lastName) {
        Person person = findById(firstname, lastName);
        if (person != null) {
            persons.remove(person);
        } else {
            throw new IllegalArgumentException();
        }

    }

}


