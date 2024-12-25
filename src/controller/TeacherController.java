package controller;

import java.util.Scanner;

import model.School;
import model.Teacher;
import view.TeacherView;

public class TeacherController {
    private School school;
    private TeacherView view;
    private Scanner scanner;

    public TeacherController(School school) {
        this.school = school;
        this.view = new TeacherView();
        this.scanner = new Scanner(System.in);
    }

    public void manageTeacher() {
        int entry;
        do {
            view.displayTeacherMenu();
            entry = scanner.nextInt();
            scanner.nextLine();
            switch (entry) {
                case 1:
                    addTeacher();
                    break;
                case 2:
                    updateTeacher();
                    break;
                case 3:
                    deleteTeacher();
                    break;
                case 4:
                    view.displayTeachers(school.getTeachers());
                    break;
                case 5:
                    System.out.println("quite");
                    break;
                default:
                    break;
            }
        } while (entry != 5);
    }

    private void addTeacher() {
        System.out.println("Enter the Teacher Id :");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the Teacher name :");
        String name = scanner.nextLine();
        System.out.println("Enter the Teacher subject :");
        String subject = scanner.nextLine();
        Teacher teacher = new Teacher(id, name, subject);
        school.addTeacher(teacher);
    }
    private void updateTeacher() {
        System.out.println("enter the Teacher id :");
        int id = scanner.nextInt();
        scanner.nextLine();
        Teacher teacher = school.getTeachers().stream().filter(T -> T.getId() == id).findFirst().orElse(null);
        if (teacher != null) {
            System.out.println("enter the new name :");
            String name = scanner.nextLine();
            System.out.println("enter the new subject :");
            String subject = scanner.nextLine();
            scanner.nextLine();
            teacher.setName(name);
            teacher.setSubject(subject);
            System.out.println("Teacher updated successfully");
        } else {
            System.out.println("Teacher unfound");
        }
    }
    private void deleteTeacher() {
        System.out.println("Enter the Teacher Id :");
        int id = scanner.nextInt();
        for (Teacher T : school.getTeachers()) {
            if (T.getId() == id) {
                school.deleteTeacher(T);
                System.out.println("Teacher deleted successfully");
                break;
            }
        }
    }
}
