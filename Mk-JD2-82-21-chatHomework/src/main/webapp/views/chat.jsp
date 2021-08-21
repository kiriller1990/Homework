<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Мессенджер: Мои сообщения</title>
    </head>
    <body>
        <h1>Мои сообщения</h1>

        <p>Сообщения:</p>
        <br/>

        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

        <c:forEach items="${messages}" var="item" >
            ${item.getSender().getLogin()}
            ${" - "}
            ${item.getMessageSendingTime().toString()}<br>
            ${item.getMessageText()}<br><br>
        </c:forEach>

    </body>
</html>