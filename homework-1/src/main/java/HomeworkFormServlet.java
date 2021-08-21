import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="HomeworkFormServlet", urlPatterns = "/test")
public class HomeworkFormServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = response.getWriter();

        String artist = request.getParameter("artist");
        String[] genres = request.getParameterValues("genres");
        String name = request.getParameter("name");

        try {
            writer.println("<p>Artist : " + artist + "</p>");
            writer.println("<p>О себе : " + name + "</p>");
            writer.println("<h4>Жанр</h4>");
            for (String genre : genres) {
                writer.println("<li>" + genre + "</li>");
            }
            writer.println("");
        } finally {
            writer.close();
        }
    }
}
