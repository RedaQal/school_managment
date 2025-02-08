package controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;

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
        studentView.getAddBtn().addActionListener(e -> addStudent());
        studentView.getStudentTable().getSelectionModel().addListSelectionListener(e -> {
            studentView.getNewBtn().setEnabled(true);
            studentView.getEditBtn().setEnabled(true);
            studentView.getDeleteBtn().setEnabled(true);
            int selectedRow = studentView.getStudentTable().getSelectedRow();
            for (ActionListener al : studentView.getDeleteBtn().getActionListeners()) {
                studentView.getDeleteBtn().removeActionListener(al);
            }
            for (ActionListener al : studentView.getEditBtn().getActionListeners()) {
                studentView.getEditBtn().removeActionListener(al);
            }
            if (selectedRow != -1) {
                int id = (int) studentView.getStudentTable().getValueAt(selectedRow, 0);
                String name = (String) studentView.getStudentTable().getValueAt(selectedRow, 1);
                int age = (int) studentView.getStudentTable().getValueAt(selectedRow, 2);
                studentView.getNameField().setText(name);
                studentView.getAgeField().setText(Integer.toString(age));

                Student student = new Student(id, name, age);

                studentView.getDeleteBtn().addActionListener(element -> {
                    deleteStudent(student);
                    formCleaning();
                });
                studentView.getEditBtn().addActionListener(element -> {
                    updateStudent(student);
                    formCleaning();
                });
                studentView.getNewBtn().addActionListener(element -> formCleaning());

            }
            studentView.getAddBtn().setEnabled(false);
        });
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

    private void addStudent() {
        try {
            String studentName = studentView.getNameField().getText();
            int studentAge = Integer.parseInt(studentView.getAgeField().getText());
            if (studentAge != 0 && !studentName.isEmpty()) {
                Student student = new Student(studentName, studentAge);
                studentDAO.save(student);
                emptyFields();
                loadStudents();
                JOptionPane.showMessageDialog(studentView, "Student : " + student.getName() + " added successfully",
                        "student added", JOptionPane.INFORMATION_MESSAGE);
            } else {
                throw new Exception("you must enter a valid name and age");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(studentView, "Age must be an natural number", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(studentView, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteStudent(Student student) {
        try {
            studentDAO.delete(student.getId());
            loadStudents();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(studentView, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateStudent(Student student) {
        try {
            String newName = studentView.getNameField().getText();
            int newAge = Integer.parseInt(studentView.getAgeField().getText());
            if (newAge != 0 && !newName.isEmpty()) {
                student.setName(newName);
                student.setAge(newAge);
                studentDAO.update(student);
                loadStudents();
                JOptionPane.showMessageDialog(studentView, "Student : " + student.getName() + " edited successfully",
                        "student updated", JOptionPane.INFORMATION_MESSAGE);
            } else {
                throw new Exception("you must enter a valid name and age");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(studentView, "Age must be an natural number", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(studentView, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void formCleaning() {
        studentView.getStudentTable().clearSelection();
        studentView.getNewBtn().setEnabled(false);
        studentView.getEditBtn().setEnabled(false);
        studentView.getDeleteBtn().setEnabled(false);
        studentView.getAddBtn().setEnabled(true);
        emptyFields();
    }

    private void emptyFields() {
        studentView.getNameField().setText("");
        studentView.getAgeField().setText("");
    }
}