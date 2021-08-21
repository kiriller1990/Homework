package by.it_academy.jd2.Mk_JD2_82_21.servlet_homework.controller;

import by.it_academy.jd2.Mk_JD2_82_21.servlet_homework.service.CustomComparator;
import by.it_academy.jd2.Mk_JD2_82_21.servlet_homework.service.JobConditionsException;
import by.it_academy.jd2.Mk_JD2_82_21.servlet_homework.service.VoteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import static java.util.Map.Entry.comparingByValue;

@WebServlet(name = "QuizServlet", urlPatterns = "/")
public class QuizServlet extends HttpServlet {

    private final VoteService service;

    public QuizServlet() {
        this.service = VoteService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();



        writer.write("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "\t<meta charset=\"UTF-8\">\n" +
                "\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "\t<title>Квиз</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\t<form action=\"/Mk-JD2-82-21-0.0.0-SNAPSHOT/\" method=\"POST\">\n" +
                "\t\t<label for=\"artist\">Группа</label>\n" +
                "\t\t<select name=\"artist\">\n" +
                "\t\t\t<option value=\"1\">МОТ</option>\n" +
                "\t\t\t<option value=\"2\">Каста</option>\n" +
                "\t\t\t<option value=\"3\">50Cent</option>\n" +
                "\t\t\t<option value=\"4\">Eminem</option>\n" +
                "\t\t</select><br/>\n" +
                "\t\t<label for=\"genre\">Жанр</label><br/>\n" +
                "\t\t<input type=\"checkbox\" name=\"genre\" value=\"1\"/> Рок <br/>\n" +
                "\t\t<input type=\"checkbox\" name=\"genre\" value=\"2\"/> Поп <br/>\n" +
                "\t\t<input type=\"checkbox\" name=\"genre\" value=\"3\"/> Фолк <br/>\n" +
                "\t\t<input type=\"checkbox\" name=\"genre\" value=\"4\"/> Альт <br/>\n" +
                "\t\t<input type=\"checkbox\" name=\"genre\" value=\"5\"/> Клкассика <br/>\n" +
                "\t\t<input type=\"checkbox\" name=\"genre\" value=\"6\"/> Джаз <br/>\n" +
                "\t\t<input type=\"checkbox\" name=\"genre\" value=\"7\"/> Тиктоник <br/>\n" +
                "\t\t<input type=\"checkbox\" name=\"genre\" value=\"8\"/> Техно <br/>\n" +
                "\t\t<input type=\"checkbox\" name=\"genre\" value=\"9\"/> Хаус <br/>\n" +
                "\t\t<input type=\"checkbox\" name=\"genre\" value=\"10\"/> Хип-хоп <br/>\n" +
                "\t\t<label for=\"about\">О себе</label><br/>\n" +
                "\t\t<textarea name=\"about\"></textarea>\n" +
                "\t\t<input type=\"submit\" name=\"Отправить\">\n" +
                "\t</form>\n" +
                "</body>\n" +
                "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String artist = req.getParameter("artist");
        String[] genres = req.getParameterValues("genre");
        String about = req.getParameter("about");


        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        try {

            this.service.addVote(artist, genres, about);

            Map<String, Integer> artistResult = this.service.getArtistResult();
            Map<String, Integer> genreResult = this.service.getGenreResult();
            List<String> aboutResult = this.service.getAboutResult();

            writer.write("<h1> Результаты голосования: </h1>");
            writer.write("<p></p>");
            writer.write("<h4>Любимый исполнитель: </h4>");
            writer.write("<ul>");

            artistResult.entrySet().stream()
                    .sorted(new CustomComparator())
                    .forEach(x->{
                        switch (x.getKey()) {
                            case "1":
                                writer.write("<li>МОТ - ");
                                break;
                            case "2":
                                writer.write("<li>Каста - ");
                                break;
                            case "3":
                                writer.write("<li>50Cent - ");
                                break;
                            case "4":
                                writer.write("<li>Eminem - ");
                                break;
                        }
                writer.write(String.valueOf(x.getValue()));
                writer.write("</li>");
            });

            writer.write("</ul>");
            writer.write("<p></p>");
            writer.write("<h4>Любимый жанр: </h4>");
            writer.write("<ul>");

            genreResult.entrySet().stream()
                    .sorted(new CustomComparator())
                    .forEach(x->{
                                switch (x.getKey()) {
                    case "1":
                        writer.write("<li>Рок - ");
                        break;
                    case "2":
                        writer.write("<li>Поп - ");
                        break;
                    case "3":
                        writer.write("<li>Фолк - ");
                        break;
                    case "4":
                        writer.write("<li>Альт - ");
                        break;
                    case "5":
                        writer.write("<li>Клкассика - ");
                        break;
                    case "6":
                        writer.write("<li>Джаз - ");
                        break;
                    case "7":
                        writer.write("<li>Тиктоник - ");
                        break;
                    case "8":
                        writer.write("<li>Техно - ");
                        break;
                    case "9":
                        writer.write("<li>Хаус - ");
                        break;
                    case "10":
                        writer.write("<li>Хип-хоп - ");
                        break;
                }
                writer.write(String.valueOf(x.getValue()));
                writer.write("</li>");
            });
            writer.write("</ul>");

            for(String s : aboutResult) {
                writer.println("<p>" + s + "</p>");
            }
        }catch (JobConditionsException e) {
            writer.write(e.getMessage());
        }
    }
}
