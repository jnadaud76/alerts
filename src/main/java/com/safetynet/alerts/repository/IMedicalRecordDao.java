package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.MedicalRecord;

import java.util.Set;

public interface IMedicalRecordDao {
    Set<MedicalRecord> getMedicalRecords();
}
