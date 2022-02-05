package com.uirise.webapp.model;

import java.util.*;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    // Unique identifier
    private final String uuid;
    private String fullName;

    private final Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private final Map<SectionType, AbstractSection> sections = new EnumMap<>(SectionType.class);

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public String getContact(ContactType type) {
        return contacts.get(type);
    }

    public void setContact(ContactType type, String details) {
        contacts.put(type, details);
    }

    public AbstractSection getSection(SectionType type) {
        return sections.get(type);
    }

    public void setSection(SectionType type, AbstractSection section) {
        sections.put(type, section);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        return (uuid.equals(resume.uuid) && fullName.equals(resume.fullName));
    }

    @Override
    public String toString() {
        StringBuilder resumeToString = new StringBuilder();
        for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
            resumeToString.append(entry.getKey().getTitle()).append(": ").append(entry.getValue()).append("\r\n");
        }

        for (Map.Entry<SectionType, AbstractSection> entry : sections.entrySet()) {
            resumeToString.append(entry.getKey().getTitle()).append(":\r\n").append(entry.getValue().toString()).append("\r\n");
        }
        return "UUID: " + uuid +"\r\n" + "ФИО: " + fullName + "\r\n" + resumeToString;
    }

    @Override
    public int compareTo(Resume o) {
        return uuid.compareTo(o.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName, contacts, sections);
    }
}
