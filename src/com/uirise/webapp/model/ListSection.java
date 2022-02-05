package com.uirise.webapp.model;

import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection {
    private final List<String> list;

    public ListSection(List<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        String listToString = "";
        for (String detail:list) {
            listToString = listToString + detail + "\r\n";
        }
        return listToString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return list.equals(that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }
}
