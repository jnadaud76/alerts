package com.safetynet.alerts.dto;

import java.util.Set;

public class PersonFloodDto {
    Set<Set<PersonLightFireDto>> setPersonLightFireDtoSet;

    public Set<Set<PersonLightFireDto>> getSetPersonLightFireDtoSet() {
        return setPersonLightFireDtoSet;
    }

    public void setSetPersonLightFireDtoSet(Set<Set<PersonLightFireDto>> setPersonLightFireDtoSetParam) {
        this.setPersonLightFireDtoSet = setPersonLightFireDtoSetParam;
    }

}
