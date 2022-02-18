package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.MedicalRecord;

import java.util.Set;

public interface IMedicalRecordDao {

    Set<MedicalRecord> getMedicalRecords();

    void setMedicalRecords(Set<MedicalRecord> medicalRecordsParam);

    /**
     * CRUD method which find all medicalRecord.
     *
     * @return all medicalRecord.
     */
    Set<MedicalRecord> findAll();

    /**
     * CRUD method which find a medicalRecord from unique id.
     *
     * @param firstName first part of unique id.
     * @param lastName  second part of unique id.
     * @return the medicalRecord.
     */
    MedicalRecord findById(String firstName, String lastName);

    /**
     * CRUD method which save a medicalRecord.
     *
     * @param medicalRecordParam a medicalRecord.
     */
    void save(MedicalRecord medicalRecordParam);

    /**
     * CRUD method which update a medicalRecord.
     *
     * @param medicalRecordParam a medicalRecord.
     */
    void update(MedicalRecord medicalRecordParam);

    /**
     * CRUD method which delete a medicalRecord from unique id.
     *
     * @param firstName first part of unique id.
     * @param lastName  second part of unique id.
     */
    void deleteById(String firstName, String lastName);

}


