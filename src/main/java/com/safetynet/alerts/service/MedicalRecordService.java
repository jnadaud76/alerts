package com.safetynet.alerts.service;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.MedicalRecordDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MedicalRecordService {

    @Autowired
    MedicalRecordDao medicalRecordDao;

    public Set<MedicalRecord> getMedicalRecords() {
        return medicalRecordDao.findAll();
    }

    public MedicalRecord getMedicalRecord(final String firstname, final String lastName) {
        return medicalRecordDao.findById(firstname, lastName);
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
