<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul class="nav--actions">
    <li class="logged-user">
        Witaj ${user}
        <ul class="dropdown">
            <li><a href="#">Profil</a></li>
            <li><a href="#">Moje zbiórki</a></li>
            <li><a>
                <form action="<c:url value="/logout"/>" method="post">
                    <input type="submit" value="Wyloguj">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </a></li>
        </ul>
    </li>
</ul>

<%@include file="featureList.jsp" %>

<div class="slogan container container--90">
    <h2>
        Dziękujemy za przesłanie formularza Na maila prześlemy wszelkie
        informacje o odbiorze.
    </h2>
</div>
</header>

<%@include file="footer.jsp" %>