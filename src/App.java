import java.util.ArrayList;
import java.util.Scanner;

public class App {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        int entry;
        do {
            System.out.println("1- add a student");
            System.out.println("2- show students");
            System.out.println("3- delete student");
            System.out.println("4- quitte");
            entry = scanner.nextInt();
            scanner.nextLine();
            switch (entry) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    showStudents();
                    break;
                case 3:
                    deleteStudent();
                default:
                    break;
            }
        } while (entry != 4);
    }

    private static void addStudent() {
        System.out.println("Enter the Student Id :");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the student name :");
        String name = scanner.nextLine();
        System.out.println("Enter the student age :");
        int age = scanner.nextInt();
        Student student1 = new Student(id, name, age);
        students.add(student1);
    }

    private static void showStudents() {
        for (Student s : students) {
            System.out.println(s);
        }
    }

    private static void deleteStudent() {
        System.out.println("Enter the student Id :");
        int id = scanner.nextInt();
        for (Student s : students) {
            if (s.getId() == id) {
                students.remove(s);
                break;
            }
        }
    }
}