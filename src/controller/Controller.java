package controller;

import java.util.Scanner;

import model.School;
import model.Student;
import view.StudentView;

public class Controller {
    static Scanner scanner = new Scanner(System.in);
    static School school = new School();
    StudentView view = new StudentView();
    public void start(){
         int entry;
        do {
            view.displayStudentMenu();
            entry = scanner.nextInt();
            scanner.nextLine();
            switch (entry) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    view.displayStudents(school);
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
        school.addStudent(student1);
    }
    private static void deleteStudent() {
        System.out.println("Enter the student Id :");
        int id = scanner.nextInt();
        for (Student s : school.gStudents()) {
            if (s.getId() == id) {
                school.deleteStudent(s);
                break;
            }
        }
    }
}
