<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages"/>
<c:set var="pageName" value="faq" scope="request"/>
<c:import url="/WEB-INF/jsp/design/header.jspf"/>

<h1>Häufig gestellte Fragen...</h1>

<dl>

<dt>Wie flausche ich?</dt>
<dd>
  Auf <em>fluffyfeedback</em> geflauscht werden können alle
  <a href="<c:url value="/fluffy/for/index.html"/>">registrierten Personen</a>. Sende
  einfach einen Tweet an den Empfänger deines Flausches (oder erwähne ihn in deinem Tweet),
  und verwende dabei das Hashtag <code>#Flausch</code>.
</dd>

<dt>Erscheint auch ein Flausch, den ich per DM verschicke?</dt>
<dd>
  Nein, <em>fluffyfeedback</em> sieht nur öffentliche Tweets.
</dd>

<dt>Mein Flausch erscheint nicht auf <em>fluffyfeedback</em>!</dt>
<dd>
  Es kann bis zu einer Stunde dauern, bis dein Flausch bei <em>fluffyfeedback</em>
  erscheint. Denke daran, dass nur die
  <a href="<c:url value="/fluffy/for/index.html"/>">registrierten Personen</a> hier
  geflauscht werden können.
</dd>

<dt>Ich möchte gerne loben, habe aber keinen Twitter-Account!</dt>
<dd>
  Momentan funktioniert <em>fluffyfeedback</em> nur über Twitter. In Zukunft wird es aber
  auch eine Möglichkeit geben, direkt über diese Website ein Lob zu verschicken.
</dd>

<dt>Ich möchte auch gerne auf <em>fluffyfeedback</em> erscheinen.</dt>
<dd>
  Um die Liste der Personen kompakt zu halten, werden nur Mitglieder der Piratenpartei
  Deutschland, die in der Öffentlichkeit stehen, aufgenommen. Bitte dazu einen
  <a href="<c:url value="/fluffy/for/index.html"/>">bereits registrierten Piraten</a>,
  mit dem du bekannt bist, <a href="https://twitter.com/#!/shred_">mir</a> einen Tweet
  mit dem Aufnahmewunsch zu senden. Bitte erlaube mir ein paar Tage für die Freischaltung.
  Ich behalte mir vor, die Aufnahme ohne Nennung von Gründen zu verweigern.
</dd>

<dt>Wie entstand die Idee zu <em>fluffyfeedback</em>?</dt>
<dd>
  <em>fluffyfeedback</em> ist das Gegenstück zu <a href="http://www.solidfeedback.de">solidfeedback.de</a>.
  <em>solidfeedback</em> bietet die Möglichkeit, konstruktive Kritik an einen Piraten
  zu senden. Ich meine, Lob ist genauso wichtig. Johannes Ponader
  <a href="https://twitter.com/JohannesPonader/status/204513697299828736" rel="nofollow">schlug dafür den Namen und die Domain vor</a>.
  Ein paar Tage später war die erste Version von <em>fluffyfeedback</em> online.
</dd>

<dt>Unter welcher Lizenz steht diese Site?</dt>
<dd>
  Der Quelltext für diese Site steht <a href="https://github.com/shred/flufftron">auf Github</a>
  unter der <a href="http://www.gnu.org/licenses/">GNU Affero General Public License</a>
  zur Verfügung.
</dd>

</dl>

<c:import url="/WEB-INF/jsp/design/footer.jspf"/>