package com.safetynet.alerts.unit;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonDao;
import com.safetynet.alerts.service.IReadData;
import com.safetynet.alerts.service.ReadDataFromJson;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Set;

public class PersonDaoTest {

    @Test
    public void getPersonsTest(){
        //Given
        PersonDao personDao = new PersonDao();

        //When
        Set<Person> persons = personDao.getPersons();
        System.out.println(persons);

        //Then
        assertNotNull(persons);

    }
}
