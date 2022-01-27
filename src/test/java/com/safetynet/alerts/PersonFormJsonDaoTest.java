package com.safetynet.alerts;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonFromJsonDao;

import org.junit.jupiter.api.Test;

import java.util.Set;

public class PersonFormJsonDaoTest {

    @Test
    public void getPersonsTest(){
        //Given
        PersonFromJsonDao personFromJsonDao = new PersonFromJsonDao();

        //When
        Set<Person> persons = personFromJsonDao.getPersons();

        //Then
        assertNotNull(persons);

    }
}
