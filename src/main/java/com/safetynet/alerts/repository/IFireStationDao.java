package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.FireStation;

import java.util.Set;

public interface IFireStationDao {

    Set<FireStation> getFireStations();

    void setFireStations(Set<FireStation> fireStationsParam);

    /**
     * CRUD method which find all fireStation.
     *
     * @return all fireStation.
     */
    Set<FireStation> findAll();

    /**
     * CRUD method which find a set of fireStation from a station number.
     *
     * @param stationParam a station number.
     * @return a set of fireStation found.
     */
    Set<FireStation> findByStation(int stationParam);

    /**
     * CRUD method which find a set of fireStation from an address.
     *
     * @param addressParam a fireStation address.
     * @return a set of fireStation found.
     */
    Set<FireStation> findByAddress(String addressParam);

    /**
     * CRUD method which update a fireStation.
     *
     * @param fireStationParam a fireStation.
     */
    void update(FireStation fireStationParam);

    /**
     * CRUD method which save a fireStation.
     *
     * @param fireStationParam a fireStation.
     */
    void save(FireStation fireStationParam);

    /**
     * CRUD method which delete a fireStation from a station number.
     *
     * @param stationParam station number.
     */
    void deleteByStation(int stationParam);

    /**
     * CRUD method which delete a fireStation from address.
     *
     * @param addressParam fireStation address.
     */
    void deleteByAddress(String addressParam);

}
