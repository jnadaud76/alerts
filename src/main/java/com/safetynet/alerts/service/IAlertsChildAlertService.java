package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.PersonChildAlertDto;

public interface IAlertsChildAlertService {
    /**
     * Get children and adults living at address.
     *
     * @param address a home address.
     * @return a set of children and adults living at address.
     */
    PersonChildAlertDto getPersonFromAddress(String address);
}
