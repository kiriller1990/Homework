package by.it_academy.jd2.Mk_JD2_82_21_output_homework.controller.Servletes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@WebServlet(name = "AboutServlet", urlPatterns = "/about")
public class AboutServlet extends HttpServlet {
    private static final String STORAGE_TYPE = "StorageType";
    private static String START_TIME_PARAM_NAME = "time";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocalDateTime dateTime = (LocalDateTime) req.getServletContext().getAttribute(START_TIME_PARAM_NAME);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyy в HH:mm");
        String time = dateTime.format(formatter);
        req.setAttribute("time", time);

        String StorageType = (String) req.getServletContext().getInitParameter(STORAGE_TYPE);
        req.setAttribute("StorageType", StorageType);

        req.getRequestDispatcher("/views/about.jsp").forward(req, resp);
    }
}
