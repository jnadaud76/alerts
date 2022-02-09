package com.safetynet.alerts.service;

import java.util.Set;

public interface IAlertsCommunityEmailService {

    Set<String> getEmailFromCity(String city);
}
