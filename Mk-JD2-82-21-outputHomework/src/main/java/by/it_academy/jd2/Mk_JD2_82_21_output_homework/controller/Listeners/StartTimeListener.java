package by.it_academy.jd2.Mk_JD2_82_21_output_homework.controller.Listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.time.LocalDateTime;

public class StartTimeListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LocalDateTime time = LocalDateTime.now();
        sce.getServletContext().setAttribute("time", time);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
