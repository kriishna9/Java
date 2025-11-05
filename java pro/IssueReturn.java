import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class IssueReturn {
    private final Map<String, String> issued = new HashMap<>(); // bookTitle -> studentRollOrName
    private final Scanner sc = new Scanner(System.in);
    private final Book bookManager;
    private final Student studentManager;

    public IssueReturn(Book bookManager, Student studentManager) {
        this.bookManager = bookManager;
        this.studentManager = studentManager;
    }

    public void issueBook() {
        System.out.print("Enter book title to issue: ");
        String book = sc.nextLine().trim();
        if (book.isEmpty()) {
            System.out.println("Book title cannot be empty.");
            return;
        }
        if (!bookManager.contains(book)) {
            System.out.println("That book is not in catalog. Add it first.");
            return;
        }
        if (!bookManager.hasAvailable(book)) {
            System.out.println("No copies available to issue.");
            return;
        }
        System.out.print("Enter student roll or name: ");
        String student = sc.nextLine().trim();
        if (student.isEmpty()) {
            System.out.println("Student identifier cannot be empty.");
            return;
        }
        if (!studentManager.contains(student)) {
            System.out.println("Student not registered. Add the student first.");
            return;
        }
        if (issued.containsKey(book)) {
            System.out.println("Book already issued to: " + issued.get(book));
            return;
        }
        boolean dec = bookManager.decrementQuantity(book);
        if (!dec) {
            System.out.println("Failed to reserve a copy.");
            return;
        }
        issued.put(book, student);
        System.out.println("✅ Issued \"" + book + "\" to " + student);
    }

    public void returnBook() {
        System.out.print("Enter book title to return: ");
        String book = sc.nextLine().trim();
        if (book.isEmpty()) {
            System.out.println("Book title cannot be empty.");
            return;
        }
        String who = issued.remove(book);
        if (who == null) {
            System.out.println("That book was not issued.");
            return;
        }
        bookManager.incrementQuantity(book);
        System.out.println("✅ Book returned: " + book + " (was issued to " + who + ")");
    }

    public void viewIssued() {
        if (issued.isEmpty()) {
            System.out.println("No books issued.");
            return;
        }
        System.out.println("\n--- Issued Books ---");
        int i = 1;
        for (Map.Entry<String, String> e : issued.entrySet()) {
            System.out.println((i++) + ". \"" + e.getKey() + "\" -> " + e.getValue());
        }
    }
}