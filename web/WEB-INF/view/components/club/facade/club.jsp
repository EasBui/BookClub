<%--
  Created by IntelliJ IDEA.
  User: bill0
  Date: 2021-12-27
  Time: 오전 10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="columns">
    <div class="column is-10 is-offset-1">
        <div class="box">
            <h1 class="title">소개</h1>
        </div>
    </div>
</div>
<div class="columns">
    <div class="column is-10 is-offset-1">
        <div class="box">
            <h1 class="title">회원</h1>
            <div class="tile is-ancestor">
                <div class="tile is-parent is-3">
                    <div class="tile is-child box">
                        <p class="image is-64x64">
                            <img src="https://bulma.io/images/placeholders/128x128.png">
                        </p>
                        <div class="content">
                            <p>
                                이름과 소개
                            </p>
                        </div>
                        <span class="tag">관심 분야1</span>
                        <span class="tag">관심 분야2</span>
                    </div>
                </div>
                <div class="tile is-parent is-3">
                    <div class="tile is-child box">
                        <p class="image is-64x64">
                            <img src="https://bulma.io/images/placeholders/128x128.png">
                        </p>
                        <div class="content">
                            <p>
                                이름과 소개
                            </p>
                        </div>
                        <span class="tag">관심 분야1</span>
                        <span class="tag">관심 분야2</span>
                    </div>
                </div>
                <div class="tile is-parent is-3">
                    <div class="tile is-child box">
                        <h1>가입 하기</h1>
                    </div>
                </div>
            </div>
            </div>
        </div>
    </div>
</div>
<div class="columns">
    <div class="column is-10 is-offset-1">
        <div class="box">
            <h1 class="title">최근에 읽은 책</h1>
            <div class="tile is-ancestor">
                <c:forEach var="book" items="${books}" varStatus="status">
                    <div class="tile is-parent is-3">
                        <div class="tile is-child box">
                            <p class="image is-2by3">
                                <img src="${book.thumbnail}" width="120px" height="180px"/>
                            </p>
                            <h1>${book.title}</h1>
                            <p>${book.authors}</p>
                            <p>${book.publisher}</p>
                            <c:if test="${book.rate == -1}">
                                <p>이 책의 리뷰가 아직 작성되지 않았습니다</p>
                            </c:if>
                            <c:if test="${book.rate != -1}">
                                <progress class="progress is-small" value="20" max="100">20%</progress>
                            </c:if>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<div class="columns">
    <div class="column is-10 is-offset-1">
        <div class="box">
            <h1 class="title">게시판</h1>
            <div class="columns">
                <div class="column is-3">
                    <span class="tag is-info is-large">공지사항</span>
                </div>
                <div class="column is-3">
                    <span class="tag is-info is-large">잡담방</span>
                </div>
                <div class="column is-3">
                    <span class="tag is-info is-large">사진첩</span>
                </div>
            </div>
        </div>
    </div>
</div>