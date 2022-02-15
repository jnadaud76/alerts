package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.MedicalRecordFullDto;
import com.safetynet.alerts.model.MedicalRecord;

import java.util.Set;

public interface IMedicalRecordService {
    /**
     * Get all medicalRecord.
     *
     * @return a set of medicalRecord.
     */
    Set<MedicalRecordFullDto> getMedicalRecords();

    /**
     * Get a medicalRecord by unique id.
     *
     * @param firstName first part of unique id.
     * @param lastName  second part of unique id.
     * @return a medicalRecord.
     */
    MedicalRecordFullDto getMedicalRecord(String firstName, String lastName);

    /**
     * Create a medicalRecord.
     *
     * @param medicalRecordParam a medicalRecord.
     */
    void createMedicalRecord(MedicalRecord medicalRecordParam);

    /**
     * Update a medicalRecord.
     *
     * @param medicalRecordParam a medicalRecord to modify.
     */
    void updateMedicalRecord(MedicalRecord medicalRecordParam);

    /**
     * Delete a person by unique id.
     *
     * @param firstName first part of unique id to delete.
     * @param lastName  second part of unique id to delete.
     */
    void deleteMedicalRecord(String firstName, String lastName);

}