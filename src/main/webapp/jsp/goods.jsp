<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dan
  Date: 25.06.2019
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="header.jsp"/>
<body>
<a href="/groups">Groups</a>
<a href="/goods">Goods</a>
<table class="table">
    <thead>
    <tr>
        <th scope="col">Remove</th>
        <th scope="col">Name</th>
        <th scope="col">Price</th>
        <th scope="col">Amount</th>
        <th scope="col">Total price</th>
        <th scope="col">Update</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="group" items="${groups}">
        <c:forEach var="good" items="${group.goods}">
            <tr>
                <th scope="row">
                    <form action="" method="post">
                        <input type="submit" value="&times;" class="page-link mx-auto">
                        <input type="hidden" name="id" value="${good.id}">
                        <input type="hidden" name="action" value="delete">
                    </form>
                </th>
                <td>${good.name}</td>
                <td>${good.price}</td>
                <td>${good.count}</td>
                <td>${good.count * good.price}</td>
                <td>
                    <form action="" method="post">
                        <input type="submit" value="Edit" class="page-link mx-auto">
                        <input type="hidden" name="id" value="${group.id}">
                        <input type="hidden" name="action" value="edit">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </c:forEach>
    <tr>
        <th scope="row">
            <button type="button" class="btn btn-outline-primary btn-lg" data-toggle="modal" data-target="#AddGoodModal">
                +
            </button>
        </th>
    </tr>
    </tbody>
</table>
</body>
<jsp:include page="addgoodmodal.jsp"/>

<jsp:include page="footer.jsp"/>

</html>

