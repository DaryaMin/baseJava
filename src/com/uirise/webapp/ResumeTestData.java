package com.uirise.webapp;

import com.uirise.webapp.model.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static com.uirise.webapp.model.ContactType.*;
import static com.uirise.webapp.model.SectionType.*;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume resume = createTestResume("UUID1", "Ivanov");
        System.out.println(resume.getContact(PHONE));
        System.out.println(resume.getSection(PERSONAL));
        System.out.println(resume.getSection(OBJECTIVE));
        System.out.println(resume.getSection(ACHIEVEMENT));
        System.out.println(resume.getSection(QUALIFICATIONS));
        System.out.println(resume.getSection(EXPERIENCE));
        System.out.println(resume.getSection(EDUCATION));
        System.out.println();
        System.out.println(resume);
    }

    public static Resume createTestResume(String uuid, String fullName){
        Resume resume = new Resume(uuid, fullName);

        resume.setContact(PHONE, "89521233");
        resume.setSection(OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        resume.setSection(PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));

        List<String> listAchievement = new ArrayList<>();
        listAchievement.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        listAchievement.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        listAchievement.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        listAchievement.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        listAchievement.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        listAchievement.add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        resume.setSection(ACHIEVEMENT, new ListSection(listAchievement));

        List<String> listQualification = new ArrayList<>();
        listQualification.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        listQualification.add( "Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        listQualification.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");

        resume.setSection(QUALIFICATIONS, new ListSection(listQualification));

        List<Organization> experience = new ArrayList<>();
        List<Organization.Position> javaPositionDetailsList = new ArrayList<>();
        javaPositionDetailsList.add(new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "Автор проекта", "Создание, организация и проведение Java онлайн проектов и стажировок."));
        experience.add(new Organization(new Link("Java Online Projects", "http://java.ru"), javaPositionDetailsList));

        List<Organization.Position> wrikePositionDetailsList = new ArrayList<>();
        wrikePositionDetailsList.add(new Organization.Position(2003, Month.MARCH, 2005, Month.JANUARY, "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."));
        experience.add(new Organization(new Link("Wrike", "http://wrike.ru"), wrikePositionDetailsList));

        resume.setSection(EXPERIENCE, new OrganizationSection(experience));

        List<Organization> education = new ArrayList<>();
        List<Organization.Position> courseraPositionDetailsList = new ArrayList<>();
        courseraPositionDetailsList.add(new Organization.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "","Functional Programming Principles in Scala by Martin Odersky"));
        education.add(new Organization(new Link("Course", null), courseraPositionDetailsList));

        List<Organization.Position> luxsoftPositionDetailsList = new ArrayList<>();
        luxsoftPositionDetailsList.add(new Organization.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.", null));
        education.add(new Organization(new Link("Luxoft", "http://luxsoft.com"), luxsoftPositionDetailsList));

        resume.setSection(EDUCATION, new OrganizationSection(education));

        return resume;
        }

}
