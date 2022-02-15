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

    @Autowired
    private MedicalRecordDao medicalRecordDao;

    private MedicalRecordFullDto medicalRecordToMedicalRecordFullDto(final MedicalRecord medicalRecord) {
        MedicalRecordFullDto medicalRecordFullDto = new MedicalRecordFullDto();
        medicalRecordFullDto.setFirstName(medicalRecord.getFirstName());
        medicalRecordFullDto.setLastName(medicalRecord.getLastName());
        medicalRecordFullDto.setBirthdate(medicalRecord.getBirthdate());
        medicalRecordFullDto.setMedications(medicalRecord.getMedications());
        medicalRecordFullDto.setAllergies(medicalRecord.getAllergies());
        return medicalRecordFullDto;
    }

    public Set<MedicalRecordFullDto> getMedicalRecords() {
        Set<MedicalRecord> medicalRecords = medicalRecordDao.findAll();
        Set<MedicalRecordFullDto> medicalRecordFullDtoSet = new HashSet<>();

        for (MedicalRecord m : medicalRecords) {
            medicalRecordFullDtoSet.add(medicalRecordToMedicalRecordFullDto(m));
        }
        return medicalRecordFullDtoSet;
    }

    public MedicalRecordFullDto getMedicalRecord(final String firstname, final String lastName) {
        MedicalRecord medicalRecord = medicalRecordDao.findById(firstname, lastName);
        if (medicalRecord != null) {
           return medicalRecordToMedicalRecordFullDto(medicalRecord);
        } else {
           return null;
        }

    }

    public void createMedicalRecord(final MedicalRecord medicalRecordParam) {
        medicalRecordDao.save(medicalRecordParam);
    }

    public void updateMedicalRecord(final MedicalRecord medicalRecordParam) {
        medicalRecordDao.update(medicalRecordParam);
    }

    public void deleteMedicalRecord(final String firstname, final String lastName) {
        medicalRecordDao.deleteById(firstname, lastName);
    }
}
