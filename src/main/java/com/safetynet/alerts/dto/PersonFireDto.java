package com.safetynet.alerts.dto;

import java.util.Set;

public class PersonFireDto {
    /**
     * A set of person.
     *
     * @see PersonLightFireDto
     */
    Set<PersonLightFireDto> personLightFireDtoSet;
    /**
     * Fire station's number.
     */
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
