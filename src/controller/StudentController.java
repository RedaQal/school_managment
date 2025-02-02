package controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.StudentDAO;
import model.Student;
import view.StudentView;

public class StudentController {
    private StudentView studentView;
    private StudentDAO studentDAO;

    public StudentView getStudentView() {
        return studentView;
    }
    public StudentController() {
        this.studentDAO = new StudentDAO();
        this.studentView = new StudentView();
        loadStudents();
    }

    private void loadStudents() {
        try {
            ArrayList<Student> students = (ArrayList<Student>) studentDAO.findAll();
            DefaultTableModel model = studentView.getTableModel();
            model.setRowCount(0);
            for (Student s : students) {
                model.addRow(new Object[] { s.getId(), s.getName(), s.getAge() });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(studentView, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}