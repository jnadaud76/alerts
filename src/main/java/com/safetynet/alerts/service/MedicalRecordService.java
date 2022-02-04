package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.MedicalRecordFullDto;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.MedicalRecordDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class MedicalRecordService {

    @Autowired
    MedicalRecordDao medicalRecordDao;

    public Set<MedicalRecordFullDto> getMedicalRecords() {
       Set<MedicalRecord> medicalRecords = medicalRecordDao.findAll();
       Set<MedicalRecordFullDto> medicalRecordFullDtoSet = new HashSet<>();

       for(MedicalRecord medicalRecord : medicalRecords) {
           MedicalRecordFullDto medicalRecordFullDto = new MedicalRecordFullDto();
           medicalRecordFullDto.setFirstName(medicalRecord.getFirstName());
           medicalRecordFullDto.setLastName(medicalRecord.getLastName());
           medicalRecordFullDto.setBirthdate(medicalRecord.getBirthdate());
           medicalRecordFullDto.setMedications(medicalRecord.getMedications());
           medicalRecordFullDto.setAllergies(medicalRecord.getAllergies());
           medicalRecordFullDtoSet.add(medicalRecordFullDto);
       }
       return medicalRecordFullDtoSet;
    }

    public MedicalRecordFullDto getMedicalRecord(final String firstname, final String lastName) {
        MedicalRecord medicalRecord = medicalRecordDao.findById(firstname, lastName);
        MedicalRecordFullDto medicalRecordFullDto = new MedicalRecordFullDto();
        if (medicalRecord != null) {
            medicalRecordFullDto.setFirstName(medicalRecord.getFirstName());
            medicalRecordFullDto.setLastName(medicalRecord.getLastName());
            medicalRecordFullDto.setBirthdate(medicalRecord.getBirthdate());
            medicalRecordFullDto.setMedications(medicalRecord.getMedications());
            medicalRecordFullDto.setAllergies(medicalRecord.getAllergies());
        } else {
            medicalRecordFullDto = null;
        }
        return  medicalRecordFullDto;

    }

    public void createMedicalRecord(final MedicalRecord medicalRecordParam) {
        medicalRecordDao.save(medicalRecordParam);
    }

    public void updateMedicalRecord(final MedicalRecord medicalRecordParam) throws Exception {
        medicalRecordDao.update(medicalRecordParam);
    }

    public void deleteMedicalRecord(final String firstname, final String lastName) {
        medicalRecordDao.deleteById(firstname, lastName);
    }
}
