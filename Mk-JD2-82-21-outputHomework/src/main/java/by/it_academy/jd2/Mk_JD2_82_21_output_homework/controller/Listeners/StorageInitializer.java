package by.it_academy.jd2.Mk_JD2_82_21_output_homework.controller.Listeners;

import by.it_academy.jd2.Mk_JD2_82_21_output_homework.service.MessageFactory;
import by.it_academy.jd2.Mk_JD2_82_21_output_homework.service.UserFactory;
import by.it_academy.jd2.Mk_JD2_82_21_output_homework.service.api.EStorageType;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.time.LocalDateTime;

public class StorageInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        String storageTypeRaw = sce.getServletContext().getInitParameter("StorageType");

        EStorageType storageType = EStorageType.valueOf(storageTypeRaw);
        LocalDateTime time = LocalDateTime.now();
        sce.getServletContext().setAttribute("time", time);

       MessageFactory.setType(storageType);
       UserFactory.setType(storageType);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
