<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Список должностей</title>
    </head>
    <body>
        <h1>Список должностей</h1>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

        <table border="1">
                <tr>
                <th>${'ID: '}</th>
                <th>${'Имя'}</th>
                <th>${'Зарплата'}</th>
                </tr>
                <c:forEach items="${employees}" var="item" >
                <tr>
                <td>${item.getId()}</td>
                <td><a href="/Mk-JD2-82-21-0.0.0-SNAPSHOT/cardEmployee?id_card=${item.getId()}">${item.getName()}</a></td>
                <td><fmt:formatNumber type = "number" value = "${item.getSalary()}" /></td>
                </tr>
                </c:forEach>
                </table>

        <br>
        <input type="button" onclick="location.href='/Mk-JD2-82-21-0.0.0-SNAPSHOT/';" value="Назад">
        </body>
</html>