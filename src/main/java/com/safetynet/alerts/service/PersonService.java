package com.safetynet.alerts.service;


import com.safetynet.alerts.dto.PersonFullDto;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


@Service
public class PersonService {

    @Autowired
    PersonDao personDao;

    public Set<PersonFullDto> getPersons() {
        Set<Person> persons = personDao.findAll();
        Set<PersonFullDto> personFullDtoSet = new HashSet<>();

        for (Person person : persons) {
            PersonFullDto personFullDto = new PersonFullDto();
            personFullDto.setFirstName(person.getFirstName());
            personFullDto.setLastName(person.getLastName());
            personFullDto.setAddress(person.getAddress());
            personFullDto.setCity(person.getCity());
            personFullDto.setZip(person.getZip());
            personFullDto.setPhone(person.getPhone());
            personFullDto.setEmail(person.getEmail());
            personFullDtoSet.add(personFullDto);
        }
        return personFullDtoSet;

    }

    public PersonFullDto getPerson(final String firstname, final String lastName) {
        Person person = personDao.findById(firstname, lastName);
        PersonFullDto personFullDto = new PersonFullDto();
        if (person != null) {
            personFullDto.setFirstName(person.getFirstName());
            personFullDto.setLastName(person.getLastName());
            personFullDto.setAddress(person.getAddress());
            personFullDto.setCity(person.getCity());
            personFullDto.setZip(person.getZip());
            personFullDto.setPhone(person.getPhone());
            personFullDto.setEmail(person.getEmail());

        } else {
            personFullDto = null;
        }
       return personFullDto;

    }

    public void createPerson (final Person personParam){
        personDao.save(personParam);
    }

    public void updatePerson (final Person personParam) throws Exception {
        personDao.update(personParam);
    }

    public void deletePerson(final String firstname, final String lastName) {
        personDao.deleteById(firstname, lastName);
    }


}
