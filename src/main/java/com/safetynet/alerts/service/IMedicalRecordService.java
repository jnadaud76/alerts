package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.MedicalRecordFullDto;
import com.safetynet.alerts.model.MedicalRecord;

import java.util.Set;

public interface IMedicalRecordService {

    Set<MedicalRecordFullDto> getMedicalRecords();

    MedicalRecordFullDto getMedicalRecord(String firstname, String lastName);

    void createMedicalRecord(MedicalRecord medicalRecordParam);

    void updateMedicalRecord(MedicalRecord medicalRecordParam) throws Exception;

    void deleteMedicalRecord(String firstname, String lastName);

}