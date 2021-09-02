<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Form</title>
</head>
<body>
    <form action="http://localhost:8080/Mk-JD2-82-21-0.0.0-SNAPSHOT/" method="POST">
    <h1> Приветству тебя пользователь, войди или зарегестрируйся <h1>
    <h2>  ${name} <h2>
    <input type="text" name="login" placeholder = "Логин" size=50>
    <p></p>
    <input type="password" name="password" placeholder= "Пароль" size=50>
    <p></p>
    <input type="text" name="name" placeholder = "ФИО" size=50>
    <p></p>
    <h4> Дата рождения: <h4>
    <input type="date" name="date">
    <p></p>
    <input type="submit" value="Зарегистрироваться">
    <input type="button" onclick="location.href='http://localhost:8080/Mk-JD2-82-21-0.0.0-SNAPSHOT/views/SignIn.jsp';" value="Вход">
    </form>
    </body>
    </html>