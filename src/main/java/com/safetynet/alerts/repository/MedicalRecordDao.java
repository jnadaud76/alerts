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

import lombok.Data;

@Data
@Repository
public class MedicalRecordDao {

    Set<MedicalRecord> medicalRecords = new HashSet<>();

     /*public void getMedicalRecordsFromJson() {

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

    }
        ObjectMapper objectMapper = new ObjectMapper();


        try {
            InputStream input = new FileInputStream("src/main/resources/data.json");

            JsonNode jsonNode = objectMapper.readValue(input, JsonNode.class);
            JsonNode medicalRecordsNode = jsonNode.get("medicalRecords");
            String medicalRecordsAsString = medicalRecordsNode.toString();
            medicalRecords = objectMapper.readValue(medicalRecordsAsString, new TypeReference<HashSet<MedicalRecord>>() {
            });
            System.out.println("persons :" + medicalRecords);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

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
