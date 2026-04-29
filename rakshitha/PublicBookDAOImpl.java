package rakshitha;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PublicBookDAOImpl implements PublicBookDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/rnsitdb";
    private static final String USER = "root";
    private static final String PASS = "your_password"; // CHANGE THIS

    @Override
    public void addBook(PublicBookDTO book) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pst = con.prepareStatement(
                     "INSERT INTO public_books(title, author, available) VALUES(?,?,?)")) {

            pst.setString(1, book.getTitle());
            pst.setString(2, book.getAuthor());
            pst.setBoolean(3, book.isAvailable());
            pst.executeUpdate();

        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public PublicBookDTO getBookById(int id) {
        PublicBookDTO book = null;
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pst = con.prepareStatement("SELECT * FROM public_books WHERE id=?")) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                book = new PublicBookDTO(rs.getInt("id"), rs.getString("title"),
                        rs.getString("author"), rs.getBoolean("available"));
            }

        } catch (Exception e) { e.printStackTrace(); }
        return book;
    }

    @Override
    public List<PublicBookDTO> getAllBooks() {
        List<PublicBookDTO> list = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pst = con.prepareStatement("SELECT * FROM public_books")) {

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new PublicBookDTO(rs.getInt("id"), rs.getString("title"),
                        rs.getString("author"), rs.getBoolean("available")));
            }

        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public void updateBook(PublicBookDTO book) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pst = con.prepareStatement(
                     "UPDATE public_books SET title=?, author=?, available=? WHERE id=?")) {

            pst.setString(1, book.getTitle());
            pst.setString(2, book.getAuthor());
            pst.setBoolean(3, book.isAvailable());
            pst.setInt(4, book.getId());
            pst.executeUpdate();

        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public void deleteBook(int id) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pst = con.prepareStatement("DELETE FROM public_books WHERE id=?")) {

            pst.setInt(1, id);
            pst.executeUpdate();

        } catch (Exception e) { e.printStackTrace(); }
    }
}
