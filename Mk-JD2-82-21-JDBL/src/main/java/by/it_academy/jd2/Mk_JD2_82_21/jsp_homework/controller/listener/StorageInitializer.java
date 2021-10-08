package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.controller.listener;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service.api.EStorageType;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.factory.DepartmentStorageFactory;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.factory.EmployeeStorageFactory;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.factory.PositionStorageFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StorageInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String storageTypeRaw = servletContextEvent.getServletContext().getInitParameter("StorageType");

        EStorageType storageType = EStorageType.valueOf(storageTypeRaw);

        DepartmentStorageFactory.setType(storageType);
        EmployeeStorageFactory.setType(storageType);
        PositionStorageFactory.setType(storageType);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
