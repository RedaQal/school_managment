package controller;

import java.util.Scanner;

import model.School;
import view.AppView;

public class AppController {
    private AppView view;
    private Scanner scanner;
    private SchoolController schoolController;
    private StudentController studentController;
    private TeacherController teacherController;
    private SchoolClassController schoolClassController;
    public AppController(School school){
        this.view = new AppView();
        this.scanner = new Scanner(System.in);
        schoolController = new SchoolController(school);
        studentController = new StudentController(school);
        teacherController = new TeacherController(school);
        schoolClassController = new SchoolClassController(school);
    }
    public void start(){
        int entry;
        do {
            view.displayMainMenu();
            entry = scanner.nextInt();
            scanner.nextLine();
            switch (entry) {
                case 1:
                     studentController.manageStudent();
                    break;
                case 2:
                     teacherController.manageTeacher();
                    break;
                default:
                    break;
            }
        } while (entry != 4);
    }
}
