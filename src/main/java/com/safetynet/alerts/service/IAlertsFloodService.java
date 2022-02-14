package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.PersonFloodDto;
import com.safetynet.alerts.dto.PersonLightFireDto;

import java.util.Set;

public interface IAlertsFloodService {

    Set<PersonLightFireDto> getFamilyFromAddress(String address);
    PersonFloodDto getFamilyByListOfStation(Set<Integer> stations);
}
