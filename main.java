import java.util.Scanner;

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
