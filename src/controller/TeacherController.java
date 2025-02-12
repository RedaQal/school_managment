package controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;

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
        teacherView.getAddBtn().addActionListener(e -> addTeacher());
        teacherView.getTeacherTable().getSelectionModel().addListSelectionListener(e -> listHandler());

    }

    private void listHandler() {

        teacherView.getNewBtn().setEnabled(true);
        teacherView.getEditBtn().setEnabled(true);
        teacherView.getDeleteBtn().setEnabled(true);
        int selectedRow = teacherView.getTeacherTable().getSelectedRow();
        for (ActionListener al : teacherView.getDeleteBtn().getActionListeners()) {
            teacherView.getDeleteBtn().removeActionListener(al);
        }
        for (ActionListener al : teacherView.getEditBtn().getActionListeners()) {
            teacherView.getEditBtn().removeActionListener(al);
        }
        if (selectedRow != -1) {
            int id = (int) teacherView.getTeacherTable().getValueAt(selectedRow, 0);
            String name = (String) teacherView.getTeacherTable().getValueAt(selectedRow, 1);
            String subject = (String) teacherView.getTeacherTable().getValueAt(selectedRow, 2);
            teacherView.getNameField().setText(name);
            teacherView.getSubjectField().setText(subject);

            Teacher teacher = new Teacher(id, name, subject);

            teacherView.getDeleteBtn().addActionListener(element -> {
                deleteTeacher(teacher);
                formCleaning();
            });
            teacherView.getEditBtn().addActionListener(element -> {
                updateTeacher(teacher);
                formCleaning();
            });
            teacherView.getNewBtn().addActionListener(element -> formCleaning());

        }
        teacherView.getAddBtn().setEnabled(false);
    }

    private void loadTeachers() {
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

    private void addTeacher() {
        try {
            String teacherName = teacherView.getNameField().getText();
            String teacherSubject = teacherView.getSubjectField().getText();
            if (!teacherSubject.isEmpty() && !teacherName.isEmpty()) {
                Teacher teacher = new Teacher(teacherName, teacherSubject);
                teacherDAO.save(teacher);
                emptyFields();
                loadTeachers();
                JOptionPane.showMessageDialog(teacherView, "Teacher : " + teacher.getName() + " added successfully",
                        "teacher added", JOptionPane.INFORMATION_MESSAGE);
            } else {
                throw new Exception("you must enter a valid name and subject");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(teacherView, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteTeacher(Teacher teacher) {
        try {
            teacherDAO.delete(teacher.getId());
            loadTeachers();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(teacherView, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateTeacher(Teacher teacher) {
        try {
            String newName = teacherView.getNameField().getText();
            String newSubject = teacherView.getSubjectField().getText();
            if (newSubject != null && !newName.isEmpty()) {
                teacher.setName(newName);
                teacher.setSubject(newSubject);
                teacherDAO.update(teacher);
                loadTeachers();
                JOptionPane.showMessageDialog(teacherView, "Teacher : " + teacher.getName() + " edited successfully",
                        "teacher updated", JOptionPane.INFORMATION_MESSAGE);
            } else {
                throw new Exception("you must enter a valid name and subject");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(teacherView, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void formCleaning() {
        teacherView.getTeacherTable().clearSelection();
        teacherView.getNewBtn().setEnabled(false);
        teacherView.getEditBtn().setEnabled(false);
        teacherView.getDeleteBtn().setEnabled(false);
        teacherView.getAddBtn().setEnabled(true);
        emptyFields();
    }

    private void emptyFields() {
        teacherView.getNameField().setText("");
        teacherView.getSubjectField().setText("");
    }
}
