package controller;

import java.awt.CardLayout;

import javax.swing.JPanel;

import view.AppView;

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
        view.getClassesItem().addActionListener(e -> mainCardLayout.show(mainPanel, "classesManagement"));

        view.setVisible(true);
    }
}
