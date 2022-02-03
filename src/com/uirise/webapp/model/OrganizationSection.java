package com.uirise.webapp.model;

import java.util.List;
import java.util.Objects;

public class OrganizationSection extends AbstractSection {
    private final List<OrganizationDetails> organizations;

    public OrganizationSection(List<OrganizationDetails> organizations) {
        Objects.requireNonNull(organizations, "organizations must not be null");
        this.organizations = organizations;
    }

    public List<OrganizationDetails> getOrganizations() {
        return organizations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationSection that = (OrganizationSection) o;

        return organizations.equals(that.organizations);

    }

    @Override
    public int hashCode() {
        return organizations.hashCode();
    }

    @Override
    public String toString() {
        String listToString = "";
        for (OrganizationDetails detail:organizations) {
            listToString = listToString + detail.toString() + "\r\n";
        }
        return listToString;
    }
}
