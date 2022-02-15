package com.safetynet.alerts.service;

import java.util.Set;

public interface IAlertsPhoneAlertService {
    /**
     * Get all phone numbers of person served by the fire station.
     *
     * @param station a fire station number.
     * @return a set of phone numbers.
     */
    Set<String> getPhoneNumberFromStation(int station);
}
