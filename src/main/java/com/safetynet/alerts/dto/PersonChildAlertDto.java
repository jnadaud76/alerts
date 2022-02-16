package com.safetynet.alerts.dto;

import java.util.Set;


public class PersonChildAlertDto {
    /**
     * A set of person (children).
     *
     * @see PersonLightChildAlertDto
     */
    private Set<PersonLightChildAlertDto> children;
    /**
     * A set of person (family members of children).
     *
     * @see PersonLightChildAlertDto
     */
    private Set<PersonLightChildAlertDto> familyMembers;


    public Set<PersonLightChildAlertDto> getChildren() {
        return children;
    }

    public void setChildren(Set<PersonLightChildAlertDto> childrenParam) {
        this.children = childrenParam;
    }

    public Set<PersonLightChildAlertDto> getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(final Set<PersonLightChildAlertDto> familyMembersParam) {
        this.familyMembers = familyMembersParam;
    }
}
