package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.FireStationService;

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
public class FireStationController {

    @Autowired
    FireStationService fireStationService;

    @GetMapping("/firestation")
    public Set<FireStation> getFireStations(){
        return fireStationService.getFireStations();
    }

    @GetMapping("/firestation/station/{station}")
    public ResponseEntity<Set<FireStation>> getFireStationsByStation(@PathVariable ("station") int station){
        if (!fireStationService.getFireStationsByStation(station).isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(fireStationService.getFireStationsByStation(station));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
     }

    @GetMapping("/firestation/address/{address}")
    public ResponseEntity<Set<FireStation>> getFireStationByAddress(@PathVariable ("address") String address){
        if (!fireStationService.getFireStationsByAddress(address).isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(fireStationService.getFireStationsByAddress(address));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping(value="/firestation")
    public ResponseEntity<?> createFireStation (@RequestBody FireStation fireStation){
        try {
            fireStationService.createFireStation(fireStation);
            return ResponseEntity.status(HttpStatus.CREATED).body("FireStation created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FireStation can't be create. May already exist");
        }
    }

    @PutMapping("/firestation")
    public ResponseEntity<?> updateFireStation (@RequestBody FireStation firestation) {
        try {
            fireStationService.updateFirestation(firestation);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully Updated");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cant Update! Entity not exist");
        }
    }

    @DeleteMapping(value = "/firestation/station/{station}")
    public ResponseEntity<?> deleteFireStationByStation (@PathVariable ("station") int station) {
        try {
            fireStationService.deleteFireStationByStation(station);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully Deleted");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cant delete! Entity not exist");
        }
    }

    @DeleteMapping(value = "/firestation/address/{address}")
    public ResponseEntity<?> deleteFireStationByAddress (@PathVariable ("address") String address) {
        try {
            fireStationService.deleteFireStationByAddress(address);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully Deleted");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cant delete! Entity not exist");
        }
    }

}
