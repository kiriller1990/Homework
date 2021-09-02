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
    <form action="http://localhost:8080/Mk-JD2-82-21-0.0.0-SNAPSHOT/login" method="POST">
    <h1> Введите свой логин и пароль : <h1>
    <h2>  ${name} <h2>
    <input type="text" name="login" placeholder = "Введите логин" size=50>
    <p></p>
    <input type="password" name="password" placeholder= "Введите пароль" size=50>
    <input type="submit" value="Submit">
    </form>
    </body>
    </html>