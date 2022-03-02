<%--
  Created by IntelliJ IDEA.
  User: bill0
  Date: 2022-01-14
  Time: 오후 5:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                        <input class="input" type="text" value="${user.name}">
                        <p>${user.registerDate} 가입</p>
                        <textarea class="textarea" rows="5">${user.profile}</textarea>
                        <a class="button is-primary">수정완료</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
