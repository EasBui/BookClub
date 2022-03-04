<%--
  Created by IntelliJ IDEA.
  User: bill0
  Date: 2021-12-27
  Time: 오전 10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <script src="https://unpkg.com/axios/dist/axios.min.js" defer></script>
    <script src=" ${pageContext.request.contextPath}/js/clubFacade.js" defer></script>
</head>
<div class="box">
    <div class="content">
        <h1 id="clubName">${info.name}</h1>
        <p>${info.foundationDate}에 설립.</p>
        <h2>소개</h2>
        <p>${info.description}</p>
        <h2>태그</h2>
        <p>${info.tags}</p>
    </div>
</div>

<div class="box">
    <h1 class="title">회원</h1>
    <c:forEach var="member" items="#{members}">
        <div class="box">
            <p class="title">${member.name}</p>
            <p class="subtitle">${member.profile}</p>
            <a class="button is-primary" href="/users/${member.name}">프로필 보러가기</a>
        </div>
    </c:forEach>
</div>

<div class="box">
    <h1 class="title">최근 ${info.name}에서 읽은 책</h1>
    <c:forEach var="book" items="${books}" varStatus="status">
        <div class="box">
            <p class="image is-2by3">
                <img src="${book.bookInfo.thumbnail}" width="120px" height="180px"/>
            </p>
            <h1>${book.bookInfo.title}</h1>
            <p>${book.bookInfo.authors}</p>
            <p>${book.bookInfo.publisher}</p>
            <c:if test="${book.clubAvgRate == -1}">
                <p>이 책의 리뷰가 아직 작성되지 않았습니다</p>
            </c:if>
            <c:if test="${book.clubAvgRate != -1}">
                <progress class="progress is-small" value="${book.clubAvgRate}" max="5"></progress>
                <c:forEach var="review" items="${reviewsClassifiedByISBN[book.bookInfo.ISBN]}">
                    <div class="box">
                        <h1 class="title">${review.title}</h1>
                        <p class="subtitle">by ${review.author}</p>
                        <a class="button is-primary" href="/reviews/${review.ID}">보러가기</a>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </c:forEach>
</div>
<c:if test="${!isMember}">
    <button class="signUp button is-primary">가입하기</button>
</c:if>

<c:if test="${isHost}">
<div class="box">
    <h1 class="title">가입 희망자</h1>
    <c:forEach var="signUp" items="${signUps}">
        <div class="box">
            <p class="title">${signUp.name}</p>
            <p class="subtitle">${signUp.profile}</p>
            <a class="button is-primary" href="/users/${signUp.name}">프로필 보러가기</a>
            <button target="${signUp.name}" class="approve button is-primary">가입 승인</button>
            <button target="${signUp.name}" class="reject button is-warning">가입 거절</button>
        </div>
    </c:forEach>
</div>
</c:if>


