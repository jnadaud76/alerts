package com.safetynet.alerts.service;

import com.fasterxml.jackson.databind.JsonNode;

public interface IReadData {

    JsonNode loadData();

    void loadPerson();

    void loadFireStation();

    void loadMedicalRecord();
}
