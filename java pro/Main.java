import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Book book = new Book();
        Student student = new Student();
        IssueReturn issue = new IssueReturn(book, student);

        while (true) {
            System.out.println("\n===== Library Management System =====");
            System.out.println("1. Add New Book");
            System.out.println("2. View Books");
            System.out.println("3. Add Student");
            System.out.println("4. View Students");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("7. View Issued Books");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int ch;
            try {
                ch = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid input. Enter a number 1-8.");
                continue;
            }

            switch (ch) {
                case 1: book.addBook(); break;
                case 2: book.viewBooks(); break;
                case 3: student.addStudent(); break;
                case 4: student.viewStudents(); break;
                case 5: issue.issueBook(); break;
                case 6: issue.returnBook(); break;
                case 7: issue.viewIssued(); break;
                case 8: System.out.println("👋 Exiting..."); System.exit(0);
                default: System.out.println("Invalid choice!");
            }
        }
    }
}