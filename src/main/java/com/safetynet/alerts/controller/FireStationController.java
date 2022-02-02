package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.service.FireStationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<FireStation> getFireStationByAddress(@PathVariable ("address") String address){
        if (fireStationService.getFireStationsByAddress(address)!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(fireStationService.getFireStationsByAddress(address));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
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

}
