<%--
  Created by IntelliJ IDEA.
  User: bill0
  Date: 2022-02-07
  Time: 오후 2:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="columns">
    <div class="column is-10 is-offset-1">
        <div id="meta" class="box">
            <article class="media">
                <div class="media-left">
                    <figure class="image is-64*64">
                        <img src="${review.book.thumbnail}"/>
                    </figure>
                </div>
                <div class="media-content">
                    <h2>${review.book.title}</h2>
                    <p>${review.book.authors} / ${review.book.publisher}</p>
                    <a href="${review.book.url}">도서 정보 보러가기</a>
                </div>
            </article>
        </div>
        <div class="box">
            <div class="content">
                <h1>${review.title}</h1>
                <p>written by ${reivew.author}, ${review.writtenDate}</p>
                <p>${review.content}</p>
                <h4>Rate</h4>
                <progress class="progress" value="${review.rate}" max="5"/>
            </div>
        </div>
    </div>
</div>
