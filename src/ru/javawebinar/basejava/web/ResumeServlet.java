package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class ResumeServlet extends HttpServlet {
    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getBase();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = response.getWriter();
        List<Resume> resumeList = storage.getAllSorted();

        writer.println("<html><head></head><body>");

        for (Resume resume : resumeList) {

            writer.println("<h3>Full name: " + resume.getFullName() + "</h3>");
            writer.println("UUID=" + resume.getUuid() + "<br>");
            for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
                writer.println(entry.getKey().name() + ":\t" + entry.getValue() + "<br>");
            }
            writer.println("<br>");
            for (Map.Entry<SectionType, AbstractSection> entry : resume.getSections().entrySet()) {
                writer.print(entry.getKey().name() + ":\t");
                writer.print(entry.getKey().name() + ":\t" + "<br>");
                switch (entry.getKey()) {
                    case PERSONAL:
                    case OBJECTIVE:
                        writer.println(((TextSection) entry.getValue()).getText() + "<br>");
                        break;
                    case QUALIFICATIONS:
                    case ACHIEVEMENT:
                        ((ListSection) entry.getValue()).getList().forEach(x -> writer.println(x + "<br>"));
                        break;
                    default:
                        throw new IllegalStateException("Wrong resume section!");
                }
            }

            writer.println("<br><br>");
        }
        writer.println("</body></html>");
    }
}
