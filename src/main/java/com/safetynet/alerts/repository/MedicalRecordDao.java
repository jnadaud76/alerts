package com.safetynet.alerts.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.MedicalRecord;

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
}
