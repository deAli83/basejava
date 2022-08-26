package ru.javawebinar.basejava.util;

import ru.javawebinar.basejava.model.*;

public class Web {
    public static String getText(SectionType type, Resume resume) {
        AbstractSection section = resume.getSection(type);
        return section == null ? "" : ((TextSection) resume.getSection(type)).getText();
    }
    public static String getList(SectionType type, Resume resume) {
        AbstractSection section = resume.getSection(type);
        return
                section == null ? "" : String.join("\n", ((ListSection) resume.getSection(type)).getList());
    }

    public static boolean isEmpty(String string) {
        return string == null || string.trim().length() == 0;
    }
}
