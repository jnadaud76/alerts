package com.safetynet.alerts;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.repository.MedicalRecordFromJsonDao;

import org.junit.jupiter.api.Test;

import java.util.Set;

public class MedicalRecordFromJsonDaoTest {
    @Test
    public void getMedicalRecordsTest(){
        //Given
        MedicalRecordFromJsonDao medicalRecordFromJsonDao = new MedicalRecordFromJsonDao();

        //When
        Set<MedicalRecord> medicalRecords = medicalRecordFromJsonDao.getMedicalRecords();

        //Then
        assertNotNull(medicalRecords);

    }
}
