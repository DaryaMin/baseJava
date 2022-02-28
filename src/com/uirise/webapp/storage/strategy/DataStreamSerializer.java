package com.uirise.webapp.storage.strategy;

import com.uirise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            Map<SectionType, Section> sections = r.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                SectionType type = entry.getKey();
                Section section = entry.getValue();
                dos.writeUTF(type.name());
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE:
                    case QUALIFICATIONS:
                        dos.writeUTF(((TextSection) section).getContent());
                        break;
                    case ACHIEVEMENT:
                        int listSize = ((ListSection)section).getList().size();
                        dos.writeInt(listSize);
                        for (int i = 0; i < listSize; i++) {
                            dos.writeUTF(((ListSection) section).getList().get(i));
                        }
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> orgs = ((OrganizationSection) section).getOrganizations();
                        dos.writeInt(orgs.size());
                        for (Organization item : orgs) {
                            dos.writeUTF(item.getHomePage().getName());
                            dos.writeUTF(item.getHomePage().getUrl());
                            dos.writeInt(item.getPositions().size());
                            for (Organization.Position position : item.getPositions()) {
                                writeDate(dos, position.getStartDate());
                                writeDate(dos, position.getEndDate());
                                dos.writeUTF(position.getTitle());
                                dos.writeUTF(position.getDescription());
                            }
                        }
                        break;
                }
            }
        }
    }


    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int contactSize = dis.readInt();
            for (int i = 0; i < contactSize; i++) {
                resume.setContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            int sectionSize = dis.readInt();
            for (int i = 0; i < sectionSize; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                    case QUALIFICATIONS:
                        resume.setSection(sectionType, new TextSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                        int sizeList = dis.readInt();
                        List<String> list = new ArrayList<>(sizeList);
                        for (int j = 0; j < sizeList; j++) {
                            list.add(dis.readUTF());
                        }
                        resume.setSection(sectionType, new ListSection(list));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        int sizeOrg = dis.readInt();
                        List<Organization> organizations = new ArrayList<>(sizeOrg);
                        for (int j = 0; j < sizeOrg; j++) {
                            Link link = new Link(dis.readUTF(), dis.readUTF());
                            int positionSize = dis.readInt();
                            List<Organization.Position> positions = new ArrayList<Organization.Position>(positionSize);
                            for (int k = 0; k < positionSize; k++) {
                                positions.add(new Organization.Position(readDate(dis), readDate(dis), dis.readUTF(), dis.readUTF()));
                            }
                            organizations.add(new Organization(link, positions));
                        }
                        resume.setSection(sectionType, new OrganizationSection(organizations));
                        break;
                    default:
                        throw new IllegalStateException();
                }
            }
            return resume;
        }
    }


    private void writeDate(DataOutputStream dos, LocalDate date) throws IOException {
        dos.writeInt(date.getYear());
        dos.writeInt(date.getMonth().getValue());
    }

    private LocalDate readDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }
}
