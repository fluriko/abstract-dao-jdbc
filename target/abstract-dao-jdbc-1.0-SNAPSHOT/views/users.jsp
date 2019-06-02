<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: insania
  Date: 2019-05-30
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users list</title>
</head>
<body>
<c:forEach var="user" items="${users}">
    <h5>
        User <c:out value="${user.id}"/>:
    <c:out value="${user.login}"/>,
    <c:out value="${user.email}"/>,
    <c:out value="${user.password}"/>,
        <a href="/editUser?id=${user.id}">edit</a>
        <a href="/deleteUser?id=${user.id}">delete</a>
    </h5>
</c:forEach>
<button onclick="location.href='/addUser'">Add new user</button>
<button onclick="location.href='/home'">Back to home</button>
</body>
</html>
