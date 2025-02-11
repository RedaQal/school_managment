package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class StudentView extends JPanel {
    private DefaultTableModel tableModel;
    private JTable studentTable;
    private JTextField nameField;
    private JTextField ageField;
    private JButton addBtn;
    private JButton newBtn;
    private JButton editBtn;
    private JButton deleteBtn;

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTable getStudentTable() {
        return studentTable;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getAgeField() {
        return ageField;
    }

    public JButton getAddBtn() {
        return addBtn;
    }

    public JButton getNewBtn() {
        return newBtn;
    }

    public JButton getEditBtn() {
        return editBtn;
    }

    public JButton getDeleteBtn() {
        return deleteBtn;
    }

    Color primaryColor = Color.decode("#0078D7");
    Color secondaryColor = Color.decode("#FFFFFF");
    Color backgroundColor = Color.decode("#F3F3F3");
    Color tableRowColor = Color.decode("#E8F4FF");

    private JButton createModerButton(String text, Color bgColor, Color fgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        return button;
    }

    public StudentView() {
        // initialize the frame
        setLayout(new BorderLayout(20, 20));
        // Title
        JLabel title = new JLabel("Student Management", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(primaryColor);
        add(title, BorderLayout.NORTH);
        // Form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(secondaryColor);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel nameLabel = new JLabel("Name :");
        nameLabel.setFont(new Font("Segoe", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(nameLabel, gbc);
        nameField = new JTextField();
        nameField.setFont(new Font("Segoe", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        formPanel.add(nameField, gbc);
        JLabel ageLabel = new JLabel("Age :");
        ageLabel.setFont(new Font("Segoe", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(ageLabel, gbc);
        ageField = new JTextField();
        ageField.setFont(new Font("Segoe", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        formPanel.add(ageField, gbc);
        addBtn = createModerButton("add", primaryColor, secondaryColor);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        formPanel.add(addBtn, gbc);
        add(formPanel, BorderLayout.WEST);
        // table
        String[] colStrings = { "ID", "Name", "Age" };
        tableModel = new DefaultTableModel(colStrings, 0);
        studentTable = new JTable(tableModel);
        studentTable.setDefaultEditor(Object.class, null);;
        studentTable.setRowHeight(30);
        studentTable.setFont(new Font("Segoe", Font.PLAIN, 14));
        studentTable.getTableHeader().setFont(new Font("Segoe", Font.BOLD, 16));
        studentTable.getTableHeader().setBackground(primaryColor);
        studentTable.getTableHeader().setForeground(secondaryColor);
        studentTable.setSelectionBackground(tableRowColor);

        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);

        // Actions
        JPanel actionPannel = new JPanel();
        actionPannel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
        actionPannel.setBackground(backgroundColor);

        newBtn = createModerButton("New", primaryColor, secondaryColor);
        editBtn = createModerButton("Edit", Color.decode("#F39C12"), secondaryColor);
        deleteBtn = createModerButton("Delete", Color.decode("#E74C3C"), secondaryColor);

        actionPannel.add(newBtn);
        actionPannel.add(editBtn);
        actionPannel.add(deleteBtn);

        add(actionPannel, BorderLayout.SOUTH);

        newBtn.setEnabled(false);
        editBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
    }
}
