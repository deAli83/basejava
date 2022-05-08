package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.util.Arrays;
import java.util.HashMap;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume = new Resume("Григорий Кислин");

        resume.setContact(ContactType.PHONE, "+7(921) 855-0482" );
        resume.setContact(ContactType.SKYPE, "skype:grigory.kislin" );
        resume.setContact(ContactType.EMAIL,"gkislin@yandex.ru" );
        resume.setContact(ContactType.LINKEDIN,"https://www.linkedin.com/in/gkislin" );
        resume.setContact(ContactType.GITHUB,"https://github.com/gkislin" );
        resume.setContact(ContactType.STACKOVERFLOW,"https://stackoverflow.com/users/548473" );
        resume.setContact(ContactType.HOMEPAGE,"http://gkislin.ru/" );

        resume.setSection(SectionType.PERSONAL,new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        resume.setSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));

        String[] achievements = {
                "Организация команды и успешная реализация Java проектов для сторонних заказчиков: приложения автопарк" +
                " на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов на Spring Boot, " +
                "участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для комплексных DIY смет",

                "Организация команды и успешная реализация Java проектов для " +
                "сторонних заказчиков: приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга " +
                "показателей спортсменов на Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot + " +
                "Vaadin проект для комплексных DIY смет", "С 2013 года: разработка проектов \"Разработка Web " +
                "приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). " +
                "Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". " +
                "Организация онлайн стажировок и ведение проектов. Более 3500 выпускников.",

                "Реализация двухфакторной " +
                "аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, " +
                "Google Authenticator, Jira, Zendesk.",

                "Налаживание процесса разработки и непрерывной интеграции " +
                "ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления " +
                "окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных " +
                "ERP модулей, интеграция CIFS/SMB java сервера.",

                "Реализация c нуля Rich Internet Application " +
                "приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock " +
                "для алгоритмического трейдинга.",

                "Создание JavaEE фреймворка для отказоустойчивого взаимодействия " +
                "слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). " +
                "Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. " +
                "Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).",

                "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, " +
                "Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа."};

        String[] qualifications = {
                "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",

                "Version control: Subversion, Git, Mercury, ClearCase, Perforce",

                "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB",

                "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy",

                "XML/XSD/XSLT, SQL, C/C++, Unix shell scripts", "Java Frameworks: Java 8 " +
                "(Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), " +
                "JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, " +
                "Eclipse SWT, JUnit, Selenium (htmlelements).",

                "Python: Django", "JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js",

                "Scala: SBT, Play2, Specs2, Anorm, Spray, Akka",

                "Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, " +
                "JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.",

                "Инструменты: Maven + plugin development, Gradle, настройка Ngnix",

                "администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer",

                "Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, " +
                "функционального программирования", "Родной русский, английский \"upper intermediate\""};

        Skill skillExperience1 = new Skill(
                "10/2013 - Сейчас",
                "Java Online Projects",
                "Автор проекта.",
                "Создание, организация и проведение Java онлайн проектов и стажировок.");

        Skill skillExperience2 = new Skill(
                "10/2014 - 01/2016",
                "Wrike",
                "Старший разработчик (backend)",
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, " +
                "pring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, " +
                "авторизация по OAuth1, OAuth2, JWT SSO.");

        Skill skillExperience3 = new Skill(
                "04/2012 - 10/2014",
                "RIT Center",
                "Java архитектор",
                "Организация процесса разработки системы ERP для разных окружений: релизная политика, " +
                "версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы " +
                "(pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных " +
                "сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html)." +
                " Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. " +
                "Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, " +
                "OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python");

        Skill skillExperience4 = new Skill(
                "12/2010 - 04/2012",
                "Luxoft (Deutsche Bank)",
                "Ведущий программист",
                "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper," +
                " Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования," +
                " мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT," +
                " ExtGWT (GXT), Highstock, Commet, HTML5.");

        Skill skillExperience5 = new Skill(
                "06/2008 - 12/2010",
                "Yota",
                "Ведущий специалист",
                "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J," +
                " EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики" +
                " и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)");

        Skill skillExperience6 = new Skill(
                "03/2007 - 06/2008",
                "Enkata",
                "Разработчик ПО",
                "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей" +
                " кластерного J2EE приложения (OLAP, Data mining).");

        Skill skillExperience7 = new Skill(
                "01/2005 - 02/2007",
                "Siemens AG",
                "Разработчик ПО",
                "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной" +
                " IN платформе Siemens @vantage (Java, Unix).");

        Skill skillExperience8 = new Skill(
                "09/1997 - 01/2005",
                "Alcatel",
                "Инженер по аппаратному и программному тестированию",
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).");

        Skill skillEducation1 = new Skill(
                "03/2013 - 05/2013",
                "Coursera",
                "'Functional Programming Principles in Scala' by Martin Odersky");

        Skill skillEducation2 = new Skill(
                "03/2011 - 04/2011",
                "Luxoft",
                "Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'");

        Skill skillEducation3 = new Skill(
                "01/2005 - 04/2005",
                "Siemens AG",
                "3 месяца обучения мобильным IN сетям (Берлин)");

        Skill skillEducation4 = new Skill(
                "09/1997 - 03/1998",
                "Alcatel",
                "6 месяцев обучения цифровым телефонным сетям (Москва)");

        Skill skillEducation5 = new Skill(
                "09/1993 - 07/1996",
                "Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики",
                "Аспирантура (программист С, С++)");

        Skill skillEducation6 = new Skill(
                "09/1987 - 07/1993",
                "Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики",
                "Инженер (программист Fortran, C)");

        Skill skillEducation7 = new Skill(
                "09/1984 - 06/1987",
                "Заочная физико-техническая школа при МФТИ",
                "Закончил с отличием");

        HashMap<String, Skill> skillsExperience = new HashMap<>();
        skillsExperience.put(skillExperience1.getDate(), skillExperience1);
        skillsExperience.put(skillExperience2.getDate(), skillExperience2);
        skillsExperience.put(skillExperience3.getDate(), skillExperience3);
        skillsExperience.put(skillExperience4.getDate(), skillExperience4);
        skillsExperience.put(skillExperience5.getDate(), skillExperience5);
        skillsExperience.put(skillExperience6.getDate(), skillExperience6);
        skillsExperience.put(skillExperience7.getDate(), skillExperience7);
        skillsExperience.put(skillExperience8.getDate(), skillExperience8);

        HashMap<String, Skill> skillsEducation = new HashMap<>();
        skillsEducation.put(skillEducation1.getDate(), skillEducation1);
        skillsEducation.put(skillEducation2.getDate(), skillEducation2);
        skillsEducation.put(skillEducation3.getDate(), skillEducation3);
        skillsEducation.put(skillEducation4.getDate(), skillEducation4);
        skillsEducation.put(skillEducation5.getDate(), skillEducation5);
        skillsEducation.put(skillEducation6.getDate(), skillEducation6);
        skillsEducation.put(skillEducation7.getDate(), skillEducation7);

        resume.setSection(SectionType.ACHIEVEMENT, new ListSection(Arrays.asList(achievements)));
        resume.setSection(SectionType.QUALIFICATIONS, new ListSection(Arrays.asList(qualifications)));
        resume.setSection(SectionType.EXPERIENCE, new SkillSection(skillsExperience));
        resume.setSection(SectionType.EDUCATION, new SkillSection(skillsEducation));

        System.out.println(resume.getFullName());

        for (ContactType type: ContactType.values()) {
            System.out.println(type.getTitle() + " :" + resume.getContact(type));
        }

        for (SectionType type: SectionType.values()) {
            System.out.println(type.getTitle() + " :" + resume.getSection(type));
        }
    }
}
