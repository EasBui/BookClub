<%--
  Created by IntelliJ IDEA.
  User: bill0
  Date: 2021-12-09
  Time: 오후 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="navbar is-light" role="navigation" aria-label="main navigation">
    <div class="navbar-brand">
        <div class="navbar-item">
            <h3>Simple CRUD</h3>
        </div>

        <a role="button" class="navbar-burger" aria-label="menu" aria-expanded="false" data-target="navMenu">
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
        </a>
    </div>
    <div id="navMenu" class="navbar-menu">
        <div class="navbar-start">
            <div class="navbar-item has-dropdown is-hoverable">
                <a class="navbar-link">게시글</a>
                <div class="navbar-dropdown">
                    <a class="navbar-item" href="${pageContext.request.contextPath}/posts">목록</a>
                    <a class="navbar-item" href="${pageContext.request.contextPath}/posts/form/new">글 쓰기</a>
                    <hr class="navbar-divider">
                    <a class="navbar-item has-text-grey-lighter" href="#">검색</a>
                </div>
            </div>
            <sec:authorize access="isAuthenticated()">
            <div class="navbar-item has-dropdown is-hoverable">
                <a class="navbar-link">나의 메뉴</a>
                <div class="navbar-dropdown">
                    <hr class="navbar-divider">
                    <a class="navbar-item has-text-grey-lighter" href="#">정보 수정</a>
                    <a class="navbar-item has-text-grey-lighter" href="#">쪽지함</a>
                    <a class="navbar-item has-text-grey-lighter" href="/users/">나의 프로필</a>
                </div>
            </div>
            </sec:authorize>
        </div>
        <div class="navbar-end">
            <div class="navbar-item">
                <div class="buttons">
                    <sec:authorize access="isAnonymous()">
                        <a class="button is-white" href="${pageContext.request.contextPath}/form/login">Log In</a>
                        <a class="button is-primary" href="${pageContext.request.contextPath}/form/register">Register</a>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <form style="margin: 0; padding: 0"
                                action="${pageContext.request.contextPath}/logout"
                                method="POST"
                                id="logout_form"
                                name="logout_form">
                            <input class="button is-light" id="logout_btn" type="submit" value="LogOut" />
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        </form>
                    </sec:authorize>
                </div>
            </div>
        </div>
    </div>
</div>