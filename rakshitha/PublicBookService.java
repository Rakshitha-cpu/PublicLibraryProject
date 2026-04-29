package rakshitha;


import java.util.List;

public class PublicBookService {
    private PublicBookDAO dao = new PublicBookDAOImpl();

    public void addBook(PublicBookDTO book) {
        if (book.getTitle() == null || book.getTitle().isEmpty()) {
            System.out.println("Title cannot be empty");
            return;
        }
        if (book.getAuthor() == null || book.getAuthor().isEmpty()) {
            System.out.println("Author cannot be empty");
            return;
        }
        dao.addBook(book);
    }

    public PublicBookDTO getBook(int id) {
        return dao.getBookById(id);
    }

    public List<PublicBookDTO> getAllBooks() {
        return dao.getAllBooks();
    }

    public void updateBook(PublicBookDTO book) {
        dao.updateBook(book);
    }

    public void deleteBook(int id) {
        dao.deleteBook(id);
    }
}
