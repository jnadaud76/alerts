package com.safetynet.alerts.unit;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.repository.MedicalRecordDao;

import org.junit.jupiter.api.Test;

import java.util.Set;

public class MedicalRecordDaoTest {
    @Test
    public void getMedicalRecordsTest(){
        //Given
        MedicalRecordDao medicalRecordDao = new MedicalRecordDao();

        //When

        Set<MedicalRecord> medicalRecords = medicalRecordDao.getMedicalRecords();
        System.out.println(medicalRecords);

        //Then
        assertNotNull(medicalRecords);

    }
}
