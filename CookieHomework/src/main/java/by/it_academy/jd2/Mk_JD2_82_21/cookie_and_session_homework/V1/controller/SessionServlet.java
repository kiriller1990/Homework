package by.it_academy.jd2.Mk_JD2_82_21.cookie_and_session_homework.V1.controller;



import by.it_academy.jd2.Mk_JD2_82_21.cookie_and_session_homework.V1.service.CookieService;
import by.it_academy.jd2.Mk_JD2_82_21.cookie_and_session_homework.V1.service.SessionService;
import by.it_academy.jd2.Mk_JD2_82_21.cookie_and_session_homework.V1.storage.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "SessionServlet", urlPatterns = "/cookie")
public class SessionServlet extends HttpServlet {
    public static final String FIRST_NAME_PARAMETER = "firstName";
    public static final String SECOND_NAME_PARAMETER = "secondName";
    public static final String AGE_PARAMETER = "age";
    public static final String PERSON_STORAGE_CHOICE = "storageType";
    public static final String COOKIE_STORAGE_TYPE = "cookie";
    public static final String SESSION_STORAGE_TYPE = "session";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter(FIRST_NAME_PARAMETER); // Не забыть поменять на Headers когда доберешься до интернета, и поменять метод на doPost
        String secondName = req.getParameter(SECOND_NAME_PARAMETER);
        String age = req.getParameter(AGE_PARAMETER);
        String storageType = req.getHeader(PERSON_STORAGE_CHOICE);


        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        try{
            if (storageType.equalsIgnoreCase(SESSION_STORAGE_TYPE)) {
                Person person = new Person (firstName , secondName , age);

                String firstNameVal = SessionService.getValueForSession(req, FIRST_NAME_PARAMETER);
                SessionService.saveSession(req,FIRST_NAME_PARAMETER,firstNameVal);

                String secondNameVal = SessionService.getValueForSession(req,SECOND_NAME_PARAMETER);
                SessionService.saveSession(req, SECOND_NAME_PARAMETER,secondNameVal);

                String ageVal = SessionService.getValueForSession(req,AGE_PARAMETER);
                SessionService.saveSession(req, AGE_PARAMETER,ageVal);

                writer.write("<p><span style='color: blue;'>Hello, " + person.getSecondName()
                        +person.getFirstName() + " I know, you are " + person.getAge() + " years old.");


            }
            if(storageType.equalsIgnoreCase(COOKIE_STORAGE_TYPE)) {
                Person person = new Person (firstName , secondName , age);

                String firstNameVal = CookieService.getValueForCookie(req, FIRST_NAME_PARAMETER);
                CookieService.sendCookie(resp, FIRST_NAME_PARAMETER,firstNameVal);

                String secondNameVal = CookieService.getValueForCookie(req, SECOND_NAME_PARAMETER);
                CookieService.sendCookie(resp, SECOND_NAME_PARAMETER,secondNameVal);

                String ageVal = CookieService.getValueForCookie(req, AGE_PARAMETER);
                CookieService.sendCookie(resp, AGE_PARAMETER,ageVal);

                writer.write("Hello! " + firstNameVal + secondNameVal);
            }



            }catch(NullPointerException e){
            writer.write("<p> Не передан тип хранилища (storageType)");
            }
    }
}
