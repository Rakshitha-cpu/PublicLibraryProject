
package main;

import dto.PublicBookDTO;
import service.PublicBookService;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        PublicBookService service = new PublicBookService();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Book");
            System.out.println("2. Get Book By ID");
            System.out.println("3. Get All Books");
            System.out.println("4. Update Book");
            System.out.println("5. Delete Book");
            System.out.println("6. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Title: "); String title = sc.next();
                    System.out.print("Author: "); String author = sc.next();
                    System.out.print("Available (true/false): "); boolean avail = sc.nextBoolean();
                    service.addBook(new PublicBookDTO(0, title, author, avail));
                }
                case 2 -> {
                    System.out.print("ID: "); int id = sc.nextInt();
                    PublicBookDTO b = service.getBook(id);
                    if (b != null) System.out.println(b.getId() + " " + b.getTitle() + " " + b.getAuthor() + " " + b.isAvailable());
                    else System.out.println("Book not found");
                }
                case 3 -> {
                    List<PublicBookDTO> list = service.getAllBooks();
                    list.forEach(b -> System.out.println(b.getId() + " " + b.getTitle() + " " + b.getAuthor() + " " + b.isAvailable()));
                }
                case 4 -> {
                    System.out.print("ID: "); int id = sc.nextInt();
                    System.out.print("New Title: "); String title = sc.next();
                    System.out.print("New Author: "); String author = sc.next();
                    System.out.print("Available (true/false): "); boolean avail = sc.nextBoolean();
                    service.updateBook(new PublicBookDTO(id, title, author, avail));
                }
                case 5 -> {
                    System.out.print("ID: "); int id = sc.nextInt();
                    service.deleteBook(id);
                }
                case 6 -> System.exit(0);
            }
        }
    }
}

