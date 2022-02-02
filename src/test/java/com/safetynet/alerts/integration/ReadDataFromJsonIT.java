package com.safetynet.alerts.integration;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.JsonNode;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.FireStationDao;
import com.safetynet.alerts.repository.PersonDao;
import com.safetynet.alerts.service.IReadData;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReadDataFromJsonIT {

    @Autowired
    private IReadData readDataFromJson;

    @Autowired
    private PersonDao personDao;

    @Autowired
    private FireStationDao fireStationDao;


    @Test
    public void loadDataTest(){
        //Given

        //When
        JsonNode jsonNode = readDataFromJson.loadData();

        //Then
        assertNotNull(jsonNode);
    }

    @Test
    public void loadPersonTest() {
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

    @Test
    public void loadFireStationTest() {
        //Given
        FireStation fireStation = new FireStation();
        fireStation.setStation(1);
        fireStation.setAddress("908 73rd St");

        //When
        readDataFromJson.loadPerson();

        //Then
        assertTrue (fireStationDao.getFireStations().contains(fireStation));
    }



}
