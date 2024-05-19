<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Film</title>
</head>
<body>
    <h1>Edit Film</h1>
    <form action="FilmServlet" method="post">
        <input type="hidden" name="action" value="add">
        <label for="title">Title:</label>
        <input type="text" id="title" name="title" required><br>
        <label for="year">Year:</label>
        <input type="number" id="year" name="year" required><br>
        <label for="director">Director:</label>
        <input type="text" id="director" name="director" required><br>
        <label for="stars">Stars:</label>
        <input type="text" id="stars" name="stars" required><br>
        <label for="review">Review:</label>
        <input type="text" id="review" name="review" required><br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>
