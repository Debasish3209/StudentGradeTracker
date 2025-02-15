
import java.util.*;

class Student {
    private String name;
    private Map<String, Double> grades;

    public Student(String name) {
        this.name = name;
        this.grades = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void addGrade(String subject, double grade) {
        grades.put(subject, grade);
    }

    public void removeGrade(String subject) {
        grades.remove(subject);
    }

    public double getAverageGrade() {
        if (grades.isEmpty()) return 0.0;
        double sum = 0;
        for (double grade : grades.values()) {
            sum += grade;
        }
        return sum / grades.size();
    }

    public void displayGrades() {
        System.out.println("Grades for " + name + ":");
        for (Map.Entry<String, Double> entry : grades.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Average Grade: " + getAverageGrade());
    }
}

public class StudentGradeTracker {
    private static List<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Student Grade Tracker ---");
            System.out.println("1. Add Student");
            System.out.println("2. Add Grade");
            System.out.println("3. View Student Grades");
            System.out.println("4. Remove Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> addGrade();
                case 3 -> viewStudentGrades();
                case 4 -> removeStudent();
                case 5 -> {
                    System.out.println("Exiting... Thank you!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        students.add(new Student(name));
        System.out.println(name + " has been added.");
    }

    private static void addGrade() {
        Student student = findStudent();
        if (student == null) return;

        System.out.print("Enter subject: ");
        String subject = scanner.nextLine();
        System.out.print("Enter grade: ");
        double grade = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        student.addGrade(subject, grade);
        System.out.println("Grade added successfully.");
    }

    private static void viewStudentGrades() {
        Student student = findStudent();
        if (student != null) {
            student.displayGrades();
        }
    }

    private static void removeStudent() {
        Student student = findStudent();
        if (student != null) {
            students.remove(student);
            System.out.println(student.getName() + " has been removed.");
        }
    }

    private static Student findStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                return student;
            }
        }

        System.out.println("Student not found.");
        return null;
    }
}
