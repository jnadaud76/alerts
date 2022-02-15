package com.safetynet.alerts.service;

import java.util.Set;

public interface IAlertsCommunityEmailService {
    /**
     * Get all email of all person living in a city.
     *
     * @param city a city.
     * @return a set of email.
     */
    Set<String> getEmailFromCity(String city);
}
