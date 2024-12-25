package controller;

import java.util.Scanner;

import model.School;
import model.Student;
import view.StudentView;

public class StudentController {
    private School school;
    private StudentView view;
    private Scanner scanner;

    public StudentController(School school) {
        this.school = school;
        this.view = new StudentView();
        this.scanner = new Scanner(System.in);
    }

    public void manageStudent() {
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
                    updateStudent();
                    break;
                case 3:
                    deleteStudent();
                case 4:
                    view.displayStudents(school);
                    break;
                case 5:
                    System.out.println("quite");
                    break;
                default:
                    break;
            }
        } while (entry != 5);
    }

    private void updateStudent() {
        System.out.println("enter the student id :");
        int id = scanner.nextInt();
        scanner.nextLine();
        Student student = school.getStudents().stream().filter(s -> s.getId() == id).findFirst().orElse(null);
        if (student != null) {
            System.out.println("enter the new name :");
            String name = scanner.nextLine();
            System.out.println("enter the new age :");
            int age = scanner.nextInt();
            scanner.nextLine();
            student.setName(name);
            student.setAge(age);
            System.out.println("student updated successfully");
        } else {
            System.out.println("student unfound");
        }
    }

    private void addStudent() {
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

    private void deleteStudent() {
        System.out.println("Enter the student Id :");
        int id = scanner.nextInt();
        for (Student s : school.getStudents()) {
            if (s.getId() == id) {
                school.deleteStudent(s);
                break;
            }
        }
    }
}
