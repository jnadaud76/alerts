package com.safetynet.alerts.dto;

import java.util.Set;

public class PersonFireDto {
    Set<PersonLightFireDto> personLightFireDtoSet;
    int station;

    public Set<PersonLightFireDto> getPersonLightFireDtoSet() {
        return personLightFireDtoSet;
    }

    public void setPersonLightFireDtoSet(final Set<PersonLightFireDto> personLightFireDtoSetParam) {
        this.personLightFireDtoSet = personLightFireDtoSetParam;
    }

    public int getStation() {
        return station;
    }

    public void setStation(final int stationParam) {
        this.station = stationParam;
    }

}
