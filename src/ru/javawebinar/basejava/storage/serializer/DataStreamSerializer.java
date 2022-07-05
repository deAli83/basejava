package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamStrategy {

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

            Map<SectionType, AbstractSection> sections = r.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, AbstractSection> entry : sections.entrySet()) {
                SectionType key = entry.getKey();
                dos.writeUTF(key.name());
                switch (key) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((TextSection) r.getSection(key)).getText());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> items = ((ListSection) r.getSection(key)).getList();
                        dos.writeInt(items.size());
                        for (String item : items) {
                            dos.writeUTF(item);
                        }
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        List<Experience> organizations = ((Organization) r.getSection(key)).getOrganizations();
                        dos.writeInt(organizations.size());
                        for (Experience experience : organizations) {
                            dos.writeUTF(experience.getName());
                            dos.writeUTF(experience.getLink());
                            List<Period> periods = experience.getPeriods();
                            dos.writeInt(periods.size());
                            for (Period period : periods) {
                                dos.writeUTF(period.getStartDate().toString());
                                dos.writeUTF(period.getFinishDate().toString());
                                dos.writeUTF(period.getPosition());
                                dos.writeUTF(period.getDescription());
                            }
                        }
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
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.setContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            int sectionsSize = dis.readInt();
            for (int i = 0; i < sectionsSize; i++) {
                SectionType key = SectionType.valueOf(dis.readUTF());
                switch (key) {
                    case PERSONAL:
                    case OBJECTIVE:
                        resume.setSection(key, new TextSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        int listSize = dis.readInt();
                        List<String> items = new ArrayList<>(listSize);
                        for (int j = 0; j < listSize; j++) {
                            items.add(dis.readUTF());
                        }
                        resume.setSection(key, new ListSection(items));
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        int organizationSize = dis.readInt();
                        List<Experience> organizations = new ArrayList<>(organizationSize);
                        for (int y = 0; y < organizationSize; y++) {
                            String name = dis.readUTF();
                            String link = dis.readUTF();
                            List<Period> periods = new ArrayList<>();
                            int periodsSize = dis.readInt();
                            for (int j = 0; j < periodsSize; j++) {
                                periods.add(new Period(
                                        LocalDate.parse(dis.readUTF()),
                                        LocalDate.parse(dis.readUTF()),
                                        dis.readUTF(),dis.readUTF()));
                            }
                            organizations.add(new Experience(name, link, periods));
                        }
                        resume.setSection(key, new Organization(organizations));
                }
            }
            return resume;
        }
    }
}