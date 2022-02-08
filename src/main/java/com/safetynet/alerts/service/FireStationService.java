package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.FireStationFullDto;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.repository.FireStationDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


@Service
public class FireStationService {

    @Autowired
    FireStationDao fireStationDao;

    //Set<FireStationFullDto> fireStationFullDtoSet = new HashSet<>();

    public Set<FireStationFullDto> getFireStations () {
    Set <FireStation> fireStations = fireStationDao.findAll();
    Set<FireStationFullDto> fireStationFullDtoSet = new HashSet<>();

        for (FireStation fireStation : fireStations) {
            FireStationFullDto firestationFullDto = new FireStationFullDto();
            firestationFullDto.setStation(fireStation.getStation());
            firestationFullDto.setAddress(fireStation.getAddress());
            fireStationFullDtoSet.add(firestationFullDto);
        }
    return fireStationFullDtoSet;
    }

    /*public Set<FireStationFullDto> findByStationSet (final Set<Integer> stations) {
        Set <FireStation> fireStations = fireStationDao.findByStationSet(stations);
        Set<FireStationFullDto> fireStationFullDtoSet = new HashSet<>();

        for (FireStation fireStation : fireStations) {
            FireStationFullDto firestationFullDto = new FireStationFullDto();
            firestationFullDto.setStation(fireStation.getStation());
            firestationFullDto.setAddress(fireStation.getAddress());
            fireStationFullDtoSet.add(firestationFullDto);
        }
        return fireStationFullDtoSet;

    }*/

   public Set<FireStationFullDto> getFireStationsByStation(final int station){
       Set <FireStation> fireStations = fireStationDao.findByStation(station);
       Set<FireStationFullDto> fireStationFullDtoSet = new HashSet<>();

       for (FireStation fireStation : fireStations) {
           FireStationFullDto firestationFullDto = new FireStationFullDto();
           firestationFullDto.setStation(fireStation.getStation());
           firestationFullDto.setAddress(fireStation.getAddress());
           fireStationFullDtoSet.add(firestationFullDto);
       }
       return fireStationFullDtoSet;
   }



    public Set<FireStationFullDto> getFireStationsByAddress(final String address){
        Set <FireStation> fireStations = fireStationDao.findByAddress(address);
        Set<FireStationFullDto> fireStationFullDtoSet = new HashSet<>();

        for (FireStation fireStation : fireStations) {
            FireStationFullDto firestationFullDto = new FireStationFullDto();
            firestationFullDto.setStation(fireStation.getStation());
            firestationFullDto.setAddress(fireStation.getAddress());
            fireStationFullDtoSet.add(firestationFullDto);
        }
        return fireStationFullDtoSet;
    }


    public void updateFirestation (final FireStation fireStation) {
        fireStationDao.update(fireStation);

    }

    public void createFireStation (final FireStation fireStation) {
        fireStationDao.save(fireStation);
    }

    public void deleteFireStationByStation (final int station) {
        fireStationDao.deleteByStation(station);
    }

    public void deleteFireStationByAddress (final String address) {
        fireStationDao.deleteByAddress(address);
    }
}
