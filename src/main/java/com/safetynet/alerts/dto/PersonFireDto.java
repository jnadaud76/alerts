package com.safetynet.alerts.dto;

import java.util.Objects;
import java.util.Set;

public class PersonFireDto {
    Set<PersonLightFireDto> personLightFireDtoSet;
    int station;

    public Set<PersonLightFireDto> getPersonLightFireDtoSet() {
        return personLightFireDtoSet;
    }

    public void setPersonLightFireDtoSet(Set<PersonLightFireDto> personLightFireDtoSet) {
        this.personLightFireDtoSet = personLightFireDtoSet;
    }

    public int getStation() {
        return station;
    }

    public void setStation(int station) {
        this.station = station;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonFireDto that = (PersonFireDto) o;
        return station == that.station && personLightFireDtoSet.equals(that.personLightFireDtoSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personLightFireDtoSet, station);
    }
}
