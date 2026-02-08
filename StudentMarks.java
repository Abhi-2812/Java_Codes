import java.util.Scanner;

class Student {

    String name;
    int rollNumber;
    int[] scores = new int[5];
    int avgMarks;
    char finalGrade;

    static int[] totalPerSubject = new int[5];
    static int totalStudents = 0;

    void input(Scanner scanner) {
        System.out.println("Enter name:");
        name = scanner.nextLine();

        System.out.println("Enter roll number:");
        rollNumber = scanner.nextInt();

        System.out.println("Enter marks for 5 subjects (out of 100):");
        int totalMarks = 0;

        for (int i = 0; i < 5; i++) {
            scores[i] = scanner.nextInt();
            totalMarks += scores[i];
            totalPerSubject[i] += scores[i];
        }

        avgMarks = totalMarks / 5;

        if (avgMarks >= 75)
            finalGrade = 'A';
        else if (avgMarks >= 60)
            finalGrade = 'B';
        else
            finalGrade = 'C';

        totalStudents++;
        scanner.nextLine(); // clear buffer
    }

    void display() {
        System.out.println("\nName: " + name);
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Average Marks: " + avgMarks);
        System.out.println("Grade: " + finalGrade);
    }

    static void displaySubjectAverages() {
        System.out.println("\nAverage marks for each subject:");
        for (int i = 0; i < 5; i++) {
            System.out.println("Subject " + (i + 1) + ": " + (totalPerSubject[i] / totalStudents));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter number of students:");
        int studentLimit = scanner.nextInt();
        scanner.nextLine();

        Student[] students = new Student[studentLimit];

        for (int i = 0; i < studentLimit; i++) {
            students[i] = new Student();
            students[i].input(scanner);
            students[i].display();
        }

        Student.displaySubjectAverages();
        scanner.close();
    }
}
