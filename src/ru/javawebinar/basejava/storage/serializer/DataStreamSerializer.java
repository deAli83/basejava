package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
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
            writeSection(contacts.entrySet(), dos, entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());});

            Map<SectionType, AbstractSection> sections = r.getSections();
            dos.writeInt(sections.size());
            writeSection(sections.entrySet(), dos, entry -> {
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
                        writeSection(((ListSection) r.getSection(key)).getList(), dos, item -> {
                            dos.writeUTF(item);
                        });
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        List<Experience> organizations = ((Organization) r.getSection(key)).getOrganizations();
                        dos.writeInt(organizations.size());
                        writeSection(((Organization) r.getSection(key)).getOrganizations(), dos, experience -> {
                            dos.writeUTF(experience.getName());
                            String link = experience.getLink();
                            dos.writeUTF(link == null ? "" : link);
                            List<Period> periods = experience.getPeriods();
                            dos.writeInt(periods.size());
                            writeSection(experience.getPeriods(), dos, period -> {
                                dos.writeUTF(period.getStartDate().toString());
                                dos.writeUTF(period.getFinishDate().toString());
                                dos.writeUTF(period.getPosition());
                                String description = period.getDescription();
                                dos.writeUTF(description == null ? "" : description);});
                        });
                }
            });
        }
    }

    private <E> void writeSection (Collection <E> section, DataOutputStream dos, WriteElement<E> writer) throws IOException {
        for (E e : section) {
            writer.write(e);
        }
    }

    private interface WriteElement<E> {
        void write(E e) throws IOException;
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readResume(resume, dis, r -> resume.setContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));


            readResume(resume, dis, r -> {
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
                                        dis.readUTF(),
                                        dis.readUTF()));
                            }
                            organizations.add(new Experience(name, link, periods));
                        }
                        resume.setSection(key, new Organization(organizations));
                }
            });
            return resume;
        }
    }

    private void readResume (Resume r, DataInputStream dis, ResumeCollect<Resume> collector) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            collector.collect(r);
        }
    }

    private interface ResumeCollect<Resume> {
        void collect(Resume r) throws IOException;
    }

}