package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.FireStationDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

import lombok.Data;

@Data
@Service
public class FireStationService {

    @Autowired
    FireStationDao fireStationDao;

    public Set<FireStation> getFireStations () {
    return fireStationDao.findAll();
    }

   public Set<FireStation> getFireStationsByStation(final int station){
        return fireStationDao.findByStation(station);
   }

    public FireStation getFireStationsByAddress(final String address){
        return fireStationDao.findByAddress(address);
    }

    /*public void updateFirestation (final int station){

    }*/

    public void deleteFireStationByStation (final int station) throws Exception {
        fireStationDao.deleteByStation(station);
    }
}
