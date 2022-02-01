package com.safetynet.alerts.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.safetynet.alerts.model.Person;

import org.springframework.stereotype.Service;

import java.util.Set;


public interface IReadData {

    JsonNode loadData();
    void loadPerson();
    void loadFireStation();
    void loadMedicalRecord();
}
