<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>ID нового сотрудника</title>
    </head>
    <body>
        <h1>Новый сотрудник  добавлен в базу данных</h1>
        ${'ID нового сотрудника - '}
        ${id}

        <br><br>

        <input type="button" onclick="location.href='/Mk-JD2-82-21-0.0.0-SNAPSHOT/';" value="Назад">
        <input type="button" onclick="location.href='/Mk-JD2-82-21-0.0.0-SNAPSHOT/views/card.jsp';" value="Карточка сотрудника">
    </body>
</html>