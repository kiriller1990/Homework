<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Список всех польэователей</title>
    </head>
    <body>
        <h1>Список всех пользователей</h1>

        <hr>

        <p>Пользователи:</p>
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

        <c:forEach items="${userMap}" var="item" >
            ${"Логин:  "}
            ${ item.value.getLogin() }
            ${";  Пользователь -  "}
            ${ item.value.getName() }
            ${";  Дата рождения -  "}
            ${ item.value.getDate() }<p></p>
        </c:forEach>
        <input type="button" onclick="location.href='http://localhost:8080/Mk-JD2-82-21-0.0.0-SNAPSHOT/views/message.jsp';" value="Назад">

    </body>
</html>