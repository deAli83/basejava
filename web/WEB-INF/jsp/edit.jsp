<%--
  Created by IntelliJ IDEA.
  User: ALX
  Date: 21.08.2022
  Time: 23:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="ru.javawebinar.basejava.util.Web" %>
<%@ page import="ru.javawebinar.basejava.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="css/style.css">
  <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
  <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
  <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
    <input type="hidden" name="uuid" value="${resume.uuid}">
    <dl>
      <dt>Имя:</dt>
      <dd><input type="text" name="fullName" pattern="^[A-Za-zА-Яа-яЁё]{1,30}\s[A-Za-zА-Яа-яЁё]{1,30}\s[A-Za-zА-Яа-яЁё]{1,30}" required size=50 value="${resume.fullName}"></dd>
    </dl>
    <h1>Контакты:</h1>
    <c:forEach var="type" items="<%=ContactType.values()%>">
      <dl>
        <dt>${type.title}</dt>
        <dd><input type="text" name="${type.name()}" size=30 value="${resume.getContact(type)}"></dd>
      </dl>
    </c:forEach>
    <c:forEach var="type" items="<%=SectionType.values()%>">
      <jsp:useBean id="type" type="ru.javawebinar.basejava.model.SectionType"/>
      <dl>
          ${type.title}
        <c:choose>
          <c:when test="${type==SectionType.PERSONAL || type==SectionType.OBJECTIVE}">
            <dd><input type="text" name="${type.name()}" size=108
                       value="<%=Web.getText(type, resume)%>">
            </dd>
          </c:when>
          <c:when test="${type==SectionType.ACHIEVEMENT || type==SectionType.QUALIFICATIONS}">
            <dd><textarea name="${type.name()}" cols=100 rows=5><%=String.join("\n", Web.getList(type, resume))%></textarea>
            </dd>
          </c:when>
          <c:when test="${type==SectionType.EXPERIENCE || type==SectionType.EDUCATION}">
            <c:forEach var="org" items="<%=Web.getOrganizations(type, resume)%>"
                       varStatus="counter">
              <dl>
                <dt>Название:</dt>
                <dd><input type="text" name='${type}' size=100 value="${org.name}"></dd>
              </dl>
              <dl>
                <dt>Сайт:</dt>
                <dd><input type="text" name='${type}url' size=100 value="${org.link}"></dd>
                </dd>
              </dl>
              <br>
              <div style="margin-left: 30px">
                <c:forEach var="pos" items="${org.periods}">
                  <jsp:useBean id="pos" type="ru.javawebinar.basejava.model.Period"/>
                  <dl>
                    <dt>Начальная дата:</dt>
                    <dd>
                      <input type="text" name="${type}${counter.index}startDate" size=10
                             value="<%=pos.getStartDate()%>" placeholder="dd/mm/yyyy">
                    </dd>
                  </dl>
                  <dl>
                    <dt>Конечная дата:</dt>
                    <dd>
                      <input type="text" name="${type}${counter.index}finishDate" size=10
                             value="<%=pos.getFinishDate()%>" placeholder="dd/mm/yyyy">
                  </dl>
                  <dl>
                    <dt>Должность:</dt>
                    <dd><input type="text" name='${type}${counter.index}position' size=75
                               value="${pos.position}">
                  </dl>
                  <dl>
                    <dt>Описание:</dt>
                    <dd><textarea name="${type}${counter.index}description" rows=5
                                  cols=75>${pos.description}</textarea></dd>
                  </dl>
                </c:forEach>
              </div>
            </c:forEach>
          </c:when>
        </c:choose>
      </dl>
    </c:forEach>
    <hr>
    <button type="submit">Сохранить</button>
    <button type="reset" onclick="window.history.back()">Отменить</button>
  </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>

