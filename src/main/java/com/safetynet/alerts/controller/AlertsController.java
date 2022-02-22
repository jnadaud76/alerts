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

/**
 * Many URLS to get information in different ways.
 * See details inside.
 */
@RestController
public class AlertsController {
    /**
     * @see Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AlertsController.class);
    /**
     * @see IAlertsFireStationService
     */
    @Autowired
    private IAlertsFireStationService alertsFireStationService;
    /**
     * @see IAlertsCommunityEmailService
     */
    @Autowired
    private IAlertsCommunityEmailService alertsCommunityEmailService;
    /**
     * @see IAlertsChildAlertService
     */
    @Autowired
    private IAlertsChildAlertService alertsChildAlertService;
    /**
     * @see IAlertsPersonInfoService
     */
    @Autowired
    private IAlertsPersonInfoService alertsPersonInfoService;
    /**
     * @see IAlertsPhoneAlertService
     */
    @Autowired
    private IAlertsPhoneAlertService alertPhoneAlertService;
    /**
     * @see IAlertsFireService
     */
    @Autowired
    private IAlertsFireService alertsFireService;
    /**
     * @see IAlertsFloodService
     */
    @Autowired
    private IAlertsFloodService alertsFloodService;

    /**
     * Access URL=ApiUrl/firestation?stationNumber=station_number
     * <p>
     * Get a set of person covered by the corresponding fire station.
     * So if station number = 1, it should return the inhabitants covered by
     * station number 1. The set must include the following specific
     * information : first name, last name, address, telephone number. What's
     * more, it must provide a count of the number of adults and the number of
     * children (any individual aged 18 or less) in the service area.
     * </p>
     *
     * @param station a station number.
     * @return a set of person with a count of the number of adults and children
     * (JSON format).
     */
    @GetMapping("firestation")
    public ResponseEntity<PersonFireStationDto> getPersonFromFireStation(@RequestParam("stationNumber") final int station) {
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

    /**
     * Access URL=ApiUrl/communityEmail?city=city
     * <p>
     * Get all email of all person living in a city.
     * </p>
     *
     * @param city a city.
     * @return a set of email (JSON format).
     */
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

    /**
     * Access URL=ApiUrl/childAlert?address=address
     * <p>
     * Get a set of children (any individual aged 18 or younger) living at this
     * address.The set should include each child's first and last name, age,
     * and a set of other household members.
     * </p>
     *
     * @param address a home address.
     * @return a set of children and adults living at address (JSON format).
     */
    @GetMapping("childAlert")
    public ResponseEntity<PersonChildAlertDto> getPersonFromAddress(@RequestParam final String address) {
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

    /**
     * Access URL=ApiUrl/personInfo?firstName=firstName&amp;lastName=lastName
     * <p>
     * Get name, address, age, email address and medical history (medications,
     * dosage, allergies) of each inhabitant. If several people have the same
     * name, they must all appear.
     * </p>
     *
     * @param firstName first part of unique id.
     * @param lastName  second part of unique id.
     * @return information about a person (JSON format).
     */

    @GetMapping("personInfo")
    public ResponseEntity<PersonInfoDto> getPersonInfo(@RequestParam final String firstName, @RequestParam final String lastName) {
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

    /**
     * Access URL=ApiUrl/phoneAlert?firestation=firestation_number
     * <p>
     * Get a set of the phone numbers of the residents served by the
     * fire station. We will use it to send emergency text messages to
     * specific households.
     * </p>
     *
     * @param fireStation a fire station number.
     * @return a set of phone numbers (JSON format).
     */
    @GetMapping("phoneAlert")
    public ResponseEntity<Set<String>> getPhoneNumberFromStation(@RequestParam("firestation") final int fireStation) {
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

    /**
     * Access URL=ApiUrl/fire?address=address
     * <p>
     * Get a set of inhabitants living at the given address as well as the
     * barracks number of firefighters serving it. The list should include name,
     * phone number, age and background medical conditions (drugs, dosage and
     * allergies) of each person.
     * </p>
     *
     * @param address a home address.
     * @return a set of person and a station number (JSON format).
     */
    @GetMapping("fire")
    public ResponseEntity<PersonFireDto> getPersonFromAddressWithStation(@RequestParam final String address) {
        PersonFireDto personFireDto = alertsFireService
                .getPersonFromAddressWithStation(address);
        if (!personFireDto.getPersonLightFireDtoSet().isEmpty()) {
            LOGGER.info("Persons successfully found - code : {}", HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(personFireDto);
        } else {
            LOGGER.info("Persons not found - code : {}", HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Access URL=ApiUrl/flood/stations?stations=a list of station_numbers
     * <p>
     * Get a set of all households served by the station. This list should
     * include the person by address. It must also include the name, telephone
     * number and age of the inhabitants, and include their medical history
     * (medications, dosages and allergies) next to each name.
     * </p>
     *
     * @param stations a list of station numbers
     * @return a set of people served by the fire station, grouped by address
     * (JSON format).
     */
    @GetMapping("flood/stations")
    public ResponseEntity<PersonFloodDto> getFamilyByListOfStation(@RequestParam final Set<Integer> stations) {
        PersonFloodDto personFloodDto = alertsFloodService
                .getFamilyByListOfStation(stations);
        if (!personFloodDto.getSetPersonLightFireDtoSet().isEmpty()) {
            LOGGER.info("Households successfully found - code : {}", HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(personFloodDto);
        } else {
            LOGGER.info("Households not found - code : {}", HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
