import java.util.Scanner;

public class StudentGetData {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter the number of subjects: ");
        int numSubjects = sc.nextInt();
        double[] marks = new double[numSubjects];
        double total = 0;

        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter marks for Subject " + (i + 1) + " out of 100: ");
            marks[i] = sc.nextDouble();
            total += marks[i];
        }
        
        System.out.println("Total Marks obtained out of " + (numSubjects * 100) + " are: " + total);
        
        double avg = calculateAverage(total, numSubjects);
        System.out.println("Average Percentage is: " + avg + "%");
        
        String grade = calculateGrade(avg);
        System.out.println("Grade Obtained: " + grade);
    }

    public static double calculateAverage(double total, int numSubjects) {
        return total / numSubjects;
    }

    public static String calculateGrade(double avg) {
        if (avg >= 90) return "A+";
        if (avg >= 80) return "A";
        if (avg >= 70) return "B+";
        if (avg >= 60) return "B";
        if (avg >= 50) return "C";
        if (avg >= 40) return "D";
        return "Fail";
    }
}
