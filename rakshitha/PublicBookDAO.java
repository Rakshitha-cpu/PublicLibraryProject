package rakshitha;

import java.util.List;

public interface PublicBookDAO {
    void addBook(PublicBookDTO book);
    PublicBookDTO getBookById(int id);
    List<PublicBookDTO> getAllBooks();
    void updateBook(PublicBookDTO book);
    void deleteBook(int id);
}