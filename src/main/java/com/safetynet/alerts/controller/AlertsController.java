package com.safetynet.alerts.controller;

import com.safetynet.alerts.dto.PersonChildAlertDto;
import com.safetynet.alerts.dto.PersonFireStationDto;
import com.safetynet.alerts.dto.PersonInfoDto;
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

    @GetMapping("firestation")
    public PersonFireStationDto getPersonFromFireStation (@RequestParam int stationNumber) {
        return alertsFireStationService.getPersonFromFireStation(stationNumber);
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

}
