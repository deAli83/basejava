package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.storage.Storage;
import ru.javawebinar.basejava.util.Date;
import ru.javawebinar.basejava.util.Web;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ResumeServlet extends HttpServlet {
    private Storage storage;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getBase();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        Resume r;
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        boolean resumeExists = uuid != null && uuid.length() > 0;

        if (resumeExists) {
            r = storage.get(uuid);
            r.setFullName(fullName);
            editResume(request, r);
        } else {
            r = new Resume(fullName);
            editResume(request, r);
        }

        if (!resumeExists) {
            storage.save(r);
        } else {
            storage.update(r);
        }

        response.sendRedirect("resume");
    }
    private void editResume(HttpServletRequest request, Resume r) {

        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                r.setContact(type, value);
            } else {
                r.getContacts().remove(type);
            }
        }

        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name());
            String[] values = request.getParameterValues(type.name());
            value = value.replaceAll("\\r","");
            List<String> notNullValuesList = Arrays.stream(value.split("\n"))
                    .filter(x -> x.trim().length() > 0)
                    .collect(Collectors.toList());
            if (notNullValuesList.size() == 0) {
                r.getSections().remove(type);
                continue;
            }
            if (value != null && value.trim().length() != 0) {
                switch (type) {
                    case OBJECTIVE:
                    case PERSONAL:
                        r.setSection(type, new TextSection(value));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        r.setSection(type, new ListSection(notNullValuesList));
//                        if (values != null) {
//                            List<String> list = Arrays.stream(values).map(String::trim).filter(s -> !s.equals("")).collect(Collectors.toList());
//                            if (!list.isEmpty())
//                                r.setSection(type, new ListSection(List.of(value.split("\\n"))));
//                        }
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        if (values != null) {
                            List<Experience> experienceList = formExperienceList(request, type, values);
                            if (experienceList != null) {
                                r.setSection(type, new Organization(experienceList));
                            }
                        }
                        break;
                }
            } else {
                r.getSections().remove(type);
            }
        }
    }

    private List<Experience> formExperienceList(HttpServletRequest request, SectionType type, String[] values) {
        List<Experience> experienceList = new ArrayList<>();
        List<Period> periodsList;
        String[] link = request.getParameterValues(type.name() + "url");
        for (int i = 0; i < values.length; i++) {
            String name = values[i];
            if (!Web.isEmpty(name)) {
                periodsList = new ArrayList<>();
                String index = type.name() + i;
                String[] start = request.getParameterValues(index + "startDate");
                String[] finish = request.getParameterValues(index + "finishDate");
                String[] positions = request.getParameterValues(index + "position");
                String[] descriptions = request.getParameterValues(index + "description");
                for (int j = 0; j < positions.length; j++) {
                    if (!Web.isEmpty(positions[j])) {
                        periodsList.add(new Period(Date.parse(start[j]), Date.parse(finish[j]), positions[j], descriptions[j]));
                    }
                }
                experienceList.add(new Experience(name, link[i], periodsList));
            }
        }
        return experienceList.isEmpty() ? null : experienceList;
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume r;
        switch (action) {
            case "add":
                r = Resume.EMPTY;
                break;
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "view":
                r = storage.get(uuid);
                break;
            case "edit":
                r = storage.get(uuid);
                for (SectionType type : SectionType.values()) {
                    AbstractSection section = r.getSection(type);
                    switch (type) {
                        case OBJECTIVE:
                        case PERSONAL:
                            if (section == null) {
                                section = TextSection.EMPTY;
                            }
                            break;
                        case ACHIEVEMENT:
                        case QUALIFICATIONS:
                            if (section == null) {
                                section = ListSection.EMPTY;
                            }
                            break;
                        case EXPERIENCE:
                        case EDUCATION:
                            Organization orgSection = (Organization) section;
                            List<Experience> emptyExperience = new ArrayList<>();
                            emptyExperience.add(Experience.EMPTY);
                            if (orgSection != null) {
                                for (Experience org : orgSection.getOrganizations()) {
                                    List<Period> emptyPeriod = new ArrayList<>();
                                    emptyPeriod.add(Period.EMPTY);
                                    emptyPeriod.addAll(org.getPeriods());
                                    emptyExperience.add(new Experience(org.getName(), org.getName(), emptyPeriod));
                                }
                            }
                            section = new Organization(emptyExperience);
                            break;
                    }
                    r.setSection(type, section);
                }
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", r);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
        ).forward(request, response);
    }
}
