package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.PersonFireDto;

public interface IAlertsFireService {

    PersonFireDto getPersonFromAddressWithStation(String address);
}
