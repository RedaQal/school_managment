package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class TeacherView extends JPanel {
    private DefaultTableModel tableModel;
    private JTable teacherTable;
    private JTextField nameField;
    private JTextField subjectField;
    private JButton addBtn;
    private JButton newBtn;
    private JButton editBtn;
    private JButton deleteBtn;

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTable getTeacherTable() {
        return teacherTable;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getSubjectField() {
        return subjectField;
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

    public TeacherView() {
        // initialize the frame
        setLayout(new BorderLayout(20, 20));
        // Title
        JLabel title = new JLabel("Teacher Management",JLabel.CENTER);
        title.setFont(new Font("Segoe UI",Font.BOLD,24));
        title.setForeground(primaryColor);
        add(title,BorderLayout.NORTH);
        // Form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(secondaryColor);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel nameLabel = new JLabel("Name :");
        nameLabel.setFont(new Font("Segoe",Font.PLAIN,16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(nameLabel,gbc);
        nameField = new JTextField();
        nameField.setFont(new Font("Segoe",Font.PLAIN,14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        formPanel.add(nameField,gbc);
        JLabel subjectLabel = new JLabel("Subject :");
        subjectLabel.setFont(new Font("Segoe",Font.PLAIN,16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(subjectLabel,gbc);
        subjectField = new JTextField();
        subjectField.setFont(new Font("Segoe",Font.PLAIN,14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        formPanel.add(subjectField,gbc);
        addBtn = createModerButton("add", primaryColor, secondaryColor);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        formPanel.add(addBtn,gbc);
        add(formPanel,BorderLayout.WEST);
        // table
        String[] colStrings = {"ID","Name","Subject"};
        tableModel = new DefaultTableModel(colStrings,0);
        teacherTable = new JTable(tableModel);
        teacherTable.setRowHeight(30);
        teacherTable.setFont(new Font("Segoe",Font.PLAIN,14));
        teacherTable.getTableHeader().setFont(new Font("Segoe",Font.BOLD,16));
        teacherTable.getTableHeader().setBackground(primaryColor);
        teacherTable.getTableHeader().setForeground(secondaryColor);
        teacherTable.setSelectionBackground(tableRowColor);

        JScrollPane scrollPane = new JScrollPane(teacherTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane,BorderLayout.CENTER);
        
        // Actions
        JPanel actionPannel = new JPanel();
        actionPannel.setLayout(new FlowLayout(FlowLayout.CENTER,15,10));
        actionPannel.setBackground(backgroundColor);

        newBtn = createModerButton("New", primaryColor, secondaryColor);
        editBtn = createModerButton("Edit", Color.decode("#F39C12"), secondaryColor);
        deleteBtn = createModerButton("Delete", Color.decode("#E74C3C"), secondaryColor);

        actionPannel.add(newBtn);
        actionPannel.add(editBtn);
        actionPannel.add(deleteBtn);

        add(actionPannel,BorderLayout.SOUTH);

        newBtn.setEnabled(false);
        editBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
    }
}
