import controller.AppController;
import model.School;

public class App {

    public static void main(String[] args) throws Exception {
        School school = new School();
        AppController controller = new AppController(school);
        controller.start();
    }
}