<%--
  Created by IntelliJ IDEA.
  User: ALX
  Date: 21.08.2022
  Time: 23:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="ru.javawebinar.basejava.model.ContactType" %>
<%@ page import="ru.javawebinar.basejava.model.SectionType" %>
<%@ page import="ru.javawebinar.basejava.model.ListSection" %>
<%@ page import="ru.javawebinar.basejava.model.TextSection" %>
<%@ page import="ru.javawebinar.basejava.util.Web" %>
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
      <dd><input type="text" name="fullName" size=50 value="${resume.fullName}"></dd>
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

