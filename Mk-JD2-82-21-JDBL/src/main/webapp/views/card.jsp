<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Карточка сотрудника</title>
    </head>
    <body>
    <form action="http://localhost:8080/Mk-JD2-82-21-0.0.0-SNAPSHOT/cardEmployee" method="GET">

        <h1>Введите ID сотрудника для простмотра его карточки</h1>

        <p style = "color:Red"> ${name} </p>
            <input name="id_card" type="text" placeholder="Введите id сотрудника" size="50">
        <br><br><br><br>

        <input type="submit" value="посмотреть карточку сотрудника"/>
        <input type="button" onclick="location.href='/Mk-JD2-82-21-0.0.0-SNAPSHOT/';" value="Назад">
    </form>
    </body>
</html>