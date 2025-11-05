import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Book {
    private final Scanner sc = new Scanner(System.in);
    private final List<BookEntry> books = new ArrayList<>();

    private static class BookEntry {
        String title;
        String author;
        double price;
        int qty;

        BookEntry(String title, String author, double price, int qty) {
            this.title = title;
            this.author = author;
            this.price = price;
            this.qty = qty;
        }
    }

    public void addBook() {
        try {
            System.out.print("Enter book title: ");
            String title = sc.nextLine().trim();
            System.out.print("Enter author: ");
            String author = sc.nextLine().trim();
            System.out.print("Enter price: ");
            double price = Double.parseDouble(sc.nextLine().trim());
            System.out.print("Enter quantity: ");
            int qty = Integer.parseInt(sc.nextLine().trim());

            if (title.isEmpty()) {
                System.out.println("Title cannot be empty.");
                return;
            }
            books.add(new BookEntry(title, author, price, qty));
            System.out.println("✅ Book added (in-memory).");
        } catch (NumberFormatException nfe) {
            System.out.println("Error: invalid number. " + nfe.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        System.out.println("\n--- Book List ---");
        for (int i = 0; i < books.size(); i++) {
            BookEntry b = books.get(i);
            System.out.println((i + 1) + " | " + b.title + " | " + b.author + " | " + b.price + " | Qty: " + b.qty);
        }
    }

    public boolean contains(String title) {
        return books.stream().anyMatch(b -> b.title.equalsIgnoreCase(title));
    }

    public boolean hasAvailable(String title) {
        return books.stream().anyMatch(b -> b.title.equalsIgnoreCase(title) && b.qty > 0);
    }

    public boolean decrementQuantity(String title) {
        for (BookEntry b : books) {
            if (b.title.equalsIgnoreCase(title) && b.qty > 0) {
                b.qty--;
                return true;
            }
        }
        return false;
    }

    public boolean incrementQuantity(String title) {
        for (BookEntry b : books) {
            if (b.title.equalsIgnoreCase(title)) {
                b.qty++;
                return true;
            }
        }
        return false;
    }
}