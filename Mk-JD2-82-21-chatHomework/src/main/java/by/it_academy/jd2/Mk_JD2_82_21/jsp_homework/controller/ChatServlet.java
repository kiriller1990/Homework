package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.controller;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service.MessageService;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service.UserStorageService;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Messages;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;

@WebServlet(name = "ChatServlet", urlPatterns = "/chat")
public class ChatServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException
    {
        HttpSession session = req.getSession();

        Users user = (Users) session.getAttribute("user");



        LinkedList<Messages> messages = MessageService.getInstance().getMessage(user) ;

        req.setAttribute("messages", messages);
        req.getRequestDispatcher("/views/chat.jsp").forward(req, resp);
    }

}

