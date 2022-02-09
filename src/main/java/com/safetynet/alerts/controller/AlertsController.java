package com.safetynet.alerts.controller;

import com.safetynet.alerts.dto.PersonChildAlertDto;
import com.safetynet.alerts.dto.PersonFireDto;
import com.safetynet.alerts.dto.PersonFireStationDto;
import com.safetynet.alerts.dto.PersonFloodDto;
import com.safetynet.alerts.dto.PersonInfoDto;
import com.safetynet.alerts.service.IAlertsChildAlertService;
import com.safetynet.alerts.service.IAlertsCommunityEmailService;
import com.safetynet.alerts.service.IAlertsFireService;
import com.safetynet.alerts.service.IAlertsFireStationService;
import com.safetynet.alerts.service.IAlertsFloodService;
import com.safetynet.alerts.service.IAlertsPersonInfoService;
import com.safetynet.alerts.service.IAlertsPhoneAlertService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class AlertsController {

    @Autowired
    private IAlertsFireStationService alertsFireStationService;

    @Autowired
    private IAlertsCommunityEmailService alertsCommunityEmailService;

    @Autowired
    private IAlertsChildAlertService alertsChildAlertService;

    @Autowired
    private IAlertsPersonInfoService alertsPersonInfoService;

    @Autowired
    private IAlertsPhoneAlertService alertPhoneAlertService;

    @Autowired
    private IAlertsFireService alertsFireService;

    @Autowired
    private IAlertsFloodService alertsFloodService;

    @GetMapping("firestation")
    public ResponseEntity<PersonFireStationDto>
    getPersonFromFireStation(@RequestParam("stationNumber") final int station) {
        if (!alertsFireStationService.getPersonFromFireStation(station)
                .getPersonLightFireStationDtoSet().isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(alertsFireStationService
                            .getPersonFromFireStation(station));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @GetMapping("communityEmail")
    public ResponseEntity<Set<String>> getEmailFromCity(@RequestParam final String city) {
        if(!alertsCommunityEmailService.getEmailFromCity(city).isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(alertsCommunityEmailService.getEmailFromCity(city));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @GetMapping("childAlert")
    public ResponseEntity<PersonChildAlertDto>
    getPersonFromAddress(@RequestParam final String address) {
        if(!alertsChildAlertService.getPersonFromAddress(address)
                .getChildren().isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(alertsChildAlertService
                            .getPersonFromAddress(address));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("personInfo")
    public ResponseEntity<PersonInfoDto>
    getAddressByCity(@RequestParam final String firstName,
                     @RequestParam final String lastName) {
        if (alertsPersonInfoService.getPersonInfo(firstName, lastName)
                .getAddress() != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(alertsPersonInfoService.getPersonInfo(firstName, lastName));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @GetMapping("phoneAlert")
    public ResponseEntity<Set<String>>
    getPhoneNumberFromStation(@RequestParam("firestation") final int fireStation) {
        if(!alertPhoneAlertService.getPhoneNumberFromStation(fireStation)
                .isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(alertPhoneAlertService.getPhoneNumberFromStation(fireStation));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("fire")
    public ResponseEntity<PersonFireDto>
    getPersonFromAddressWithStation(@RequestParam final String address) {
        if(!alertsFireService.getPersonFromAddressWithStation(address)
                .getPersonLightFireDtoSet()
                .isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(alertsFireService.getPersonFromAddressWithStation(address));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("flood/stations")
    public ResponseEntity<PersonFloodDto>
    getFamilyByListOfStation(@RequestParam final Set<Integer> stations) {
        if(!alertsFloodService.getFamilyByListOfStation(stations)
                .getSetPersonLightFireDtoSet()
                .isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(alertsFloodService.getFamilyByListOfStation(stations));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
