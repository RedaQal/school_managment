package controller;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;

import dao.SchoolClassDAO;
import dao.TeacherDAO;
import model.SchoolClass;
import model.Teacher;
import view.SchoolClassView;

public class SchoolClassController {
    private TeacherDAO teacherDAO;
    private SchoolClassDAO schoolClassDAO;
    private SchoolClassView schoolClassView;

    public SchoolClassView getSchoolClassView() {
        return schoolClassView;
    }

    public SchoolClassController() {
        this.schoolClassView = new SchoolClassView();
        this.schoolClassDAO = new SchoolClassDAO();
        this.teacherDAO = new TeacherDAO();
        loadClasses();
        schoolClassView.getAddBtn().addActionListener(e -> addClass());
        schoolClassView.getClassTable().getSelectionModel().addListSelectionListener(e -> listHandler());
    }

    private void listHandler() {
        schoolClassView.getNewBtn().setEnabled(true);
        schoolClassView.getEditBtn().setEnabled(true);
        schoolClassView.getDeleteBtn().setEnabled(true);
        int selectedRow = schoolClassView.getClassTable().getSelectedRow();
        for (ActionListener al : schoolClassView.getDeleteBtn().getActionListeners()) {
            schoolClassView.getDeleteBtn().removeActionListener(al);
        }
        for (ActionListener al : schoolClassView.getEditBtn().getActionListeners()) {
            schoolClassView.getEditBtn().removeActionListener(al);
        }
        if (selectedRow != -1) {
            int id = (int) schoolClassView.getClassTable().getValueAt(selectedRow, 0);
            String className = (String) schoolClassView.getClassTable().getValueAt(selectedRow, 1);
            Teacher teacher = (Teacher) schoolClassView.getClassTable().getValueAt(selectedRow, 2);
            schoolClassView.getNameField().setText(className);
            SchoolClass schoolClass = new SchoolClass(id, className, teacher);
            schoolClassView.getTeacherComboBox().setSelectedItem(teacher);
            schoolClassView.getDeleteBtn().addActionListener(element -> deleteClass(id));
            schoolClassView.getEditBtn().addActionListener(element -> updateClass(schoolClass));
            schoolClassView.getNewBtn().addActionListener(element -> formCleaning());
        }
    }

    private void addClass() {
        try {
            String className = schoolClassView.getNameField().getText();
            Teacher teacher = (Teacher) schoolClassView.getTeacherComboBox().getSelectedItem();
            if(className.isEmpty() || teacher == null){
                throw new Exception("you must enter a valid name and teacher");
            }
            SchoolClass schoolClass = new SchoolClass(className, teacher);
            schoolClassDAO.save(schoolClass);
            loadClasses();
            formCleaning();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(schoolClassView, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteClass(int id) {
        try {
            schoolClassDAO.delete(id);
            loadClasses();
            formCleaning();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(schoolClassView, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void updateClass(SchoolClass schoolClass) {
        try {
            String className = schoolClassView.getNameField().getText();
            Teacher teacher = (Teacher) schoolClassView.getTeacherComboBox().getSelectedItem();
            if(className.isEmpty() || teacher == null){
                throw new Exception("you must enter a valid name and teacher");
            }
            schoolClass.setClassName(className);
            schoolClass.setTeacher(teacher);
            schoolClassDAO.update(schoolClass);
            loadClasses();
            formCleaning();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(schoolClassView, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void loadClasses() {
        try {
            List<SchoolClass> classes = schoolClassDAO.findAll();
            DefaultTableModel tableModel = schoolClassView.getTableModel();
            tableModel.setRowCount(0);
            for (SchoolClass c : classes) {
                tableModel.addRow(new Object[] { c.getId(), c.getClassName(), c.getTeacher()});
            }
            List<Teacher> teachers = teacherDAO.findAll();
            JComboBox<Teacher> teacherComboBox = schoolClassView.getTeacherComboBox();
            teacherComboBox.removeAllItems();
            for (Teacher t : teachers) {
                teacherComboBox.addItem(t);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(schoolClassView, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void formCleaning() {
        schoolClassView.getClassTable().clearSelection();
        schoolClassView.getNewBtn().setEnabled(false);
        schoolClassView.getEditBtn().setEnabled(false);
        schoolClassView.getDeleteBtn().setEnabled(false);
        schoolClassView.getAddBtn().setEnabled(true);
        emptyFields();
    }

    private void emptyFields() {
        schoolClassView.getNameField().setText("");
        schoolClassView.getTeacherComboBox().setSelectedIndex(0);
    }
}
