<%--
  Created by IntelliJ IDEA.
  User: bill0
  Date: 2022-02-07
  Time: 오후 3:04
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
                        <img src="${book.thumbnail}"/>
                    </figure>
                </div>
                <div class="media-content">
                    <h2>${book.title}</h2>
                    <p>${book.authors} / ${book.publisher}</p>
                    <a href="${book.url}">도서 정보 보러가기</a>
                </div>
            </article>
        </div>
        <div class="box">
            <form>
                <div class="field">
                    <label class="label">제목</label>
                    <div class="control">
                        <input class="input" type="text" placeholder="리뷰의 제목을 입력해주세요!">
                    </div>
                    <p class="help">최대 50자 이내로 입력해 주세요.</p>
                </div>
                <div class="field">
                    <label class="label">본문</label>
                    <div class="control">
                        <textarea class="textarea" placeholder="리뷰의 본문을 입력해주세요!"></textarea>
                    </div>
                </div>
                <div class="field">
                    <label class="label">별점</label>
                    <div class="control">
                        <div class="select">
                            <select>
                                <option>5</option>
                                <option>4</option>
                                <option>3</option>
                                <option>2</option>
                                <option>1</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="field">
                    <div class="control">
                        <input type="submit" class="button is-primary" value="작성완료">
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>