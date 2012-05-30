<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages"/>
<c:import url="/WEB-INF/jsp/design/header.jspf"/>

<h1>Zur Flauschwahl stehen...</h1>

<p>
  Diese Piraten sind in <em>fluffyfeedback</em> eingetragen:
</p>

<ul>
  <c:forEach var="p" items="${persons}">
    <li>
      <a href="<c:url value="/fluffy/for/${p.nick}.html"/>"><c:out value="${p.name}"/></a>
    </li>
  </c:forEach>
</ul>

<c:import url="/WEB-INF/jsp/design/footer.jspf"/>