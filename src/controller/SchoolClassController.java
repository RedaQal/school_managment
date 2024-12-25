package controller;

import java.util.Scanner;

import model.School;
import model.SchoolClass;
import model.Student;
import model.Teacher;
import view.SchoolClassView;

public class SchoolClassController {
    private School school;
    private SchoolClassView view;
    private Scanner scanner;

    SchoolClassController(School school){
    this.school = school;
    this.view = new SchoolClassView();
    this.scanner = new Scanner(System.in);
    }
    public void manageSchoolClass() {
        int entry;
        do {
            view.displaySchoolClassMenu();
            entry = scanner.nextInt();
            scanner.nextLine();
            switch (entry) {
                case 1:
                addSchoolClass();
                    break;
                case 2:
                updateSchoolClass();
                    break;
                case 3:
                deleteSchoolClass();
                    break;
                case 4:
                addStudentToClass();
                    break;
                case 5:
                deleteStudentFromClass();
                    break;
                case 6:
                displayClassStudents();
                    break;
                case 7:
                    view.displaySchoolClasses(school.getClasses());
                    break;
                case 8:
                    System.out.println("quite");
                    break;
                default:
                    break;
            }
        } while (entry != 8);
    }
    private Teacher findTeacher(int id){
        Teacher teacher = school.getTeachers().stream().filter( t -> t.getId() == id).findFirst().orElse(null);
        return teacher;
        
    }
    private void addSchoolClass() {
        System.out.println("Enter the Class Id :");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the Class name :");
        String name = scanner.nextLine();
        System.out.println("Enter the Class teacher id :");
        int teacherId = scanner.nextInt();
        scanner.nextLine();
        Teacher teacher = findTeacher(teacherId);
        SchoolClass schoolClass = new SchoolClass(id, name ,teacher );
        school.addSchoolClass(schoolClass);
    }
    private void updateSchoolClass() {
        System.out.println("enter the class id :");
        int id = scanner.nextInt();
        scanner.nextLine();
        SchoolClass schoolClass = school.getClasses().stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        if (schoolClass != null) {
            System.out.println("enter the new class name :");
            String name = scanner.nextLine();
            System.out.println("enter the new teacher id :");
            int teacherId = scanner.nextInt();
            scanner.nextLine();
            Teacher teacher = findTeacher(teacherId);
            schoolClass.setClassName(name);
            schoolClass.setTeacher(teacher);
            System.out.println("The class is updated successfully");
        } else {
            System.out.println("class not found");
        }
    }
    private void deleteSchoolClass() {
        System.out.println("Enter the class Id :");
        int id = scanner.nextInt();
        for (SchoolClass c : school.getClasses()) {
            if (c.getId() == id) {
                school.deleteSchoolClass(c);
                System.out.println("class deleted successfully");
                break;
            }
        }
    }
    private SchoolClass getSchoolClasses(int id){
        SchoolClass schoolClass = school.getClasses().stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        return schoolClass;
    }
    private Student getStudent(int id){
        Student student = school.getStudents().stream().filter(s -> s.getId() == id).findFirst().orElse(null);
        return student;
    }
    private void addStudentToClass(){
        System.out.println("Enter the class Id :");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the Student Id :");
        int studentId = scanner.nextInt();
        SchoolClass schoolClass = getSchoolClasses(id);
        Student student = getStudent(studentId);
        if (student != null && schoolClass != null) {          
            schoolClass.addStudent(student);
            System.out.println("student added to class successfully");
        }else{
            System.out.println("class or student not found");
        }
    }
    private void deleteStudentFromClass(){
        System.out.println("Enter the class Id :");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the Student Id :");
        int studentId = scanner.nextInt();
        SchoolClass schoolClass = getSchoolClasses(id);
        Student student = getStudent(studentId);
        if (student != null && schoolClass != null) {          
            schoolClass.removeStudent(student);
            System.out.println("student removed from the class successfully");
        }else{
            System.out.println("class or student are not found");
        }
    }
    private void displayClassStudents(){
        System.out.println("Enter the class Id :");
        int id = scanner.nextInt();
        scanner.nextLine();
        SchoolClass schoolClass = getSchoolClasses(id);
        view.displayClassStudents(schoolClass.getStudents());
    }
}
