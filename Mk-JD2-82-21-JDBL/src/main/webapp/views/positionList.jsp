<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Список должностей</title>
    </head>
    <body>
        <h1>Список должностей</h1>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <table border="1">
                <tr>
                <th>${'ID: '}</th>
                <th>${' Должность: '}</th>
                </tr>
                <c:forEach items="${position}" var="item" >
                <tr>
                <td>${item.getId()}</td>
                <td><a href="/Mk-JD2-82-21-0.0.0-SNAPSHOT/position?id=${item.getId()}">${item.getPosition()}</a></td>
                </tr>
                </c:forEach>
                </table>

        <br>
        <input type="button" onclick="location.href='/Mk-JD2-82-21-0.0.0-SNAPSHOT/';" value="Назад">
        </body>
</html>