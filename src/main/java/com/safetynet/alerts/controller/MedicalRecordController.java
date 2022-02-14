package com.safetynet.alerts.controller;

import com.safetynet.alerts.dto.MedicalRecordFullDto;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.IMedicalRecordService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class MedicalRecordController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MedicalRecordController.class);

    @Autowired
    private IMedicalRecordService medicalRecordService;

    @GetMapping("/medicalrecords")
    public Set<MedicalRecordFullDto> getMedicalRecords() {
        LOGGER.info("Medicalrecords successfully found - code : {}", HttpStatus.OK);
        return medicalRecordService.getMedicalRecords();
    }

    @GetMapping("medicalrecord/")
    public ResponseEntity<MedicalRecordFullDto>
    getPerson(@RequestParam("firstName") final String firstName,
              @RequestParam("lastName") final String lastName) {
        if (medicalRecordService.getMedicalRecord(firstName, lastName) != null) {
            LOGGER.info("Medicalrecord successfully found - code : {}", HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(medicalRecordService
                            .getMedicalRecord(firstName, lastName));
        } else {
            LOGGER.error("Medicalrecord not found - code : {}", HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @PostMapping("/medicalrecord")
    public ResponseEntity<?>
    createPerson(@RequestBody final MedicalRecord medicalRecord) {
        try {
            medicalRecordService.createMedicalRecord(medicalRecord);
            LOGGER.info("Medicalrecord successfully created - code : {}", HttpStatus.CREATED);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("MedicalRecord created");
        } catch (IllegalArgumentException e) {
            LOGGER.error("Medicalrecord can't be create - code : {}", HttpStatus.BAD_REQUEST, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("MedicalRecord can't be create. May already exist");
        }
    }

    @PutMapping("/medicalrecord")
    public ResponseEntity<?>
    updateMedicalRecord(@RequestBody final MedicalRecord medicalRecord) {
        try {
            medicalRecordService.updateMedicalRecord(medicalRecord);
            LOGGER.info("Medicalrecord successfully updated - code : {}", HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Successfully Updated");

        } catch (Exception e) {
            LOGGER.error("Medicalrecord can't be update - code : {}", HttpStatus.BAD_REQUEST, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Cant Update! Entity not exist");
        }
    }

    @DeleteMapping("medicalrecord/")
    public ResponseEntity<?>
    deletePerson(@RequestParam("firstName") final String firstName,
                 @RequestParam("lastName") final String lastName) {
        try {
            medicalRecordService.deleteMedicalRecord(firstName, lastName);
            LOGGER.info("Medicalrecord successfully deleted - code : {}", HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Successfully Deleted");

        } catch (IllegalArgumentException e) {
            LOGGER.error("Medicalrecord can't be delete - code : {}", HttpStatus.BAD_REQUEST, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Cant delete! Entity not exist");
        }
    }
}
