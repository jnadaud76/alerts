package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.PersonInfoDto;

public interface IAlertsPersonInfoService {

    PersonInfoDto getPersonInfo(String firstname, String lastName);
}
