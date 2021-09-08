<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Карточка пользователя</title>
    </head>
    <body>
        <h1>Карточка пользователя</h1>
        ${'ID пользователя: '}
        ${employee.getId()}<br>

        ${'Имя: '}
        ${employee.getName()}<br>

        ${'Зарплата: '}
        ${employee.getSalary()}<br>
        <br><br>
        <input type="button" onclick="location.href='/Mk-JD2-82-21-0.0.0-SNAPSHOT/';" value="Назад">
        <input type="button" onclick="location.href='/Mk-JD2-82-21-0.0.0-SNAPSHOT/views/card.jsp';" value="Посмотреть карточку другого сотрудника">
    </body>
</html>