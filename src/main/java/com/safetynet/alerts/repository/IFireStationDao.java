package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.FireStation;

import java.util.Set;

public interface IFireStationDao {
    Set<FireStation> getFireStations();
}
