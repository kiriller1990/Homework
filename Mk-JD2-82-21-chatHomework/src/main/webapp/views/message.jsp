<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Мессенджер: Отправка сообщений</title>
    </head>
    <body>
        <form action="http://localhost:8080/Mk-JD2-82-21-0.0.0-SNAPSHOT/message" method="POST">
            <h1>Отправка сообщений</h1>
            <p>Укажите логин получателя сообщения</p>
            <p>Получатель:</p><input name="messageRecipient" type="text" placeholder="Получатель" size="50">
            <br><br>

            <p>Напишите сообщение:</p>
             <p><textarea rows="10" cols="45" name="text" placeholder="Введите сообщение"></textarea></p>

            <input type="submit" value="Отправить сообщение" /><br><br>
            <input type="button" onclick="location.href='http://localhost:8080/Mk-JD2-82-21-0.0.0-SNAPSHOT/views/chat.jsp';" value="Мои сообщения">
            <input type="button" onclick="location.href='http://localhost:8080/Mk-JD2-82-21-0.0.0-SNAPSHOT/views/SignUp.jsp';" value="Назад">


        </form>
    </body>
</html>