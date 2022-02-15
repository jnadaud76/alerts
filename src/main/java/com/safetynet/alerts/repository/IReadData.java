package com.safetynet.alerts.repository;

import com.fasterxml.jackson.databind.JsonNode;

public interface IReadData {
    /**
     * Load data from data source.
     *
     * @return JsonNode.
     */
    JsonNode loadData();

    /**
     * Load person.
     */
    void loadPerson();

    /**
     * Load fireStation.
     */
    void loadFireStation();

    /**
     * Load medicalRecord.
     */
    void loadMedicalRecord();
}
