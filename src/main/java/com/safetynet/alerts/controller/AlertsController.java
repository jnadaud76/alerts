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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class AlertsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlertsController.class);

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
        PersonFireStationDto personFireStationDto = alertsFireStationService
                .getPersonFromFireStation(station);
        if (!personFireStationDto.getPersonLightFireStationDtoSet().isEmpty()) {
            LOGGER.info("Persons successfully found - code : {}", HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(personFireStationDto);
        } else {
            LOGGER.error("Persons not found - code : {}", HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @GetMapping("communityEmail")
    public ResponseEntity<Set<String>> getEmailFromCity(@RequestParam final String city) {
        if (!alertsCommunityEmailService.getEmailFromCity(city).isEmpty()) {
            LOGGER.info("Emails successfully found - code : {}", HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(alertsCommunityEmailService.getEmailFromCity(city));
        } else {
            LOGGER.error("Emails not found - code : {}", HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @GetMapping("childAlert")
    public ResponseEntity<PersonChildAlertDto>
    getPersonFromAddress(@RequestParam final String address) {
        PersonChildAlertDto personChildAlertDto = alertsChildAlertService
                .getPersonFromAddress(address);
        if (!personChildAlertDto.getChildren().isEmpty()) {
            LOGGER.info("Children successfully found - code {}", HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(personChildAlertDto);
        } else {
            LOGGER.error("Children not found - code : {}", HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("personInfo")
    public ResponseEntity<PersonInfoDto>
    getAddressByCity(@RequestParam final String firstName,
                     @RequestParam final String lastName) {
        PersonInfoDto personInfoDto = alertsPersonInfoService
                .getPersonInfo(firstName, lastName);
        if (personInfoDto.getAddress() != null) {
            LOGGER.info("Person successfully found - code : {}", HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(personInfoDto);
        } else {
            LOGGER.error("Person not found - code : {}", HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @GetMapping("phoneAlert")
    public ResponseEntity<Set<String>>
    getPhoneNumberFromStation(@RequestParam("firestation") final int fireStation) {
        if (!alertPhoneAlertService.getPhoneNumberFromStation(fireStation)
                .isEmpty()) {
            LOGGER.info("Phonenumbers successfully found - code : {}", HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(alertPhoneAlertService.getPhoneNumberFromStation(fireStation));
        } else {
            LOGGER.error("Phonenumbers not found - code : {}", HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("fire")
    public ResponseEntity<PersonFireDto>
    getPersonFromAddressWithStation(@RequestParam final String address) {
        PersonFireDto personFireDto = alertsFireService
                .getPersonFromAddressWithStation(address);
        if (!personFireDto.getPersonLightFireDtoSet().isEmpty()) {
            LOGGER.info("Persons successfully found - code : {}", HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(personFireDto);
        } else {
            LOGGER.info("Persons not found - code : {}", HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("flood/stations")
    public ResponseEntity<PersonFloodDto>
    getFamilyByListOfStation(@RequestParam final Set<Integer> stations) {
        PersonFloodDto personFloodDto = alertsFloodService
                .getFamilyByListOfStation(stations);
        if (!personFloodDto.getSetPersonLightFireDtoSet().isEmpty()) {
            LOGGER.info("Households successfully found - code : {}", HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(personFloodDto);
        } else {
            LOGGER.info("Households not found - code : {}", HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
