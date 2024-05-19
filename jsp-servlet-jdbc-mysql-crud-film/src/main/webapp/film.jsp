<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>List of Films</title>
</head>
<body>
    <h1>List of Films</h1>
    <table border="1">
        <tr>
            <th>Title</th>
            <th>Year</th>
            <th>Director</th>
            <th>Stars</th>
            <th>Review</th>
        </tr>
        <c:forEach var="film" items="${film}">
            <tr>
                <td>${film.title}</td>
                <td>${film.year}</td>
                <td>${film.director}</td>
                <td>${film.stars}</td>
                <td>${film.review}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
