package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.PersonInfoDto;

public interface IAlertsPersonInfoService {
    /**
     * Get person's information by id.
     *
     * @param firstName first part of unique id.
     * @param lastName  second part of unique id.
     * @return information about a person.
     */
    PersonInfoDto getPersonInfo(String firstName, String lastName);
}
