package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class AppView extends JFrame {
    private CardLayout mainCardLayout;
    private JPanel mainPanel;
    private JMenuItem studentItem;
    private JMenuItem teacherItem;
    private JMenuItem classesItem;

    public AppView() {
        setTitle("School Management");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu schoolManagementMenu = new JMenu("School Management");
        JMenu quitMenu = new JMenu("quit");
        // initialize menus items
        studentItem = new JMenuItem("Students Management");
        teacherItem = new JMenuItem("Teachers Management");
        classesItem = new JMenuItem("Classes Management");
        JMenuItem quitMenuItem = new JMenuItem("quit");
        // adding events for each item
        quitMenuItem.addActionListener(e -> System.exit(0));

        schoolManagementMenu.add(studentItem);
        schoolManagementMenu.addSeparator();
        schoolManagementMenu.add(teacherItem);
        schoolManagementMenu.addSeparator();
        schoolManagementMenu.add(classesItem);
        quitMenu.add(quitMenuItem);

        menuBar.add(schoolManagementMenu);
        menuBar.add(quitMenu);
        setJMenuBar(menuBar);

        mainPanel = new JPanel();
        mainCardLayout = new CardLayout();
        mainPanel.setLayout(mainCardLayout);

        add(mainPanel, BorderLayout.CENTER);
    }

    public CardLayout getMainCardLayout() {
        return mainCardLayout;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JMenuItem getStudentItem() {
        return studentItem;
    }

    public JMenuItem getTeacherItem() {
        return teacherItem;
    }

    public JMenuItem getClassesItem() {
        return classesItem;
    }
}