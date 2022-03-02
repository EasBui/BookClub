<%--
  Created by IntelliJ IDEA.
  User: bill0
  Date: 2021-12-09
  Time: 오후 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <script src="https://unpkg.com/axios/dist/axios.min.js" defer></script>
    <script src=" ${pageContext.request.contextPath}/js/post.js" defer></script>
</head>
<div class="box">
    <h1 class="title">${title}</h1>
    <p class="subtitle">${author}, ${dateTime}</p>
    <div class="content">
        <p>${content}</p>
    </div>
    <a class="button is-danger" id="DeleteButton" targetPostId="${id}">삭제</a>
    <a class="button is-light" id="UpdateButton" targetPostId="${id}">수정</a>
</div>

