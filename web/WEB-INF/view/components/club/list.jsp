<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1 class="title">클럽 검색</h1>
<form action="/clubs/list" style="margin: 0; padding: 0" method="get" accept-charset="utf-8">
    <input class="input" type="text" name="query" placeholder="띄어쓰기가 반영되고, 띄어쓰기로 태그가 구분됩니다.">
    <label class="radio"><input type="radio" name="isTags" value="false" checked>제목 검색</label>
    <label class="radio"><input type="radio" name="isTags" value="true">태그 검색</label>
    <button type="submit" class="button is-primary">검색</button>
</form>

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