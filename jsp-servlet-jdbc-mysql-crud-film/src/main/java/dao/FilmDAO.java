package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Film;

public class FilmDAO {
    private final String url = "jdbc:mysql://localhost:3306/createfilms"; //Give path of your own mysql database
    private final String user = "root";
    private final String password = "1234";

    private static final String SELECT_ALL_FILMS = "SELECT * FROM films";
    private static final String SELECT_FILM_BY_ID = "SELECT * FROM films WHERE id = ?";
    private static final String INSERT_FILM = "INSERT INTO films (title, year, director, stars, review) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_FILM = "UPDATE films SET title = ?, year = ?, director = ?, stars = ?, review = ? WHERE id = ?";
    private static final String DELETE_FILM = "DELETE FROM films WHERE id = ?";
    private static final String SEARCH_FILMS = "SELECT * FROM films WHERE title LIKE ? OR director LIKE ?";

    public FilmDAO() {}

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    private void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Film> getAllFilms() {
        List<Film> films = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_FILMS);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Film film = new Film();
                film.setId(rs.getInt("id"));
                film.setTitle(rs.getString("title"));
                film.setYear(rs.getInt("year"));
                film.setDirector(rs.getString("director"));
                film.setStars(rs.getString("stars"));
                film.setReview(rs.getString("review"));
                films.add(film);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return films;
    }

    public Film getFilmByID(int id) {
        Film film = null;
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_FILM_BY_ID)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    film = new Film();
                    film.setId(rs.getInt("id"));
                    film.setTitle(rs.getString("title"));
                    film.setYear(rs.getInt("year"));
                    film.setDirector(rs.getString("director"));
                    film.setStars(rs.getString("stars"));
                    film.setReview(rs.getString("review"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return film;
    }

    public void insertFilm(Film film) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_FILM)) {
            stmt.setString(1, film.getTitle());
            stmt.setInt(2, film.getYear());
            stmt.setString(3, film.getDirector());
            stmt.setString(4, film.getStars());
            stmt.setString(5, film.getReview());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateFilm(Film film) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_FILM)) {
            stmt.setString(1, film.getTitle());
            stmt.setInt(2, film.getYear());
            stmt.setString(3, film.getDirector());
            stmt.setString(4, film.getStars());
            stmt.setString(5, film.getReview());
            stmt.setInt(6, film.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFilm(int id) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_FILM)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Film> searchFilm(String searchStr) {
        List<Film> films = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(SEARCH_FILMS)) {
            stmt.setString(1, "%" + searchStr + "%");
            stmt.setString(2, "%" + searchStr + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Film film = new Film();
                    film.setId(rs.getInt("id"));
                    film.setTitle(rs.getString("title"));
                    film.setYear(rs.getInt("year"));
                    film.setDirector(rs.getString("director"));
                    film.setStars(rs.getString("stars"));
                    film.setReview(rs.getString("review"));
                    films.add(film);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return films;
    }
}
