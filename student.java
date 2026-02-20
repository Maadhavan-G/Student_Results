import java.io.Serializable;

public class Student implements Serializable {

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
        for (int s : scores) {
            sum += s;
        }

        mean = sum / 5.0;

        // Result
        result = (mean < 50) ? "Fail" : "Pass";

        // Grade
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
