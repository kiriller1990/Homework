<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Добавление сотрудников</title>
    </head>
    <body>
    <form action="http://localhost:8080/Mk-JD2-82-21-0.0.0-SNAPSHOT/" method="POST">

        <h1>Добавление сотрудника в базу данных</h1>
        <p>Для добавления сотрудника необходимо указать имя и зарплату</p>
        <p style = "color:Red"> ${name} </p>
            <input name="name" type="text" placeholder="Введите имя сотрудника" size="50"><p></p>
            <input name="salary" type="text" placeholder="Укажите зарплату сотрудника" size="50">

        <br><br><br><br>

        <input type="submit" value="Добавить"/>
        <input type="button" onclick="location.href='/Mk-JD2-82-21-0.0.0-SNAPSHOT/views/card.jsp';" value="Карточка сотрудника">
    </form>
    </body>
</html>