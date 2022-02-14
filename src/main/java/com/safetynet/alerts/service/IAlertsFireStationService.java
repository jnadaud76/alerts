package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.PersonFireStationDto;

public interface IAlertsFireStationService {

    PersonFireStationDto getPersonFromFireStation(int station);

}
