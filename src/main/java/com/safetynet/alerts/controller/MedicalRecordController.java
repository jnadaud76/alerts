package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.MedicalRecordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class MedicalRecordController {

    @Autowired
    MedicalRecordService medicalRecordService;

    @GetMapping("/medicalrecord")
    public Set<MedicalRecord> getMedicalRecords() {
        return medicalRecordService.getMedicalRecords();
    }

    @GetMapping(value = "medicalrecord/{firstName}/{lastName}")
    public ResponseEntity<MedicalRecord> getPerson(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
        if (medicalRecordService.getMedicalRecord(firstName, lastName)!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(medicalRecordService.getMedicalRecord(firstName, lastName));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @PostMapping(value="/medicalrecord")
    public ResponseEntity<?> createPerson (@RequestBody MedicalRecord medicalRecord){
        try {
            medicalRecordService.createMedicalRecord(medicalRecord);
            return ResponseEntity.status(HttpStatus.CREATED).body("MedicalRecord created");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("MedicalRecord can't be create. May already exist");
        }
    }

    @PutMapping(value="/medicalrecord")
    public ResponseEntity<?> updateMedicalRecord (@RequestBody MedicalRecord medicalRecord) {
        try{
            medicalRecordService.updateMedicalRecord(medicalRecord);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully Updated");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cant Update! Entity not exist");
        }
    }

    @DeleteMapping(value = "medicalrecord/{firstName}/{lastName}")
    public ResponseEntity<?> deletePerson (@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
        try {
            medicalRecordService.deleteMedicalRecord(firstName, lastName);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully Deleted");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cant delete! Entity not exist");
        }
    }
}
