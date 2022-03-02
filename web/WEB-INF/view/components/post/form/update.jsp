<%--
  Created by IntelliJ IDEA.
  User: bill0
  Date: 2021-12-19
  Time: 오후 9:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="box">
    <form
            style="margin: 0; padding: 0"
            name="new_post_form"
            action="/posts/${id}"
            accept-charset="utf-8"
            method="post"
    ><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <input type="hidden" name="_method" value="PUT"/>
        <div class="field">
            <label class="label">제목</label>
            <div class="control">
                <input class="input" id="title" name="title" type="text" value="${title}">
            </div>
        </div>
        <div class="field">
            <label class="label">본문</label>
            <div class="control">
                <textarea class="textarea" id="content" name="content" rows="20" cols="100">${content}</textarea>
            </div>
        </div>
        <div class="field">
            <div class="control">
                <input class="button is-primary" type="submit" value="작성완료"/>
            </div>
        </div>
    </form>
</div>
