package com.uirise.webapp.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ListSection extends Section {
    private static final long serialVersionUID = 1L;
    public static final ListSection EMPTY = new ListSection(Arrays.asList(""));


    private List<String> list;

    public ListSection() {
    }
    public ListSection(String[] items) {
        this(Arrays.asList(items));
    }

    public ListSection(List<String> list) {
        Objects.requireNonNull(list, "items must not be null");
        this.list = list;
    }

    public List<String> getList() {
        return list;
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
