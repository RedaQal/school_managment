package view;

import java.util.List;

import model.SchoolClass;
import model.Student;
import model.Teacher;

public class SchoolClassView {
    public void displaySchoolClassMenu() {
        System.out.println("|-------Student Menu-------|");
        System.out.println("1- add a class");
        System.out.println("2- update class");
        System.out.println("3- delete class");
        System.out.println("4- add student to the class");
        System.out.println("5- delete student from a class");
        System.out.println("6- show students");
        System.out.println("7- show classes");
        System.out.println("8- quitte");
    }

    public void displaySchoolClasses(List<SchoolClass> classes) {
        for (SchoolClass c : classes) {
            System.out.println(c);
        }
    }

    public void displayClassStudents(List<Student> students) {
        for (Student s : students) {
            System.out.println(s);
        }
    }
}
