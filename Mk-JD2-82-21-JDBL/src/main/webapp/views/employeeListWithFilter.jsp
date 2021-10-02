<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Список должностей</title>
    </head>
    <body>
        <h1>Список должностей</h1>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

        <table border="1">
                <tr>
                <th>${'ID: '}</th>
                <th>${'Имя'}</th>
                <th>${'Зарплата'}</th>
                </tr>
                <c:forEach items="${employees}" var="item" >
                <tr>
                <td>${item.getId()}</td>
                <td><a href="/Mk-JD2-82-21-0.0.0-SNAPSHOT/cardEmployee?id_card=${item.getId()}">${item.getName()}</a></td>
                <td><fmt:formatNumber type = "number" value = "${item.getSalary()}" /></td>
                </tr>
                </c:forEach>
                </table>

                 <c:if test="${requestScope.page == 1}">
                    <b>${page}</b>
                    <a href="/Mk-JD2-82-21-0.0.0-SNAPSHOT/employeeList?pageNumber=${page+1}">${page+1}</a>
                    <a href="/Mk-JD2-82-21-0.0.0-SNAPSHOT/employeeList?pageNumber=${page+2}">${page+2}</a>
                    <a href="/Mk-JD2-82-21-0.0.0-SNAPSHOT/employeeList?pageNumber=${page+3}">${page+3}</a>
                    <a href="/Mk-JD2-82-21-0.0.0-SNAPSHOT/employeeList?pageNumber=${page+4}">${page+4}</a>
                    <a href="/Mk-JD2-82-21-0.0.0-SNAPSHOT/employeeList?pageNumber=${page+5}">${page+5}</a>
                    <b>...</b>
                    <a href="/Mk-JD2-82-21-0.0.0-SNAPSHOT/employeeList?pageNumber=${lastPage}">${lastPage}</a>
                 </c:if>
                 <c:if test="${requestScope.page != 1}">
                                     <c:if test="${page > 3}">
                                        <a href="/Mk-JD2-82-21-0.0.0-SNAPSHOT/employeeList?pageNumber=1">1</a>
                                        <b>...</b>
                                         </c:if>
                                     <c:if test="${page > 2}">
                                        <a href="/Mk-JD2-82-21-0.0.0-SNAPSHOT/employeeList?pageNumber=${page-2}">${page-2}</a>
                                     </c:if>
                                        <a href="/Mk-JD2-82-21-0.0.0-SNAPSHOT/employeeList?pageNumber=${page-1}">${page-1}</a>
                                        <b>${page}</b>
                                    <c:if test="${page <= lastPage-2}">
                                        <a href="/Mk-JD2-82-21-0.0.0-SNAPSHOT/employeeList?pageNumber=${page+1}">${page+1}</a>
                                        <a href="/Mk-JD2-82-21-0.0.0-SNAPSHOT/employeeList?pageNumber=${page+2}">${page+2}</a>
                                     <c:if test="${page >= 2}">
                                        <a href="/Mk-JD2-82-21-0.0.0-SNAPSHOT/employeeList?pageNumber=${page+3}">${page+3}</a>
                                     </c:if>
                                     </c:if>
                                        <b>...</b>
                                        <a href="/Mk-JD2-82-21-0.0.0-SNAPSHOT/employeeList?pageNumber=${lastPage}">${lastPage}</a>

                                  </c:if><p></p>

        <br>
        <input type="button" onclick="location.href='/Mk-JD2-82-21-0.0.0-SNAPSHOT/';" value="Назад"> <p></p>

        Поиск по имени:
        <input type="text" name="nameFilter" placeholder = "Поиск по имени" size=50>
        Поиск по зарплате
        <input type="text" name="salaryValue" placeholder = "Поиск по зарплате" size=50>
        <select name="filterType">
                    <option>больше</option>
                    <option>меньше</option>
                </select>
        <input type="submit" value="Сортировать">
        </body>
</html>