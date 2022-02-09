package com.uirise.webapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrganizationDetails {
    private final String name;
    private List<PositionDetails> positions = new ArrayList<>();
    
    public OrganizationDetails(String name, List<PositionDetails> positions) {
        this.name = name;
        this.positions = positions;
    }

    public String getName() {
        return name;
    }

    public List<PositionDetails> getPositions() {
        return positions;
    }

    @Override
    public String toString() {
        String allPositions = "";
        for (PositionDetails positions: positions) {
            allPositions += positions.toString() + "\r\n";
        }
        return name + "\r\n" + allPositions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationDetails that = (OrganizationDetails) o;
        return name.equals(that.name) && positions.equals(that.positions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, positions);
    }

    public static class PositionDetails {
        private final LocalDate startDate;
        private final LocalDate endDate;
        private final String description;
        private final String details;

        public PositionDetails(LocalDate startDate, LocalDate endDate, String description, String details) {
            this.startDate = startDate;
            this.endDate = endDate;
            this.description = description;
            this.details = details;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public String getDescription() {
            return description;
        }

        public String getDetails() {
            return details;
        }

        @Override
        public String toString() {
            return "c " + startDate + " по " + endDate + " " + description + "\r\n" + details;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PositionDetails that = (PositionDetails) o;
            return startDate.equals(that.startDate) && endDate.equals(that.endDate) && description.equals(that.description) && Objects.equals(details, that.details);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startDate, endDate, description, details);
        }
    }

}
