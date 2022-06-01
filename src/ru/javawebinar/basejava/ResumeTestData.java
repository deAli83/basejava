package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResumeTestData {

    public static Resume generate(String uuid, String fullname) {
        Resume resume = new Resume(uuid, fullname);
        resume.setContact(ContactType.PHONE, "phone number" + uuid);
        resume.setContact(ContactType.SKYPE, "skype" + uuid);
        resume.setContact(ContactType.EMAIL, "email" + uuid);
        resume.setContact(ContactType.LINKEDIN, "linkedin" + uuid);
        resume.setContact(ContactType.GITHUB, "github" + uuid);
        resume.setContact(ContactType.STACKOVERFLOW, "stackoverflow" + uuid);
        resume.setContact(ContactType.HOMEPAGE, "homepage" + uuid);

        resume.setSection(SectionType.PERSONAL, new TextSection("personal characteristic" + uuid));
        resume.setSection(SectionType.OBJECTIVE, new TextSection("objective" + uuid));

        String[] achievements = {"achievement" + uuid + " 1", "achievement" + uuid + " 2"};
        resume.setSection(SectionType.ACHIEVEMENT, new ListSection(Arrays.asList(achievements)));

        String[] qualifications = {"qualification" + uuid + " 1", "qualification" + uuid + " 2"};
        resume.setSection(SectionType.QUALIFICATIONS, new ListSection(Arrays.asList(qualifications)));

        Period period1Job1 = new Period(
                LocalDate.of(2013, 10, 1),
                LocalDate.now(),
                "position" + uuid,
                "description" + uuid);

        List<Period> periodsJob1 = new ArrayList<>();
        periodsJob1.add(period1Job1);
        Experience job1 = new Experience("Organization" + uuid + " 1", "link", periodsJob1);

        Period period1Job2 = new Period(
                LocalDate.of(2010, 8, 1),
                LocalDate.of(2013, 10, 1),
                "position" + uuid,
                "description" + uuid);

        List<Period> periodsJob2 = new ArrayList<>();
        periodsJob2.add(period1Job2);
        Experience job2 = new Experience("Organization" + uuid + " 2", "link", periodsJob2);

        List<Experience> experienceJob = new ArrayList<>();
        experienceJob.add(job1);
        experienceJob.add(job2);

        resume.setSection(SectionType.EXPERIENCE, new Organization(experienceJob));

        Period period1Education1 = new Period(
                LocalDate.of(2007, 6, 1),
                LocalDate.of(2008, 6, 1),
                "description" + uuid);

        List<Period> periodsEducation1 = new ArrayList<>();
        periodsEducation1.add(period1Education1);
        Experience education1 = new Experience("Education Organization" + uuid + " 1", "link", periodsEducation1);

        Period period1Education2 = new Period(
                LocalDate.of(2000, 9, 1),
                LocalDate.of(2005, 5, 1),
                "position" + uuid,
                "description" + uuid);

        List<Period> periodsEducation2 = new ArrayList<>();
        periodsEducation2.add(period1Education2);
        Experience education2 = new Experience("Education Organization" + uuid + " 2", "link", periodsEducation2);

        List<Experience> experienceEducation = new ArrayList<>();
        experienceJob.add(education1);
        experienceJob.add(education2);

        resume.setSection(SectionType.EDUCATION, new Organization(experienceEducation));

        return resume;
    }
}
