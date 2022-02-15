package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.PersonFloodDto;
import com.safetynet.alerts.dto.PersonLightFireDto;

import java.util.Set;

public interface IAlertsFloodService {
    /**
     * Get a set of people by address.
     *
     * @param address a home address.
     * @return a set of person.
     */
    Set<PersonLightFireDto> getFamilyFromAddress(String address);

    /**
     * Get a set of people served by the fire station, grouped by address.
     *
     * @param stations a set of station number.
     * @return a set of people served by the fire station, grouped by address.
     */
    PersonFloodDto getFamilyByListOfStation(Set<Integer> stations);
}
