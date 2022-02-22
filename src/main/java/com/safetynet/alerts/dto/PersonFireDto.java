package com.safetynet.alerts.dto;

import java.util.Set;

public class PersonFireDto {
    /**
     * A set of person.
     *
     * @see PersonLightFireDto
     */
    private Set<PersonLightFireDto> personLightFireDtoSet;
    /**
     * Fire station's number.
     */
    private Set<Integer> station;

    public Set<PersonLightFireDto> getPersonLightFireDtoSet() {
        return personLightFireDtoSet;
    }

    public void setPersonLightFireDtoSet(final Set<PersonLightFireDto> personLightFireDtoSetParam) {
        this.personLightFireDtoSet = personLightFireDtoSetParam;
    }

    public Set<Integer> getStation() {
        return station;
    }

    public void setStation(final Set<Integer> stationParam) {
        this.station = stationParam;
    }

}
