package com.uirise.webapp.model;

import java.time.LocalDate;

public class OrganizationDetails {
    private final String name;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String position;
    private final String description;


    public OrganizationDetails(String name, LocalDate startDate, LocalDate endDate, String position, String description) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.position = position;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationDetails organizationDetails = (OrganizationDetails) o;

        return (name.equals(organizationDetails.name)
                && startDate.equals(organizationDetails.startDate)
                && endDate.equals(organizationDetails.endDate)
                && position.equals(organizationDetails.position)
                && description.equals(organizationDetails.description));
    }

    @Override
    public String toString() {
        return name + "\r\nc " + startDate + " по " + endDate + " " + position + "\r\n" + description;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + startDate.hashCode() + endDate.hashCode() + position.hashCode() + description.hashCode();
    }
}
