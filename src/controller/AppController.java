package controller;

import java.awt.CardLayout;

import javax.swing.JPanel;

import view.AppView;
import view.CostumSplash;

public class AppController {
    private AppView view;
    private SchoolClassController schoolClassController;
    private StudentController studentController;
    private TeacherController teacherController;
    
    public AppController(){
        view = new AppView();
        studentController = new StudentController();
        teacherController = new TeacherController();
        schoolClassController = new SchoolClassController();
    }
    public void start(){
        CardLayout mainCardLayout = view.getMainCardLayout();
        JPanel mainPanel = view.getMainPanel();
        mainPanel.add(studentController.getStudentView(),"studentManagement");
        mainPanel.add(teacherController.getTeacherView(),"teacherManagement");
        mainPanel.add(schoolClassController.getSchoolClassView(),"classesManagement"); 

        view.getStudentItem().addActionListener(e -> mainCardLayout.show(mainPanel, "studentManagement")); 
        view.getTeacherItem().addActionListener(e -> mainCardLayout.show(mainPanel, "teacherManagement")); 
        view.getClassesItem().addActionListener(e -> {
            schoolClassController.loadClasses();
            mainCardLayout.show(mainPanel, "classesManagement");
        });
        view.setVisible(true);
    }
    public void app(){
        CostumSplash splashScreen = new CostumSplash();
        splashScreen.setVisible(true);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        splashScreen.setVisible(false);
        start();
    }
}
