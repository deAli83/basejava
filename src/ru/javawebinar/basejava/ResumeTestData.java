package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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


        Period period1Job1 = new Period(
                LocalDate.of(2013,10,1),
                LocalDate.now(),
                "Автор проекта.",
                "Создание, организация и проведение Java онлайн проектов и стажировок.");

        List<Period> periodsJob1 = new ArrayList<>();
        periodsJob1.add(period1Job1);

        Experience job1 = new Experience("Java Online Projects", periodsJob1);

        Period period1Job2 = new Period(
                LocalDate.of(2014,10,1),
                LocalDate.of(2016,1,1),
                "Старший разработчик (backend)",
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, " +
                "pring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, " +
                "авторизация по OAuth1, OAuth2, JWT SSO.");

        List<Period> periodsJob2 = new ArrayList<>();
        periodsJob2.add(period1Job2);

        Experience job2 = new Experience("Wrike", periodsJob2);

        Period period1Job3 = new Period(
                LocalDate.of(2012,4,1),
                LocalDate.of(2014,10,1),
                "Java архитектор",
                "Организация процесса разработки системы ERP для разных окружений: релизная политика, " +
                "версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы " +
                "(pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных " +
                "сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html)." +
                " Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. " +
                "Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, " +
                "OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python");

        List<Period> periodsJob3 = new ArrayList<>();
        periodsJob3.add(period1Job3);

        Experience job3 = new Experience("RIT Center", periodsJob3);

        Period period1Job4 = new Period(
                LocalDate.of(2010,12,1),
                LocalDate.of(2012,4,1),
                "Ведущий программист",
                "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper," +
                " Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования," +
                " мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT," +
                " ExtGWT (GXT), Highstock, Commet, HTML5.");

        List<Period> periodsJob4 = new ArrayList<>();
        periodsJob4.add(period1Job4);

        Experience job4 = new Experience("Luxoft (Deutsche Bank)", periodsJob4);

        Period period1Job5 = new Period(
                LocalDate.of(2008,6,1),
                LocalDate.of(2010,12,1),
                "Ведущий специалист",
                "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J," +
                        " EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики" +
                        " и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)");

        List<Period> periodsJob5 = new ArrayList<>();
        periodsJob5.add(period1Job5);

        Experience job5 = new Experience("Yota", periodsJob5);

        Period period1Job6 = new Period(
                LocalDate.of(2007,3,1),
                LocalDate.of(208,6,1),
                "Разработчик ПО",
                "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей" +
                " кластерного J2EE приложения (OLAP, Data mining).");

        List<Period> periodsJob6 = new ArrayList<>();
        periodsJob6.add(period1Job6);

        Experience job6 = new Experience("Enkata", periodsJob6);

        Period period1Job7 = new Period(
                LocalDate.of(2005,1,1),
                LocalDate.of(207,2,1),
                "Разработчик ПО",
                "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной" +
                " IN платформе Siemens @vantage (Java, Unix).");

        List<Period> periodsJob7 = new ArrayList<>();
        periodsJob7.add(period1Job7);

        Experience job7 = new Experience("Siemens AG", periodsJob7);

        Period period1Job8= new Period(
                LocalDate.of(1997,9,1),
                LocalDate.of(2005,1,1),
                "Инженер по аппаратному и программному тестированию",
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).");

        List<Period> periodsJob8 = new ArrayList<>();
        periodsJob8.add(period1Job8);

        Experience job8 = new Experience("Alcatel", periodsJob8);

        Period period1Education1= new Period(
                LocalDate.of(2013,3,1),
                LocalDate.of(2013,5,1),
                "'Functional Programming Principles in Scala' by Martin Odersky");

        List<Period> periodsEducation1 = new ArrayList<>();
        periodsEducation1.add(period1Education1);

        Experience education1 = new Experience("Coursera", periodsEducation1);

        Period period1Education2= new Period(
                LocalDate.of(2011,3,1),
                LocalDate.of(2011,4,1),
                "Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'");

        List<Period> periodsEducation2 = new ArrayList<>();
        periodsEducation2.add(period1Education2);

        Experience education2 = new Experience("Luxoft", periodsEducation2);

        Period period1Education3= new Period(
                LocalDate.of(2005,1,1),
                LocalDate.of(2005,4,1),
                "3 месяца обучения мобильным IN сетям (Берлин)");

        List<Period> periodsEducation3 = new ArrayList<>();
        periodsEducation3.add(period1Education3);

        Experience education3 = new Experience("Siemens AG", periodsEducation3);

        Period period1Education4= new Period(
                LocalDate.of(1997,9,1),
                LocalDate.of(1998,3,1),
                "6 месяцев обучения цифровым телефонным сетям (Москва)");

        List<Period> periodsEducation4 = new ArrayList<>();
        periodsEducation4.add(period1Education4);

        Experience education4 = new Experience("Alcatel", periodsEducation4);

        Period period1Education5= new Period(
                LocalDate.of(1993,9,1),
                LocalDate.of(1996,7,1),
                "Аспирантура (программист С, С++)");

        Period period2Education5= new Period(
                LocalDate.of(1987,9,1),
                LocalDate.of(1993,7,1),
                "Инженер (программист Fortran, C)");

        List<Period> periodsEducation5 = new ArrayList<>();
        periodsEducation5.add(period1Education5);
        periodsEducation5.add(period2Education5);

        Experience education5 = new Experience("Санкт-Петербургский национальный исследовательский университет" +
                " информационных технологий, механики и оптики", periodsEducation5);

        Period period1Education6= new Period(
                LocalDate.of(1984,9,1),
                LocalDate.of(1987,6,1),
                "Закончил с отличием");

        List<Period> periodsEducation6 = new ArrayList<>();
        periodsEducation6.add(period1Education6);

        Experience education6 = new Experience("Заочная физико-техническая школа при МФТИ", periodsEducation6);

        List<Experience> experienceJob = new ArrayList<>();
        experienceJob.add(job1);
        experienceJob.add(job2);
        experienceJob.add(job3);
        experienceJob.add(job4);
        experienceJob.add(job5);
        experienceJob.add(job6);
        experienceJob.add(job7);
        experienceJob.add(job8);

        List<Experience> experienceEducation = new ArrayList<>();
        experienceEducation.add(education1);
        experienceEducation.add(education2);
        experienceEducation.add(education3);
        experienceEducation.add(education4);
        experienceEducation.add(education5);
        experienceEducation.add(education6);

        resume.setSection(SectionType.ACHIEVEMENT, new ListSection(Arrays.asList(achievements)));
        resume.setSection(SectionType.QUALIFICATIONS, new ListSection(Arrays.asList(qualifications)));
        resume.setSection(SectionType.EXPERIENCE, new Organization(experienceJob));
        resume.setSection(SectionType.EDUCATION, new Organization(experienceEducation));

        System.out.println(resume.getFullName());

        for (ContactType type: ContactType.values()) {
            System.out.println(type.getTitle() + ": " + resume.getContact(type));
        }

        for (SectionType type: SectionType.values()) {
            System.out.println(type.getTitle() + ": " + resume.getSection(type));
        }
    }
}
