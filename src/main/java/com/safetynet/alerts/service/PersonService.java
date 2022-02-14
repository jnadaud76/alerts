package com.safetynet.alerts.service;



import com.safetynet.alerts.dto.PersonFullDto;


import com.safetynet.alerts.model.Person;

import com.safetynet.alerts.repository.PersonDao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class PersonService implements IPersonService {

    @Autowired
    private PersonDao personDao;

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


    public Set<PersonFullDto> getPersons() {
        Set<Person> persons = personDao.findAll();
        Set<PersonFullDto> personFullDtoSet = new HashSet<>();

        for (Person p : persons) {
            personFullDtoSet.add(personToPersonFullDto(p));
        }
        return personFullDtoSet;

    }

    public PersonFullDto getPerson(final String firstname, final String lastName) {
        Person person = personDao.findById(firstname, lastName);

        if (person != null) {
            return personToPersonFullDto(person);
        } else {
            return null;
        }
    }

    public Set<PersonFullDto> getPersonByAddress(final String address) {
        Set<Person> persons = personDao.findByAddress(address);
        Set<PersonFullDto> personFullDtoSet = new HashSet<>();
        for (Person p : persons) {
            personFullDtoSet.add(personToPersonFullDto(p));
        }
        return personFullDtoSet;

    }

    public void createPerson(final Person personParam) {
        personDao.save(personParam);
    }

    public void updatePerson(final Person personParam) {
        personDao.update(personParam);
    }

    public void deletePerson(final String firstname, final String lastName) {
        personDao.deleteById(firstname, lastName);
    }


}
