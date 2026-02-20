import java.io.*;
import java.util.*;

public class StudentManager {

    private ArrayList<Student> database = new ArrayList<>();
    private Scanner input = new Scanner(System.in);

    public void insertRecord() {
        System.out.print("Enter ID: ");
        int id = input.nextInt();
        input.nextLine();

        System.out.print("Enter Name: ");
        String name = input.nextLine();

        int[] marks = new int[5];
        System.out.println("Enter 5 subject scores:");
        for (int i = 0; i < 5; i++) {
            marks[i] = input.nextInt();
        }

        database.add(new Student(id, name, marks));
        System.out.println("Record stored.");
    }

    public void showAll() {
        System.out.println("\nAll Students:");
        for (Student s : database) {
            s.printSummary();
        }
    }

    public void showFailures() {
        System.out.println("\nFailed Students:");
        for (Student s : database) {
            if (s.isFailCase()) {
                s.printSummary();
            }
        }
    }

    public void exportData() {
        try {
            ObjectOutputStream writer =
                    new ObjectOutputStream(new FileOutputStream("records.bin"));
            writer.writeObject(database);
            writer.close();
            System.out.println("Data saved successfully.");
        } catch (Exception e) {
            System.out.println("Saving error: " + e.getMessage());
        }
    }
}
