<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages"/>
<c:import url="/WEB-INF/jsp/design/header.jspf"/>

<h1><c:out value="${person.name}"/></h1>

<ul>
  <li>Twitter: <a href="<c:url value="https://twitter.com/${person.timeline.twitter}"/>" rel="nofollow"><c:out value="@${person.timeline.twitter}"/></a></li>
  <c:if test="${not empty person.solidName}">
    <li>Konstruktive Kritik über <a href="<c:url value="http://solidfeedback.de/?an=${person.solidName}"/>">Solid Feedback</a></li>
  </c:if>
  <c:if test="${not empty person.timeline.lastFluff}">
    <li>Zuletzt geflufft am <fmt:formatDate value="${person.timeline.lastFluff}" type="both" dateStyle="full"/></li>
  </c:if>
  <c:if test="${empty person.timeline.lastFluff}">
    <li>Wurde noch gar nicht geflauscht... Sei du der Erste!</li>
  </c:if>
</ul>

<c:forEach var="f" items="${fluffs}">
  <c:set var="fluff" value="${f}" scope="request"/>
  <c:import url="/WEB-INF/jsp/fragment/fluff.jspf"/>
</c:forEach>

<c:import url="/WEB-INF/jsp/design/footer.jspf"/>