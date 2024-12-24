package view;

import model.School;
import model.Student;

public class StudentView {
    public void displayStudentMenu(){
        System.out.println("1- add a student");
        System.out.println("2- update student");
        System.out.println("3- delete student");
        System.out.println("4- show students");
        System.out.println("5- quitte");
    }
    public void displayStudents(School school) {
        for (Student s : school.getStudents()) {
            System.out.println(s);
        }
    }
}
