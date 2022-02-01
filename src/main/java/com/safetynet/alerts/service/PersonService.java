package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

import lombok.Data;

@Data
@Service
public class PersonService {

    @Autowired
    PersonDao personDao;

    public Set<Person> getPersons() {
        return personDao.findAll();

    }

    public Person getPerson(final String firstname, final String lastName) {
        return personDao.findById(firstname, lastName);

    }

    public void createPerson (final Person person){
        personDao.save(person);
    }

    public void updatePerson (final Person person) {
        personDao.update(person);
    }

    public void deletePerson(final String firstname, final String lastName) {
        personDao.deleteById(firstname, lastName);
    }


}
