<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages"/>
<c:import url="/WEB-INF/jsp/design/header.jspf"/>

<h1>Lange nicht geflauscht...</h1>

<p>
  Die folgenden Piraten wurden lange nicht mehr geflauscht:
</p>

<ul>
  <c:forEach var="uf" items="${unfluffed}">
    <c:set var="interval" value="${timeMap[uf]}"/>
    <li>
      <a href="<c:url value="/fluffy/for/${uf.nick}.html"/>"><c:out value="${uf.name}"/></a><c:out value=": "/>
      <c:if test="${not empty interval}">
        <fmt:message key="unfluffed.interval">
          <fmt:param value="${interval.days}"/>
          <fmt:param value="${interval.hours}"/>
          <fmt:param value="${interval.minutes}"/>
        </fmt:message>
      </c:if>
      <c:if test="${empty interval}">
        <fmt:message key="unfluffed.never"/>
      </c:if>
    </li>
  </c:forEach>
</ul>

<c:import url="/WEB-INF/jsp/design/footer.jspf"/>