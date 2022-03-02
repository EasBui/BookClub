<%--
  Created by IntelliJ IDEA.
  User: bill0
  Date: 2021-12-21
  Time: 오후 2:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section class="section is-large has-background-warning">
    <div class="columns is-vcentered">
        <div class="column is-4 is-offset-4">
            <div class="box">
                <form
                        style="margin: 0; padding: 0"
                        name="register_form"
                        action="/register"
                        method="post"
                        accept-charset="utf-8"
                ><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <br>
                    <h1 class="title">Registration</h1>
                    <br>
                    <div class="field">
                        <label class="label">계정</label>
                        <div class="control">
                            <input class="input" id="account" type="text" name="account" placeholder="계정을 입력해주세요"/>
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">패스워드</label>
                        <div class="control">
                            <input class="input" id="password" type="text" name="password" placeholder="패스워드를 입력해주세요"/>
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">이름</label>
                        <div class="control">
                            <input class="input" id="name" type="text" name="name" placeholder="이름을 입력해주세요"/>
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">소개</label>
                        <div class="control">
                            <textarea class="textarea" id="profile" name="profile" placeholder="짧은 소개글을 입력해주세요"></textarea>
                        </div>
                    </div>
                    <br>
                    <div class="field">
                        <div class="control">
                            <button class="button is-primary" type="submit">가입하기</button>
                        </div>
                    </div>
                    <br>
                </form>
            </div>
        </div>
    </div>
</section>


