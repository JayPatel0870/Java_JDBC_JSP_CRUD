package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FilmDAO;
import model.Film;

@WebServlet("/FilmServlet")
public class FilmServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private FilmDAO filmDAO;

    public void init() {
        filmDAO = new FilmDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "add":
                showAddForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteFilm(request, response);
                break;
            default:
                listFilms(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "add":
                addFilm(request, response);
                break;
            case "edit":
                updateFilm(request, response);
                break;
        }
    }

    private void listFilms(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Film> films = filmDAO.getAllFilms();
        request.setAttribute("films", films);
        request.getRequestDispatcher("film.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("editFilm.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Film film = filmDAO.getFilmByID(id);
        request.setAttribute("film", film);
        request.getRequestDispatcher("editFilm.jsp").forward(request, response);
    }

    private void addFilm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        int year = Integer.parseInt(request.getParameter("year"));
        String director = request.getParameter("director");
        String stars = request.getParameter("stars");
        String review = request.getParameter("review");

        Film film = new Film(year, title, year, director, stars, review);
        filmDAO.insertFilm(film);
        response.sendRedirect("FilmServlet");
    }

    private void updateFilm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        int year = Integer.parseInt(request.getParameter("year"));
        String director = request.getParameter("director");
        String stars = request.getParameter("stars");
        String review = request.getParameter("review");

        Film film = new Film(id, title, year, director, stars, review);
        filmDAO.updateFilm(film);
        response.sendRedirect("FilmServlet");
    }

    private void deleteFilm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        filmDAO.deleteFilm(id);
        response.sendRedirect("FilmServlet");
    }
}
