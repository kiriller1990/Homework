<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Карточка пользователя</title>
    </head>
    <body>
        <h1>Карточка пользователя</h1>
        <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

        <table border="1">
                <tr>
                <th>${'ID: '}</th>
                <th>${' Имя '}</th>
                <th>${'Зарплата:'}</th>
                <th>${'Отдел'}</th>
                <th>${'Должность'}</th>
                </tr>
                <tr>
                <td>${employee.getId()}</td>
                <td>${employee.getName()}</td>
                <td><fmt:formatNumber type = "number" value ="${employee.getSalary()}"/></td>
                <td>${employee.getTitle()}</td>
                <td>${employee.getPosition()}</td>
                </tr>
                </table>
        <input type="button" onclick="location.href='/Mk-JD2-82-21-0.0.0-SNAPSHOT/';" value="Назад">
        <input type="button" onclick="location.href='/Mk-JD2-82-21-0.0.0-SNAPSHOT/views/card.jsp';" value="Посмотреть карточку другого сотрудника">
    </body>
</html>