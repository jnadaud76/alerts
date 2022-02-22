package com.safetynet.alerts.dto;

import java.util.Set;

public class PersonFloodDto {
    /**
     * A set of set of person.
     *
     * @see PersonLightFireDto
     */
    private Set<Set<PersonLightFireDto>> setPersonLightFireDtoSet;

    public Set<Set<PersonLightFireDto>> getSetPersonLightFireDtoSet() {
        return setPersonLightFireDtoSet;
    }

    public void setSetPersonLightFireDtoSet(final Set<Set<PersonLightFireDto>> setPersonLightFireDtoSetParam) {
        this.setPersonLightFireDtoSet = setPersonLightFireDtoSetParam;
    }

}
