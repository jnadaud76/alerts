package com.safetynet.alerts.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.FireStationDao;
import com.safetynet.alerts.repository.MedicalRecordDao;
import com.safetynet.alerts.repository.PersonDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

@Service
public class ReadDataFromJson implements IReadData {

   @Autowired
    PersonDao personDao;

    @Autowired
    FireStationDao fireStationDao;

    @Autowired
    MedicalRecordDao medicalRecordDao;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public JsonNode loadData() {
        JsonNode jsonNode = null;
        try {
            InputStream input = new FileInputStream("src/main/resources/data.json");
            jsonNode = objectMapper.readValue(input, JsonNode.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonNode;
    }

    @PostConstruct
    public void loadPerson() {

        try {
            JsonNode personsNode = loadData().get("persons");
            String personsAsString = personsNode.toString();
            Set<Person>persons = objectMapper.readValue(personsAsString, new TypeReference<HashSet<Person>>() {
            });
            personDao.setPersons(persons);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    @PostConstruct
    public void loadFireStation() {
        try {
            JsonNode fireStationsNode = loadData().get("firestations");
            String fireStationsAsString = fireStationsNode.toString();
            Set<FireStation>fireStations = objectMapper.readValue(fireStationsAsString, new TypeReference<HashSet<FireStation>>() {
            });
            fireStationDao.setFireStations(fireStations);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    @PostConstruct
    public void loadMedicalRecord() {
        try {
        JsonNode medicalRecordsNode = loadData().get("medicalrecords");
        String medicalRecordsAsString = medicalRecordsNode.toString();
        Set <MedicalRecord> medicalRecords = objectMapper.readValue(medicalRecordsAsString, new TypeReference<HashSet<MedicalRecord>>() {
        });
        medicalRecordDao.setMedicalRecords(medicalRecords);


    } catch (IOException e) {
        e.printStackTrace();
    }
    }

}
