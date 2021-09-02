<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>О приложении</title>
    </head>
    <body>
        <h1>Информация о приложении: </h1>

        <hr>
        ${"Время запуска приложения: "}
        ${time}<br>

        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <c:if test="${StorageType.equalsIgnoreCase('FILE')}">
            <p>Способ хранения данных: в файле</p>
        </c:if>
        <c:if test="${StorageType.equalsIgnoreCase('MEMORY')}">
                <p>Способ хранения данных: в памяти приложения</p>
        </c:if>

        <input type="button" onclick="location.href='http://localhost:8080/Mk-JD2-82-21-0.0.0-SNAPSHOT/views/message.jsp';" value="Назад">

    </body>
</html>