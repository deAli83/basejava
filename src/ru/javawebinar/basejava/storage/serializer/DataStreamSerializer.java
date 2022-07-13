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
            writeSection(contacts.entrySet(), dos, entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());});

            Map<SectionType, AbstractSection> sections = r.getSections();
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
                        writeSection(((ListSection) r.getSection(key)).getList(), dos, item -> {
                            dos.writeUTF(item);
                        });
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        writeSection(((Organization) r.getSection(key)).getOrganizations(), dos, experience -> {
                            dos.writeUTF(experience.getName());
                            String link = experience.getLink();
                            dos.writeUTF(link == null ? "" : link);
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
        dos.writeInt(section.size());
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
                        resume.setSection(key, new ListSection(readList(dis, dis::readUTF)));
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        resume.setSection(key, new Organization(readList(dis, () -> new Experience(dis.readUTF(), dis.readUTF(),
                            readList(dis, () -> new Period(
                                LocalDate.parse(dis.readUTF()),
                                LocalDate.parse(dis.readUTF()),
                                dis.readUTF(),
                                dis.readUTF())))
                                ))
                        );
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

    private <E> List<E> readList (DataInputStream dis, ListCollect<E> listCollector) throws IOException {
        int size = dis.readInt();
        List<E> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(listCollector.collect());
        }
        return list;
    }

    private interface ListCollect<E> {
        E collect() throws IOException;
    }
}