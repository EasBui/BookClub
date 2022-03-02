<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<head>
    <script src="https://unpkg.com/axios/dist/axios.min.js" defer></script>
    <script src=" ${pageContext.request.contextPath}/js/message.js" defer></script>
</head>
<div id="userName" style="display: none;"><sec:authentication property="principal.name"/></div>
<div class="columns">
    <div class="column is-10 is-offset-1">
        <div class="box">
            <h1 class="title">메세지 함</h1>
        </div>
    </div>
</div>
<div class="columns">
    <div class="column is-6">
    <c:forEach var="conversation" items="${conversations}">
        <div cpname="${conversation.counterpartName}" class="box cp_box">
            <h1 class="title" style="display: inline" >${conversation.counterpartName}</h1>
            <button id="unsubscribeBtn" class="delete" ></button>
        </div>
    </c:forEach>
    </div>
    <div class="column is-6">
    <c:forEach var="conversation" items="${conversations}">
        <div id="con_${conversation.counterpartName}" cpname="${conversation.counterpartName}"class="box" style="display: none;">
        <c:forEach var="message" items="${conversation.messages}">
            <div class="columns">
            <c:if test="${message.senderName == conversation.counterpartName}">
                <div class="column is-8">
                    <article class="message is-dark">
                        <div class="message-header">
                            <p>${conversation.counterpartName} 님이 보냄 ... ${message.time}</p>
                        </div>
                        <div class="message-body">
                            ${message.content}
                        </div>
                    </article>
                </div>
            </c:if>
            <c:if test="${message.receiverName == conversation.counterpartName}">
                <div class="column is-8 is-offset-4">
                    <article class="message is-warning">
                        <div class="message-header">
                            <p>${conversation.counterpartName} 님에게 보냄... ${message.time}</p>
                        </div>
                        <div class="message-body">
                                ${message.content}
                        </div>
                    </article>
                </div>
            </c:if>
            </div>
        </c:forEach>
            <textarea id="message_textarea" class="textarea" placeholder="메세지를 입력하세요!" rows="5"></textarea>
            <button id="message_send_btn" class="button is-primary">보내기</button>
        </div>
    </c:forEach>
    </div>
</div>
