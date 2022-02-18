package com.safetynet.alerts.repository.impl;

import static com.safetynet.alerts.constants.Constants.JSON_PATH;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.IFireStationDao;
import com.safetynet.alerts.repository.IMedicalRecordDao;
import com.safetynet.alerts.repository.IPersonDao;
import com.safetynet.alerts.repository.IReadData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

@Repository
public class ReadDataFromJson implements IReadData {
    /**
     * @see Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ReadDataFromJson.class);
    /**
     * @see ObjectMapper
     */
    private final ObjectMapper objectMapper = new ObjectMapper();
    /**
     * @see PersonDao
     */
    @Autowired
    private IPersonDao personDao;
    /**
     * @see FireStationDao
     */
    @Autowired
    private IFireStationDao fireStationDao;
    /**
     * @see MedicalRecordDao
     */
    @Autowired
    private IMedicalRecordDao medicalRecordDao;

    /**
     * Load data from Json file.
     *
     * @return JsonNode of the file provided.
     */
    @PostConstruct
    public JsonNode loadData() {
        JsonNode jsonNode = null;
        try {
            LOGGER.debug("JsonNode process successful");
            InputStream input = getClass().getResourceAsStream(JSON_PATH);
            jsonNode = objectMapper.readValue(input, JsonNode.class);
        } catch (IOException e) {
            LOGGER.error("IO error", e);
        }
        return jsonNode;
    }

    /**
     * Load persons from JsonNode selected when application is started.
     * Add result to Set persons in personDao.
     */
    @PostConstruct
    public void loadPerson() {

        try {
            LOGGER.debug("Json process successful");
            JsonNode personsNode = loadData().get("persons");
            String personsAsString = personsNode.toString();
            Set<Person> persons = objectMapper.readValue(personsAsString,
                    new TypeReference<HashSet<Person>>() {
                    });
            personDao.setPersons(persons);

        } catch (JsonProcessingException e) {
            LOGGER.error("Error in Json Process", e);
        }

    }

    /**
     * Load fireStations from JsonNode selected when application is started.
     * Add result to Set fireStations in fireStationDao.
     */
    @PostConstruct
    public void loadFireStation() {
        try {
            LOGGER.debug("Json process successful");
            JsonNode fireStationsNode = loadData().get("firestations");
            String fireStationsAsString = fireStationsNode.toString();
            Set<FireStation> fireStations = objectMapper
                    .readValue(fireStationsAsString,
                            new TypeReference<HashSet<FireStation>>() {
                            });
            fireStationDao.setFireStations(fireStations);

        } catch (JsonProcessingException e) {
            LOGGER.error("Error in Json Process", e);
        }

    }

    /**
     * Load medicalRecords from JsonNode selected when application is started.
     * Add result to Set medicalRecords in medicalRecordDao.
     */
    @PostConstruct
    public void loadMedicalRecord() {
        try {
            LOGGER.debug("Json process successful");
            JsonNode medicalRecordsNode = loadData().get("medicalrecords");
            String medicalRecordsAsString = medicalRecordsNode.toString();
            Set<MedicalRecord> medicalRecords = objectMapper
                    .readValue(medicalRecordsAsString,
                            new TypeReference<HashSet<MedicalRecord>>() {
                            });
            medicalRecordDao.setMedicalRecords(medicalRecords);


        } catch (JsonProcessingException e) {
            LOGGER.error("Error in Json Process", e);
        }

    }

}
