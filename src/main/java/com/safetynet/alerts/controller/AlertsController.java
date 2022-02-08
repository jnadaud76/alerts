package com.safetynet.alerts.controller;

import com.safetynet.alerts.dto.PersonChildAlertDto;
import com.safetynet.alerts.dto.PersonFireDto;
import com.safetynet.alerts.dto.PersonFireStationDto;
import com.safetynet.alerts.dto.PersonFloodDto;
import com.safetynet.alerts.dto.PersonInfoDto;
import com.safetynet.alerts.service.AlertsFireService;
import com.safetynet.alerts.service.AlertsFloodService;
import com.safetynet.alerts.service.AlertsPhoneAlertService;
import com.safetynet.alerts.service.AlertsChildAlertService;
import com.safetynet.alerts.service.AlertsCommunityEmailService;
import com.safetynet.alerts.service.AlertsFireStationService;
import com.safetynet.alerts.service.AlertsPersonInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class AlertsController {

    @Autowired
    AlertsFireStationService alertsFireStationService;

    @Autowired
    AlertsCommunityEmailService alertsCommunityEmailService;

    @Autowired
    AlertsChildAlertService alertsChildAlertService;

    @Autowired
    AlertsPersonInfoService alertsPersonInfoService;

    @Autowired
    AlertsPhoneAlertService alertPhoneAlertService;

    @Autowired
    AlertsFireService alertsFireService;

    @Autowired
    AlertsFloodService alertsFloodService;

    @GetMapping("firestation")
    public PersonFireStationDto getPersonFromFireStation (@RequestParam ("stationNumber") int station) {
        return alertsFireStationService.getPersonFromFireStation(station);
    }

    @GetMapping("communityEmail")
    public Set<String> getEmailFromCity(@RequestParam String city) {
        return alertsCommunityEmailService.getEmailFromCity(city);
    }

    @GetMapping("childAlert")
    public PersonChildAlertDto getPersonFromAddress (@RequestParam String address) {
        return alertsChildAlertService.getPersonFromAddress(address);
    }

    @GetMapping("personInfo")
    public PersonInfoDto getAddressByCity(@RequestParam String firstName, String lastName) {
        return alertsPersonInfoService.getPersonInfo(firstName, lastName);
    }

    @GetMapping ("phoneAlert")
    public Set<String> getPhoneNumberFromStation (@RequestParam ("firestation" )int fireStation) {
        return alertPhoneAlertService.getPhoneNumberFromStation(fireStation);
    }

    @GetMapping ("fire")
    public PersonFireDto getPersonFromAddressWithStation (@RequestParam String address) {
        return alertsFireService.getPersonFromAddressWithStation(address);
    }

    @GetMapping ("flood/stations")
    public PersonFloodDto getFamilyByListOfStation (@RequestParam Set<Integer> stations){
        return alertsFloodService.getFamilyByListOfStation(stations);
    }

}
