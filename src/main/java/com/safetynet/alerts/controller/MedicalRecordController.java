package com.safetynet.alerts.controller;

import com.safetynet.alerts.dto.MedicalRecordFullDto;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.IMedicalRecordService;
import com.safetynet.alerts.service.IPersonService;


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

/**
 * Endpoint : ApiUrl/medicalrecord
 * <p>
 * This endpoint will perform the following actions via
 * Post/Put/Delete with HTTP:
 * <li>add a medical file;</li>
 * <li>update an existing medical record (as mentioned previously, assume that the
 * first name and last name do not change);</li>
 * <li>Delete a medical record (use a combination of first and last name like
 * unique identifier).</li>
 * </p>
 */
@RestController
public class MedicalRecordController {
    /**
     * @see Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MedicalRecordController.class);
    /**
     * @see IMedicalRecordService
     */
    @Autowired
    private IMedicalRecordService medicalRecordService;

    /**
     * Access URL=ApiUrl/medicalrecords
     * <p>
     * Get all medicalRecord.
     * </p>
     *
     * @return all medicalRecord (JSON format).
     */
    @GetMapping("/medicalrecords")
    public Set<MedicalRecordFullDto> getMedicalRecords() {
        LOGGER.info("Medicalrecords successfully found - code : {}", HttpStatus.OK);
        return medicalRecordService.getMedicalRecords();
    }

    /**
     * Access URL=ApiUrl/medicalrecord?firstName=firstName&lastName=lastName
     * <p>
     * Get a medicalRecord by unique id.
     * </p>
     *
     * @param firstName first part of unique id.
     * @param lastName  second part of unique id.
     * @return the medicalRecord sought if founded with HttpStatus ok (JSON format) or
     * null with HttpStatus not found otherwise.
     */
    @GetMapping("/medicalrecord")
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

    /**
     * Create a medicalRecord.
     *
     * @param medicalRecord a medicalRecord (JSON format).
     * @return HttpStatus created if request is successful or HttpStatus bad
     * request otherwise.
     */
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

    /**
     * Update a medicalRecord.
     *
     * @param medicalRecord a medicalRecord to modify (JSON format).
     * @return HttpStatus ok if request is successful or HttpStatus bad
     * request otherwise.
     */
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

    /**
     * Delete a medicalRecord by unique id.
     *
     * @param firstName first part of unique id to delete.
     * @param lastName  second part of unique id to delete.
     * @return HttpStatus ok if request is successful or HttpStatus bad
     * request otherwise.
     */
    @DeleteMapping("/medicalrecord")
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
