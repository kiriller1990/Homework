package by.it_academy.jd2.Mk_JD2_82_21_output_homework.controller.Servletes;

import by.it_academy.jd2.Mk_JD2_82_21_output_homework.service.*;
import by.it_academy.jd2.Mk_JD2_82_21_output_homework.storage.model.Messages;
import by.it_academy.jd2.Mk_JD2_82_21_output_homework.storage.model.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;


@WebServlet(name = "MessageServlet", urlPatterns = "/message")

public class MessageServlet extends HttpServlet {
    private static final String MESSAGE_RECIPIENT_NAME = "messageRecipient";
    private static final String TEXT_PARAM = "text";
    private static final String ATTRIBUTE_NAME = "user";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String messageRecipientName = req.getParameter(MESSAGE_RECIPIENT_NAME);
        String text = req.getParameter(TEXT_PARAM);
        LocalDateTime date = LocalDateTime.now();

        HttpSession session = req.getSession();
        Users sender = (Users) session.getAttribute(ATTRIBUTE_NAME);
        Users recipient = UserFactory.getInstance().getUser(messageRecipientName);

        try {
            LoginService.getInstance().messageParameterChek(recipient);
            String recipientName = recipient.getLogin();
            Messages message = new Messages(sender, text, date);
            MessageFactory.getInstance().setMessage(recipientName,message);
            req.getRequestDispatcher("/views/message_sending_accept.jsp").forward(req, resp);
        } catch (IllegalArgumentException e) {
            req.setAttribute("name", e.getMessage());
            getServletContext().getRequestDispatcher("/views/message.jsp").forward(req,resp);
        }
    }

}
