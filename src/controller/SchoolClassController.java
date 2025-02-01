package controller;


import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.SchoolClassDAO;
import dao.TeacherDAO;
import model.SchoolClass;
import model.Teacher;
import view.SchoolClassView;

public class SchoolClassController {
    private TeacherDAO teacherDAO;
    private SchoolClassDAO schoolClassDAO;
    private SchoolClassView schoolClassView;

    public SchoolClassController(SchoolClassView schoolClassView,SchoolClassDAO schoolClassDAO) {
        this.schoolClassView = schoolClassView;
        this.schoolClassDAO = schoolClassDAO;
        this.teacherDAO = new TeacherDAO();
        loadClasses();
    }
    private void loadClasses(){
        try {
            List<SchoolClass> classes = schoolClassDAO.findAll();
            DefaultTableModel tableModel = schoolClassView.getTableModel();
            tableModel.setRowCount(0);
            for(SchoolClass c : classes){
                tableModel.addRow(new Object[]{c.getId(),c.getClassName(),c.getTeacher().getName()});
            }
            List<Teacher> teachers = teacherDAO.findAll();
            JComboBox<Teacher> teacherComboBox = schoolClassView.getTeacherComboBox();
            for(Teacher t: teachers){
                teacherComboBox.addItem(t);
            }
        } catch (Exception e) {
             JOptionPane.showMessageDialog(schoolClassView, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
