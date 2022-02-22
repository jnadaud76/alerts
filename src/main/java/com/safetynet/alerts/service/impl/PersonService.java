package com.safetynet.alerts.service.impl;


import com.safetynet.alerts.dto.PersonFullDto;


import com.safetynet.alerts.model.Person;

import com.safetynet.alerts.repository.IPersonDao;
import com.safetynet.alerts.service.IPersonService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class PersonService implements IPersonService {
    /**
     * @see IPersonDao
     */
    @Autowired
    private IPersonDao personDao;

    /**
     * Transform Person object in PersonFullDto.
     *
     * @param person a person to transform.
     * @return a person transformed.
     */
    private PersonFullDto personToPersonFullDto(final Person person) {
        PersonFullDto personFullDto = new PersonFullDto();
        personFullDto.setFirstName(person.getFirstName());
        personFullDto.setLastName(person.getLastName());
        personFullDto.setAddress(person.getAddress());
        personFullDto.setCity(person.getCity());
        personFullDto.setZip(person.getZip());
        personFullDto.setPhone(person.getPhone());
        personFullDto.setEmail(person.getEmail());
        return personFullDto;
    }

    /**
     * Get all person.
     *
     * @return a set of person.
     */
    public Set<PersonFullDto> getPersons() {
        Set<Person> persons = personDao.findAll();
        Set<PersonFullDto> personFullDtoSet = new HashSet<>();

        for (Person p : persons) {
            personFullDtoSet.add(personToPersonFullDto(p));
        }
        return personFullDtoSet;

    }

    /**
     * Get a person by unique id.
     *
     * @param firstName first part of unique id.
     * @param lastName  second part of unique id.
     * @return the person sought if present in the set or null otherwise.
     */
    public PersonFullDto getPerson(final String firstName, final String lastName) {
        Person person = personDao.findById(firstName, lastName);

        if (person != null) {
            return personToPersonFullDto(person);
        } else {
            return null;
        }
    }

    /**
     * Get a set of person by address.
     *
     * @param address a home address.
     * @return a set of people found. If no people are found, the set is empty.
     */
    public Set<PersonFullDto> getPersonByAddress(final String address) {
        Set<Person> persons = personDao.findByAddress(address);
        Set<PersonFullDto> personFullDtoSet = new HashSet<>();
        for (Person p : persons) {
            personFullDtoSet.add(personToPersonFullDto(p));
        }
        return personFullDtoSet;

    }

    /**
     * Create a person.
     *
     * @param personParam a person to add to the set.
     */
    public void createPerson(final Person personParam) {
        personDao.save(personParam);
    }

    /**
     * Update a person.
     *
     * @param personParam a person to modify.
     */
    public void updatePerson(final Person personParam) {
        personDao.update(personParam);
    }

    /**
     * Delete a person by unique id.
     *
     * @param firstName first part of unique id to delete.
     * @param lastName  second part of unique id to delete.
     */
    public void deletePerson(final String firstName, final String lastName) {
        personDao.deleteById(firstName, lastName);
    }


}
