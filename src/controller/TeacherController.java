package controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.TeacherDAO;

import model.Teacher;
import view.TeacherView;

public class TeacherController {
    private TeacherView teacherView;
    private TeacherDAO teacherDAO;

    public TeacherView getTeacherView() {
        return teacherView;
    }
    public TeacherController() {
        this.teacherView = new TeacherView() ;
        this.teacherDAO = new TeacherDAO() ;
        loadTeachers();

    }
    private void loadTeachers(){
         try {
            ArrayList<Teacher> Teachers = (ArrayList<Teacher>) teacherDAO.findAll();
            DefaultTableModel model = teacherView.getTableModel();
            model.setRowCount(0);
            for (Teacher t : Teachers) {
                model.addRow(new Object[] { t.getId(), t.getName(), t.getSubject() });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(teacherView, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
