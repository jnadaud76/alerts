package com.safetynet.alerts.unit;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.JsonNode;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonDao;
import com.safetynet.alerts.service.IReadData;
import com.safetynet.alerts.service.ReadDataFromJson;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReadDataFromJsonTest {

    @Autowired
    private IReadData readDataFromJson;

    @Autowired
    private PersonDao personDao;


    @Test
    public void loadDataTest(){
        //Given

        //When
        JsonNode jsonNode = readDataFromJson.loadData();

        //Then
        assertNotNull(jsonNode);
    }

    @Test
    public void loadPerson() {
        //Given
        Person person = new Person();
        person.setFirstName("Lily");
        person.setLastName("Cooper");
        person.setAddress("489 Manchester St");
        person.setCity("Culver");
        person.setZip(97451);
        person.setPhone("841-874-9845");
        person.setEmail("lily@email.com");

        //When
        readDataFromJson.loadPerson();

        //Then
        assertTrue (personDao.getPersons().contains(person));
    }



}
