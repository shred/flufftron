<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages"/>
<c:import url="/WEB-INF/jsp/design/header.jspf"/>

<h1>Frisch geflauscht...</h1>

<p>
  Mitmachen? Sende einfach einen Tweet mit dem Hashtag <code>#flausch</code> an einen
  <a href="<c:url value="/fluffy/for/index.html"/>">flauschbaren Piraten</a>!
</p>

<c:forEach var="f" items="${fluffs}">
  <c:set var="fluff" value="${f}" scope="request"/>
  <c:import url="/WEB-INF/jsp/fragment/fluff.jspf"/>
</c:forEach>

<p>
  Es kann bis zu eine Stunde dauern, bis Flauschtweets hier erscheinen.
</p>

<c:import url="/WEB-INF/jsp/design/footer.jspf"/>