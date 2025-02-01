import controller.SchoolClassController;
import controller.StudentController;
import controller.TeacherController;
import dao.SchoolClassDAO;
import dao.StudentDAO;
import dao.TeacherDAO;
import database.DatabaseManager;
import view.SchoolClassView;
import view.StudentView;
import view.TeacherView;

public class App {

    public static void main(String[] args) throws Exception {
        DatabaseManager.initializeDatabase();
        StudentView studentview = new StudentView();
        StudentDAO studentDAO = new StudentDAO();
        new StudentController(studentview, studentDAO);
        studentview.setVisible(true);
        TeacherView teacherView = new TeacherView();
        TeacherDAO teacherDAO = new TeacherDAO();
        new TeacherController(teacherView, teacherDAO);
        teacherView.setVisible(true);
        SchoolClassView schoolClassView = new SchoolClassView();
        SchoolClassDAO schoolClassDAO = new SchoolClassDAO();
        new SchoolClassController(schoolClassView, schoolClassDAO);
        schoolClassView.setVisible(true);
    }
}