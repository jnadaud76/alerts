package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.MedicalRecord;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Repository
public class MedicalRecordFromJsonDao implements IMedicalRecordDao {

    @Override
    public Set<MedicalRecord> getMedicalRecords() {
        Set<MedicalRecord> medicalRecords = new HashSet<>();
        JSONParser jsonparser = new JSONParser();

        try {
            JSONObject jsonObject = (JSONObject) jsonparser
                    .parse(new FileReader("src/main/resources/data.json"));
            JSONArray jsonArray = (JSONArray) jsonObject.get("medicalrecords");
            for (Object c : jsonArray) {
                JSONObject ObjectMedicalRecord = (JSONObject) c;
                String firstName = (String) ObjectMedicalRecord.get("firstName");
                String lastName = (String) ObjectMedicalRecord.get("lastName");
                String birthDate = (String) ObjectMedicalRecord.get("birthdate");
                JSONArray medicationsArray = (JSONArray) ObjectMedicalRecord.get("medications");
                Set<String> medications = new HashSet<>();
                if (medicationsArray != null) {
                    for (Object d : medicationsArray) {
                        medications.add(d.toString());
                    }
                } else {
                    medications = null;
                }
                JSONArray allergiesArray = (JSONArray) ObjectMedicalRecord.get("allergies");
                Set<String> allergies = new HashSet<>();
                if (allergiesArray != null) {
                    for (Object f : allergiesArray) {
                         allergies.add(f.toString());
                    }
                } else {
                    allergies = null;
                }
                MedicalRecord medicalRecord = new MedicalRecord();
                medicalRecord.setFirstName(firstName);
                medicalRecord.setLastName(lastName);
                medicalRecord.setBirthdate(birthDate);
                medicalRecord.setMedications(medications);
                medicalRecord.setAllergies(allergies);
                medicalRecords.add(medicalRecord);
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return medicalRecords;
    }
}
