import java.io.*;
import java.util.*;

class Student implements Serializable {

    private int id;
    private String fullName;
    private int[] scores;
    private double mean;
    private String result;
    private String grade;

    public Student(int id, String fullName, int[] scores) {
        this.id = id;
        this.fullName = fullName;
        this.scores = scores;
        processResult();
    }

    private void processResult() {
        int sum = 0;
        for (int s : scores) sum += s;

        mean = sum / 5.0;

        result = (mean < 50) ? "Fail" : "Pass";

        if (mean >= 90) grade = "A";
        else if (mean >= 75) grade = "B";
        else if (mean >= 60) grade = "C";
        else if (mean >= 50) grade = "D";
        else grade = "F";
    }

    public boolean isFailCase() {
        return mean < 50;
    }

    public void printSummary() {
        System.out.println(
            id + " - " + fullName +
            " | Avg: " + String.format("%.2f", mean) +
            " | Grade: " + grade +
            " | Status: " + result
        );
    }
}


class StudentManager {

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
        for (int i = 0; i < 5; i++) marks[i] = input.nextInt();

        database.add(new Student(id, name, marks));
        System.out.println("Record stored.");
    }

    public void showAll() {
        System.out.println("\nAll Students:");
        for (Student s : database) s.printSummary();
    }

    public void showFailures() {
        System.out.println("\nFailed Students:");
        for (Student s : database) {
            if (s.isFailCase()) s.printSummary();
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

public class Main {

    public static void main(String[] args) {

        StudentManager manager = new StudentManager();
        Scanner input = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n--- Student Manager ---");
            System.out.println("1. Insert new record");
            System.out.println("2. Display all records");
            System.out.println("3. Display failed students");
            System.out.println("4. Save records to file");
            System.out.println("5. Quit");
            System.out.print("Choose: ");

            option = input.nextInt();

            switch (option) {
                case 1 -> manager.insertRecord();
                case 2 -> manager.showAll();
                case 3 -> manager.showFailures();
                case 4 -> manager.exportData();
                case 5 -> System.out.println("Application closed.");
                default -> System.out.println("Invalid selection.");
            }

        } while (option != 5);

        input.close();
    }
}
