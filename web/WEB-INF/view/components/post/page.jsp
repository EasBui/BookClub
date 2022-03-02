<%--
  Created by IntelliJ IDEA.
  User: bill0
  Date: 2021-12-09
  Time: 오전 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="box">
    <table class="table">
        <thead>
            <tr>
                <th> ID </th>
                <th> 제목 </th>
                <th> 작성자 </th>
                <th> 작성일시 </th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="post" items="${page.postList}">
                <tr>
                    <th>${post.id}</th>
                    <td><a href="/posts/${post.id}?page=${page.currentPage}">${post.title}</a></td>
                    <td>${post.author}</td>
                    <td>${post.writtenDate}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <nav class="pagination" role="navigation">
        <ul class="pagination-list">
            <li>
                <c:forEach var="idx" items="${page.pageIndex}">
                    <c:choose>
                        <c:when test="${idx == page.currentPage}"><a class="pagination-link is-current" href="/posts/${post.id}?page=${idx}">${idx}</a></c:when>
                        <c:otherwise><a class="pagination-link" href="/posts/${post.id}?page=${idx}">${idx}</a></c:otherwise>
                    </c:choose>
                </c:forEach>
            </li>
        </ul>
    </nav>
</div>
