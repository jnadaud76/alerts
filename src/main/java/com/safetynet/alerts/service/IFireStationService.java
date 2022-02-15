package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.FireStationFullDto;
import com.safetynet.alerts.model.FireStation;

import java.util.Set;

public interface IFireStationService {
    /**
     * Get all fireStation.
     *
     * @return a set of fireStation.
     */
    Set<FireStationFullDto> getFireStations();

    /**
     * Get a set of fireStation by station number.
     *
     * @param station a station number.
     * @return a set of fireStation.
     */
    Set<FireStationFullDto> getFireStationsByStation(int station);

    /**
     * Get a set of fireStation by address.
     *
     * @param address a fireStation address.
     * @return a set of fireStation.
     */
    Set<FireStationFullDto> getFireStationsByAddress(String address);

    /**
     * Create a fireStation.
     *
     * @param fireStation a fireStation to add to the set.
     */
    void createFireStation(FireStation fireStation);

    /**
     * Update a fireStation.
     *
     * @param fireStation a fireStation to modify.
     */
    void updateFirestation(FireStation fireStation);

    /**
     * Delete a fireStation by station number.
     *
     * @param station station number to delete.
     */
    void deleteFireStationByStation(int station);

    /**
     * Delete a fireStation by address.
     *
     * @param address fireStation address to delete.
     */
    void deleteFireStationByAddress(String address);

}
