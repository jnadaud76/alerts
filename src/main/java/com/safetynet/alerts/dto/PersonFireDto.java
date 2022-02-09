package com.safetynet.alerts.dto;

import java.util.Set;

public class PersonFireDto {
    Set<PersonLightFireDto> personLightFireDtoSet;
    int station;

    public Set<PersonLightFireDto> getPersonLightFireDtoSet() {
        return personLightFireDtoSet;
    }

    public void setPersonLightFireDtoSet(Set<PersonLightFireDto> personLightFireDtoSetParam) {
        this.personLightFireDtoSet = personLightFireDtoSetParam;
    }

    public int getStation() {
        return station;
    }

    public void setStation(int stationParam) {
        this.station = stationParam;
    }

   /* @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonFireDto that = (PersonFireDto) o;
        return station == that.station && personLightFireDtoSet.equals(that.personLightFireDtoSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personLightFireDtoSet, station);
    }*/
}
