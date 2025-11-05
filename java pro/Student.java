import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student {
    private final Scanner sc = new Scanner(System.in);
    private final List<StudentEntry> students = new ArrayList<>();

    private static class StudentEntry {
        String name;
        String roll;
        String cls;
        String email;

        StudentEntry(String name, String roll, String cls, String email) {
            this.name = name;
            this.roll = roll;
            this.cls = cls;
            this.email = email;
        }
    }

    public void addStudent() {
        System.out.print("Enter name: ");
        String name = sc.nextLine().trim();
        System.out.print("Enter roll no: ");
        String roll = sc.nextLine().trim();
        System.out.print("Enter class: ");
        String cls = sc.nextLine().trim();
        System.out.print("Enter email: ");
        String email = sc.nextLine().trim();

        if (name.isEmpty() || roll.isEmpty()) {
            System.out.println("Name and roll number are required.");
            return;
        }

        students.add(new StudentEntry(name, roll, cls, email));
        System.out.println("✅ Student added (in-memory).");
    }

    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students registered.");
            return;
        }
        System.out.println("\n--- Student List ---");
        for (int i = 0; i < students.size(); i++) {
            StudentEntry s = students.get(i);
            System.out.println((i + 1) + " | " + s.name + " | Roll: " + s.roll + " | " + s.cls + " | " + s.email);
        }
    }

    public boolean contains(String rollOrName) {
        String key = rollOrName == null ? "" : rollOrName.trim();
        return students.stream().anyMatch(s ->
                s.roll.equalsIgnoreCase(key) || s.name.equalsIgnoreCase(key));
    }
}