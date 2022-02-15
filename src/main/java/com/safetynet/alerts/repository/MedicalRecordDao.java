package com.safetynet.alerts.repository;


import com.safetynet.alerts.model.MedicalRecord;


import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;


@Repository
public class MedicalRecordDao {
    /**
     * Set of medicalRecord extract from data source.
     */
    Set<MedicalRecord> medicalRecords = new HashSet<>();

    public Set<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

    public void setMedicalRecords(final Set<MedicalRecord> medicalRecordsParam) {
        this.medicalRecords = medicalRecordsParam;
    }

    /**
     * CRUD method which find all medicalRecord.
     *
     * @return all medicalRecord.
     */
    public Set<MedicalRecord> findAll() {
        return this.medicalRecords;
    }

    /**
     * CRUD method which find a medicalRecord from unique id.
     *
     * @param firstName first part of unique id.
     * @param lastName  second part of unique id.
     * @return the medicalRecord sought if present in the set or null otherwise.
     */
    public MedicalRecord findById(final String firstName, final String lastName) {
        MedicalRecord medicalRecord = null;
        for (MedicalRecord m : medicalRecords) {
            if (m.getFirstName().equals(firstName) && m.getLastName().equals(lastName)) {
                medicalRecord = m;
                break;
            }
        }
        return medicalRecord;
    }

    /**
     * CRUD method which save a medicalRecord.
     * Try to find medicalRecord first. If already present in set,
     * throw new IllegalArgumentException().
     *
     * @param medicalRecordParam a medicalRecord to add to the set.
     */
    public void save(final MedicalRecord medicalRecordParam) {
        MedicalRecord medicalRecord = findById(medicalRecordParam.getFirstName(), medicalRecordParam.getLastName());
        if (medicalRecord == null) {
            medicalRecords.add(medicalRecordParam);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * CRUD method which update a medicalRecord.
     * Delete the medicalRecord first before create it again with parameters provided.
     * Unique id (firstName+lastName) can't be modified.
     *
     * @param medicalRecordParam a medicalRecord to modify.
     */
    public void update(final MedicalRecord medicalRecordParam) {
        deleteById(medicalRecordParam.getFirstName(), medicalRecordParam.getLastName());
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName(medicalRecordParam.getFirstName());
        medicalRecord.setLastName(medicalRecordParam.getLastName());
        medicalRecord.setBirthdate(medicalRecordParam.getBirthdate());
        medicalRecord.setMedications(medicalRecordParam.getMedications());
        medicalRecord.setAllergies(medicalRecordParam.getAllergies());
        medicalRecords.add(medicalRecord);

    }

    /**
     * CRUD method which delete a medicalRecord from unique id.
     * Try to find medicalRecord first. If not present in set,
     * throw new IllegalArgumentException().
     *
     * @param firstName first part of unique id to delete.
     * @param lastName  second part of unique id to delete.
     */
    public void deleteById(final String firstName, final String lastName) {
        MedicalRecord medicalRecord = findById(firstName, lastName);
        if (medicalRecord != null) {
            medicalRecords.remove(medicalRecord);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
