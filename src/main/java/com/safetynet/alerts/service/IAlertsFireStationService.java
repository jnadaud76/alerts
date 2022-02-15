package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.PersonFireStationDto;

public interface IAlertsFireStationService {
    /**
     * Get all person who depend on the fire station provided.
     * Also gives a count of the number of adults and children.
     *
     * @param station a station number.
     * @return a set of person with a count of the number of adults and children.
     */
    PersonFireStationDto getPersonFromFireStation(int station);

}
