package com.safetynet.alerts.controller;

import com.safetynet.alerts.dto.FireStationFullDto;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.service.IFireStationService;

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
 * Endpoint : ApiUrl/firestation
 * <p>
 * This endpoint will perform the following actions via
 * Post/Put/Delete with HTTP:
 * <ul>
 * <li>addition of a barracks/address mapping;</li>
 * <li>update the fire station number of an address;</li>
 * <li>delete the mapping of a barracks or an address.</li>
 * </ul>
 */
@RestController
public class FireStationController {
    /**
     * @see Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(FireStationController.class);
    /**
     * @see IFireStationService
     */
    @Autowired
    private IFireStationService fireStationService;

    /**
     * Get all fireStation.
     *
     * @return all fireStation (JSON format).
     */
    @GetMapping("/firestations")
    public Set<FireStationFullDto> getFireStations() {
        LOGGER.info("Firestations successfully found - code : {}", HttpStatus.OK);
        return fireStationService.getFireStations();
    }

    /**
     * Get a set of fireStation by station number.
     *
     * @param station a station number.
     * @return a set of fireStation if founded (JSON format) with HttpStatus ok
     * or null with HttpStatus not found otherwise.
     */
    @GetMapping("/firestation/station/")
    public ResponseEntity<Set<FireStationFullDto>>
    getFireStationsByStation(@RequestParam("station") final int station) {
        if (!fireStationService.getFireStationsByStation(station).isEmpty()) {
            LOGGER.info("Firestation successfully found - code : {}", HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(fireStationService.getFireStationsByStation(station));
        } else {
            LOGGER.error("Firestation not found - code : {}", HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Get a set of fireStation by address.
     *
     * @param address a fireStation address.
     * @return a set of fireStation if founded (JSON format) with HttpStatus ok
     * or null with HttpStatus not found otherwise.
     */
    @GetMapping("/firestation/address/")
    public ResponseEntity<Set<FireStationFullDto>>
    getFireStationByAddress(@RequestParam("address") final String address) {
        if (!fireStationService.getFireStationsByAddress(address).isEmpty()) {
            LOGGER.info("Firestation successfully found - code : {}", HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(fireStationService.getFireStationsByAddress(address));
        } else {
            LOGGER.error("Firestation not found - code : {}", HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Create a fireStation.
     *
     * @param fireStation a fireStation (JSON format).
     * @return HttpStatus created if request is successful or HttpStatus bad
     * request otherwise.
     */
    @PostMapping("/firestation")
    public ResponseEntity<String>
    createFireStation(@RequestBody final FireStation fireStation) {
        try {
            fireStationService.createFireStation(fireStation);
            LOGGER.info("Firestation successfully created - code : {}", HttpStatus.CREATED);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("FireStation created");
        } catch (Exception e) {
            LOGGER.error("Firestation can't be create - code : {}", HttpStatus.BAD_REQUEST, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("FireStation can't be create. May already exist");
        }
    }

    /**
     * Update a fireStation.
     *
     * @param fireStation a fireStation to modify (JSON format).
     * @return HttpStatus ok if request is successful or HttpStatus bad
     * request otherwise.
     */
    @PutMapping("/firestation")
    public ResponseEntity<String>
    updateFireStation(@RequestBody final FireStation fireStation) {
        try {
            fireStationService.updateFirestation(fireStation);
            LOGGER.info("Firestation successfully updated - code : {}", HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Successfully Updated");

        } catch (Exception e) {
            LOGGER.error("Firestation can't be update - code : {}", HttpStatus.BAD_REQUEST, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Cant Update! Entity not exist");
        }
    }

    /**
     * Delete a fireStation by station number.
     *
     * @param station station number to delete.
     * @return HttpStatus ok if request is successful or HttpStatus bad
     * request otherwise.
     */
    @DeleteMapping("/firestation/station/")
    public ResponseEntity<String>
    deleteFireStationByStation(@RequestParam("station") final int station) {
        try {
            fireStationService.deleteFireStationByStation(station);
            LOGGER.info("Firestation successfully deleted - code : {}", HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Successfully Deleted");

        } catch (Exception e) {
            LOGGER.error("Firestation can't be delete - code : {}", HttpStatus.BAD_REQUEST, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Cant delete! Entity not exist");
        }
    }

    /**
     * Delete a fireStation by address.
     *
     * @param address fireStation address to delete.
     * @return HttpStatus ok if request is successful or HttpStatus bad
     * request otherwise.
     */
    @DeleteMapping("/firestation/address/")
    public ResponseEntity<String>
    deleteFireStationByAddress(@RequestParam("address") final String address) {
        try {
            fireStationService.deleteFireStationByAddress(address);
            LOGGER.info("Firestation successfully deleted - code : {}", HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Successfully Deleted");

        } catch (Exception e) {
            LOGGER.error("Firestation can't be delete - code : {}", HttpStatus.BAD_REQUEST, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Cant delete! Entity not exist");
        }
    }

}
