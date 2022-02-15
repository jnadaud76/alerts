package com.safetynet.alerts.service.impl;

import com.safetynet.alerts.dto.MedicalRecordFullDto;
import com.safetynet.alerts.model.MedicalRecord;

import com.safetynet.alerts.repository.MedicalRecordDao;
import com.safetynet.alerts.service.IMedicalRecordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class MedicalRecordService implements IMedicalRecordService {
    /**
     * @see MedicalRecordDao
     */
    @Autowired
    private MedicalRecordDao medicalRecordDao;

    /**
     * Transform medicalRecord object in medicalRecordFullDto.
     *
     * @param medicalRecord a medicalRecord to transform.
     * @return a medicalRecord transformed.
     */
    private MedicalRecordFullDto medicalRecordToMedicalRecordFullDto(final MedicalRecord medicalRecord) {
        MedicalRecordFullDto medicalRecordFullDto = new MedicalRecordFullDto();
        medicalRecordFullDto.setFirstName(medicalRecord.getFirstName());
        medicalRecordFullDto.setLastName(medicalRecord.getLastName());
        medicalRecordFullDto.setBirthdate(medicalRecord.getBirthdate());
        medicalRecordFullDto.setMedications(medicalRecord.getMedications());
        medicalRecordFullDto.setAllergies(medicalRecord.getAllergies());
        return medicalRecordFullDto;
    }

    /**
     * Get all medicalRecord.
     *
     * @return a set of medicalRecord.
     */
    public Set<MedicalRecordFullDto> getMedicalRecords() {
        Set<MedicalRecord> medicalRecords = medicalRecordDao.findAll();
        Set<MedicalRecordFullDto> medicalRecordFullDtoSet = new HashSet<>();

        for (MedicalRecord m : medicalRecords) {
            medicalRecordFullDtoSet.add(medicalRecordToMedicalRecordFullDto(m));
        }
        return medicalRecordFullDtoSet;
    }

    /**
     * Get a medicalRecord by unique id.
     *
     * @param firstName first part of unique id.
     * @param lastName  second part of unique id.
     * @return the medicalRecord sought if present in the set or null otherwise.
     */
    public MedicalRecordFullDto getMedicalRecord(final String firstName, final String lastName) {
        MedicalRecord medicalRecord = medicalRecordDao.findById(firstName, lastName);
        if (medicalRecord != null) {
            return medicalRecordToMedicalRecordFullDto(medicalRecord);
        } else {
            return null;
        }

    }

    /**
     * Create a medicalRecord.
     *
     * @param medicalRecordParam a medicalRecord to add to the set.
     */
    public void createMedicalRecord(final MedicalRecord medicalRecordParam) {
        medicalRecordDao.save(medicalRecordParam);
    }

    /**
     * Update a medicalRecord.
     *
     * @param medicalRecordParam a medicalRecord to modify.
     */
    public void updateMedicalRecord(final MedicalRecord medicalRecordParam) {
        medicalRecordDao.update(medicalRecordParam);
    }

    /**
     * Delete a person by unique id.
     *
     * @param firstName first part of unique id to delete.
     * @param lastName  second part of unique id to delete.
     */
    public void deleteMedicalRecord(final String firstName, final String lastName) {
        medicalRecordDao.deleteById(firstName, lastName);
    }
}
