<%--
  Created by IntelliJ IDEA.
  User: bill0
  Date: 2022-03-02
  Time: 오후 9:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="box">
  <form
          style="margin: 0; padding: 0"
          name="register_form"
          action="/register"
          method="post"
          accept-charset="utf-8">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <div class="field">
      <label class="label">클럽 이름</label>
      <div class="control">
        <input name="clubName" type="text">
      </div>
    </div>

    <div class="field">
      <label class="label">클럽 소개</label>
      <div class="control">
        <textarea name="description" type="text"></textarea>
      </div>
    </div>

    <div class="field">
      <label class="label">태그 작성</label>
      <div class="control">
        <textarea name="tags" type="text"></textarea>
      </div>
      <p class="help">해쉬태그 형태로 입력해주세요! 띄어쓰기는 무시됩니다.</p>
    </div>

    <div class="field">
      <div class="control">
        <label class="checkbox">
          <input name="isOpened" type="checkbox">
          클럽을 공개합니다.
        </label>
      </div>
    </div>

    <div class="field">
      <div class="control">
        <button class="button is-primary" type="submit">생성하기</button>
      </div>
    </div>
  </form>
</div>