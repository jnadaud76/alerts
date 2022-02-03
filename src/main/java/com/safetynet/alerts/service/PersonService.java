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
        Set<PersonFullDto> personDtoSet = new HashSet<>();
        Iterator<Person> i = persons.iterator();
        while (i.hasNext()) {
            Person person = i.next();
            PersonFullDto personFullDto = new PersonFullDto();
            personFullDto.setFirstName(person.getFirstName());
            personFullDto.setLastName(person.getLastName());
            personFullDto.setAddress(person.getAddress());
            personFullDto.setCity(person.getCity());
            personFullDto.setZip(person.getZip());
            personFullDto.setPhone(person.getPhone());
            personFullDto.setEmail(person.getEmail());
            personDtoSet.add(personFullDto);
        }
        return personDtoSet;

    }

    public Person getPerson(final String firstname, final String lastName) {
        return personDao.findById(firstname, lastName);

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
