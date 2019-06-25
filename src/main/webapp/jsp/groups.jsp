<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%--
  Created by IntelliJ IDEA.
  User: dan
  Date: 25.06.2019
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<body>
<a href="/groups">Groups</a>
<a href="/goods">Goods</a>
<table class="table">
    <thead>
    <tr>
        <th scope="col">Remove</th>
        <th scope="col">Group Name</th>
        <th scope="col">Total amount</th>
        <th scope="col">Total price</th>
        <th scope="col">Update</th>

    </tr>
    </thead>
    <tbody>
    <c:forEach var="group" items="${groups}">
        <tr>
            <th scope="row">
                <form action="" method="post">
                    <input type="submit" value="&times;" class="page-link mx-auto">
                    <input type="hidden" name="${group.id}" value="${group.id}">
                    <input type="hidden" name="action" value="delete">
                </form>
            </th>
            <td>${group.name}</td>
            <c:set var="amount" value="${0}"/>
            <td>${amount}</td>
            <td>
                <c:set var="price" value="${0}"/>
                    ${price}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
<jsp:include page="footer.jsp"/>

