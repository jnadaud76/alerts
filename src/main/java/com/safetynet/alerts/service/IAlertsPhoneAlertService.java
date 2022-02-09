package com.safetynet.alerts.service;

import java.util.Set;

public interface IAlertsPhoneAlertService {

    Set<String> getPhoneNumberFromStation(int station);
}
