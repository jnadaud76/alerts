package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.PersonChildAlertDto;

public interface IAlertsChildAlertService {

    PersonChildAlertDto getPersonFromAddress(String address);
}
