package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.PersonFireDto;

public interface IAlertsFireService {
    /**
     * Get all person living at address provided and the number of the
     * fire station on which they depend.
     *
     * @param address a home address
     * @return a set of person and a station number.
     */
    PersonFireDto getPersonFromAddressWithStation(String address);
}
