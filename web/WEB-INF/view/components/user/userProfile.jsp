<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <script src="https://unpkg.com/axios/dist/axios.min.js" defer></script>
    <script src=" ${pageContext.request.contextPath}/js/profile.js" defer></script>
</head>
<div id="userName" style="display: none;">${user.name}</div>
<div class="columns">
    <div class="column is-10 is-offset-1">
        <div class="box">
            <div class="tile is-ancestor">
                <div class="tile is-parent is-4">
                    <div class="tile is-child">
                        <p class="image is-square">
                            <img src="https://bulma.io/images/placeholders/480x480.png">
                        </p>
                    </div>
                </div>
                <div class="tile is-parent is-8">
                    <div class="tile is-child">
                        <h1 class="title">${user.name} 님</h1>
                        <p>${user.registerDate} 가입</p>
                        <p>${user.profile}</p>
                        <c:if test="${isMe}">
                            <a class="button is-primary">수정하기</a>
                            <a class="button is-primary">쪽지함으로</a>
                        </c:if>
                        <c:if test="${!isMe}">
                            <div id="csrf" style="display: none;" headerName="${_csrf.headerName}" token="${_csrf.token}"></div>
                            <a id="sayHi" class="button is-primary">인사건네기</a>
                        </c:if>
                    </div>
                </div>
            </div>
            <h1 class="title">작성한 리뷰들</h1>
            <c:forEach var="review" items="${reviews}" varStatus="Status">
                <div class="card">
                    <div class="card-content">
                        <div class="media">
                            <div class="media-left">
                                <figure class="image is-2by3" style="height: 60px; width: 90px;">
                                    <img src="${review.book.thumbnail}">
                                </figure>
                            </div>
                            <div class="media-content">
                                <p class="title is-4">${review.book.title}</p>
                                <p class="subTitle is-6">${review.book.authors}</p>
                            </div>
                        </div>
                        <div class="content">
                            <p class="title is-4">${review.title}</p>
                            <p class="subTitle is-6">${review.authorName}, ${review.writtenDate}</p>
                            <progress class="progress is-success" value="${review.rate}" max="5"></progress>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
