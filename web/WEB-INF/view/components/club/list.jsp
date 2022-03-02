<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1 class="title">클럽 목록</h1>
<c:forEach var="club" items="${clubs}">
    <div class="box">
        <h1 class="title">${club.name}</h1>
        <p class="title is-2">소개</p>
        <p>${club.description}</p>
        <p class="title is-2">태그</p>
        <p>${club.tags}</p>
        <p class="title is-2">창립일</p>
        <p>${club.foundationDate}</p>
    </div>
</c:forEach>