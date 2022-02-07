package com.safetynet.alerts.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;

import org.springframework.stereotype.Repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;


@Repository
public class MedicalRecordDao {

    Set<MedicalRecord> medicalRecords = new HashSet<>();

    public Set<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

    public void setMedicalRecords(Set<MedicalRecord> medicalRecordsParam) {
        this.medicalRecords = medicalRecordsParam;
    }

    public Set<MedicalRecord> findAll() {
        return this.medicalRecords;
    }

    public MedicalRecord findById (final String firstname, final String lastName){
        MedicalRecord medicalRecord = null;
        for (MedicalRecord m : medicalRecords) {
            if (m.getFirstName().equals(firstname) && m.getLastName().equals(lastName)) {
                medicalRecord = m;
                break;
            }
        }
        return medicalRecord;
    }

    public void save (final MedicalRecord medicalRecordParam) {
        MedicalRecord medicalRecord = findById(medicalRecordParam.getFirstName(), medicalRecordParam.getLastName());
        if (medicalRecord == null) {
            medicalRecords.add(medicalRecordParam);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void update(final MedicalRecord medicalRecordParam) throws Exception {
        deleteById(medicalRecordParam.getFirstName(), medicalRecordParam.getLastName());
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName(medicalRecordParam.getFirstName());
        medicalRecord.setLastName(medicalRecordParam.getLastName());
        medicalRecord.setBirthdate(medicalRecordParam.getBirthdate());
        medicalRecord.setMedications(medicalRecordParam.getMedications());
        medicalRecord.setAllergies(medicalRecordParam.getAllergies());
        medicalRecords.add(medicalRecord);

    }

    public void deleteById (final String firstname, final String lastName) {
        MedicalRecord medicalRecord = findById(firstname, lastName);
        if (medicalRecord != null) {
            medicalRecords.remove(medicalRecord);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
